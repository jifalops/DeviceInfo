package com.deviceinfoapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.deviceinfoapp.data.Elements;
import com.deviceinfoapp.element.ActiveElement;
import com.deviceinfoapp.element.UnavailableFeatureException;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.ItemExpandableListAdapter;

public class MainFragment extends Fragment implements ActiveElementController.Callbacks {

    public static final String ARG_ITEM_ID = "item_id";

    private static final int INDICATOR_DURATION = 200;

    private int mItem;

    private AbsElementController mController;
    private boolean mIsActive, mIsPlaying;
    private MenuItem mIndicatorMenuItem, mPlayPauseMenuItem;
    private Handler mHandler;
    private boolean mIsShowing;
    private boolean mPlayImmediately;

    private ItemExpandableListAdapter mAdapter;
    protected ExpandableListView mListView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mHandler = new Handler();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View root = inflater.inflate(R.layout.fragment_item_detail, container, false);

        mListView = (ExpandableListView) root.findViewById(R.id.list);
        mListView.setGroupIndicator(null);

//        List<Item> info = null;

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getInt(ARG_ITEM_ID);

//            String[] items = getResources().getStringArray(R.array.main_items);
//            getActivity().setTitle(items[mItem]);

            switch (mItem) {
                case Elements.OVERVIEW: break;
                case Elements.PROCESSORS: break;
                case Elements.RAM: break;
                case Elements.STORAGE: break;
                case Elements.DISPLAY: break;
                case Elements.CAMERAS: break;
                case Elements.BATTERY:
                    mController = new BatteryController(getActivity(), new BatteryController.Callbacks() {

                    });
                    mPlayImmediately = true;
                    break;
                case Elements.SENSORS: break;
                case Elements.AUDIO:
                    mController = new AudioController(getActivity());
                    break;
                case Elements.GRAPHICS: break;
                case Elements.LOCATION: break;
                case Elements.NETWORK: break;
                case Elements.CELLULAR: break;
                case Elements.WIFI: break;
                case Elements.BLUETOOTH:
                    try {
                        mController = new BluetoothController(getActivity(), new BluetoothController.Callbacks() {

                        });
                        mPlayImmediately = true;
                    } catch (UnavailableFeatureException e) {
                        e.printStackTrace();
                    }
                    break;
                case Elements.UPTIME: break;
                case Elements.PLATFORM: break;
                case Elements.IDENTIFIERS: break;
                case Elements.FEATURES: break;
                case Elements.PROPERTIES: break;
                case Elements.KEYS: break;
            }

            if (mController != null) {
                if (mController instanceof ActiveElementController) {
                    mIsActive = true;
                    ((ActiveElement) mController).setCallbacks(this);

                    mAdapter = new ItemExpandableListAdapter(getActivity(), mController.getData());
                    mListView.setAdapter(mAdapter);
                    mListView.expandGroup(0);
                }
            }
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mIsActive) {
            inflater.inflate(R.menu.fragment_detail_dynamic, menu);
            mIndicatorMenuItem = menu.findItem(R.id.menu_indicator);
            mPlayPauseMenuItem = menu.findItem(R.id.menu_playpause);
            if (mPlayImmediately) start();
        }
        else {
            inflater.inflate(R.menu.fragment_detail, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_playpause:
                if (mIsPlaying) {
                    stop();
                }
                else {
                    start();
                }
//                final ImageView iv = new ImageView(getActivity());
//                iv.setImageDrawable(item.getIcon());
//                Animation rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
//                rotation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        item.setActionView(null);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//                iv.startAnimation(rotation);
//                item.setActionView(iv);
                break;

        }
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mIsActive) {
            stop();
            mHandler.removeCallbacksAndMessages(null);
            mIndicatorMenuItem.setIcon(R.drawable.indicator_inactive);
        }
    }

    private void start() {
        ((ActiveElementController) mController).start();
        mPlayPauseMenuItem.setIcon(R.drawable.pause);
        mIsPlaying = true;
    }

    private void stop() {
        ((ActiveElementController) mController).stop();
        mPlayPauseMenuItem.setIcon(R.drawable.play);
        mIsPlaying = false;
    }

    private void showIndicator() {
        mHandler.removeCallbacksAndMessages(null);

        if (!mIsShowing) {
            mIndicatorMenuItem.setIcon(R.drawable.indicator_active);
            mIsShowing = true;
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIndicatorMenuItem.setIcon(R.drawable.indicator_inactive);
                mIsShowing = false;
            }
        }, INDICATOR_DURATION);
    }


    //
    // ActiveItemController callbacks
    //

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onAction(int action, long timestamp) {
        int pos = mListView.getFirstVisiblePosition();
        int count = mListView.getChildCount();
        Item item;
        for (int i = pos; i < count; ++i) {
            //item = (Item) mAdapter.g
        }

        listVisibleRowsForExpandableGroup();
//        mAdapter.notifyDataSetChanged();
        showIndicator();

        // vvv  Do using ActiveElementController?
        // TODO when this is called (up to fragment), update visible views
//        You can check whether a given position is visible, using getFirstVisiblePosition() and getChildCount().
        // dont wait for getView in Item, just setText from adapter.
    }

    public void listVisibleRowsForExpandableGroup()
    {
        int firstVis  = mListView.getFirstVisiblePosition();
        int lastVis = mListView.getLastVisiblePosition();

        int count = firstVis;

        while (count <= lastVis)
        {
            long longposition = mListView.getExpandableListPosition(count);
            int type = mListView.getPackedPositionType(longposition);
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                int groupPosition = mListView.getPackedPositionGroup(longposition);
                int childPosition = mListView.getPackedPositionChild(longposition);
                Log.d("Test", "group: " + groupPosition + " and child: " + childPosition);
            }
            count++;

        }
    }
}
