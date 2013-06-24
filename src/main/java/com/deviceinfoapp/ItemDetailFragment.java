package com.deviceinfoapp;

import android.app.ListFragment;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.deviceinfoapp.adapter.ElementAdapter;
import com.deviceinfoapp.element.Audio;
import com.deviceinfoapp.element.Battery;
import com.deviceinfoapp.element.Bluetooth;
import com.deviceinfoapp.element.Element;
import com.deviceinfoapp.element.ListeningElement;
import com.deviceinfoapp.element.UnavailableFeatureException;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends ListFragment implements Battery.Callback, Bluetooth.Callback {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final int INDICATOR_DURATION = 200;

    private static final int PROCESSORS  = 0;
    private static final int RAM 		 = 1;
    private static final int STORAGE 	 = 2;
    private static final int DISPLAY 	 = 3;
    private static final int CAMERAS 	 = 4;
    private static final int BATTERY 	 = 5;
    private static final int SENSORS 	 = 6;
    private static final int AUDIO 		 = 7;
    private static final int GRAPHICS 	 = 8;
    private static final int LOCATION 	 = 9;
    private static final int NETWORK     = 10;
    private static final int CELLULAR 	 = 11;
    private static final int WIFI 		 = 12;
    private static final int BLUETOOTH 	 = 13;
    private static final int UPTIME 	 = 14;
    private static final int PLATFORM 	 = 15;
    private static final int IDENTIFIERS = 16;
    private static final int FEATURES    = 17;
    private static final int PROPERTIES  = 18;
    private static final int KEYS 		 = 19;

    /**
     * The dummy content this fragment is presenting.
     */
    private int mItem;

    private Element mElement;
    private boolean mIsPlayable, mIsPlaying;
    private MenuItem mIndicatorMenuItem, mPlayPauseMenuItem;
    private Handler mHandler;
    private ElementAdapter mAdapter;
    private boolean mIsShowing;
    private boolean mPlayImmediately;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mHandler = new Handler();

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getInt(ARG_ITEM_ID);

            String[] items = getResources().getStringArray(R.array.main_items);
            getActivity().setTitle(items[mItem]);

            switch (mItem) {
                case PROCESSORS: break;
                case RAM: break;
                case STORAGE: break;
                case DISPLAY: break;
                case CAMERAS: break;
                case BATTERY: mElement = new Battery(getActivity());
                    mPlayImmediately = true;
                    break;
                case SENSORS: break;
                case AUDIO: mElement = new Audio(getActivity());
                    break;
                case GRAPHICS: break;
                case LOCATION: break;
                case NETWORK: break;
                case CELLULAR: break;
                case WIFI: break;
                case BLUETOOTH:
                    try {
                        mElement = new Bluetooth(getActivity());
                        mPlayImmediately = true;
                    } catch (UnavailableFeatureException e) {
                        e.printStackTrace();
                    }
                    break;
                case UPTIME: break;
                case PLATFORM: break;
                case IDENTIFIERS: break;
                case FEATURES: break;
                case PROPERTIES: break;
                case KEYS: break;
            }

            if (mElement != null) {
                if (mElement instanceof ListeningElement) {
                    mIsPlayable = true;
                    ((ListeningElement) mElement).setCallback(this);
                }
                mAdapter = new ElementAdapter(getActivity(), mElement);
                setListAdapter(mAdapter);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mIsPlayable) {
            inflater.inflate(R.menu.fragment_detail_dynamic, menu);
            mIndicatorMenuItem = menu.findItem(R.id.menu_indicator);
            mPlayPauseMenuItem = menu.findItem(R.id.menu_playpause);
            if (mPlayImmediately) play();
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
                    pause();
                }
                else {
                    play();
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
        if (mIsPlayable) {
            pause();
            mHandler.removeCallbacksAndMessages(null);
            mIndicatorMenuItem.setIcon(R.drawable.indicator_inactive);
        }
    }

    private void play() {
        ((ListeningElement) mElement).startListening();
        mPlayPauseMenuItem.setIcon(R.drawable.pause);
        mIsPlaying = true;
    }

    private void pause() {
        ((ListeningElement) mElement).stopListening();
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
    // Battery callbacks
    //

    @Override
    public void onReceive(Context context, Intent intent) {
        mAdapter.update();
        mAdapter.notifyDataSetChanged();
        showIndicator();
    }

    //
    // Bluetooth callbacks
    //

    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        mAdapter.update();
        mAdapter.notifyDataSetChanged();
        showIndicator();
    }

    @Override
    public void onServiceDisconnected(int profile) {
        mAdapter.update();
        mAdapter.notifyDataSetChanged();
        showIndicator();
    }
}
