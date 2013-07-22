package com.deviceinfoapp;

import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Address;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.telephony.CellLocation;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.deviceinfoapp.controller.AbsElementController;
import com.deviceinfoapp.controller.ActiveElementController;
import com.deviceinfoapp.controller.AudioController;
import com.deviceinfoapp.controller.BatteryController;
import com.deviceinfoapp.controller.BluetoothController;
import com.deviceinfoapp.controller.CameraController;
import com.deviceinfoapp.controller.CellularController;
import com.deviceinfoapp.controller.CpuController;
import com.deviceinfoapp.controller.DisplayController;
import com.deviceinfoapp.controller.FeaturesController;
import com.deviceinfoapp.controller.GraphicsController;
import com.deviceinfoapp.controller.IdentifiersController;
import com.deviceinfoapp.controller.KeysController;
import com.deviceinfoapp.controller.LocationController;
import com.deviceinfoapp.controller.NetworkController;
import com.deviceinfoapp.controller.PlatformController;
import com.deviceinfoapp.controller.PropertiesController;
import com.deviceinfoapp.controller.RamController;
import com.deviceinfoapp.controller.SensorsController;
import com.deviceinfoapp.controller.StorageController;
import com.deviceinfoapp.controller.UptimeController;
import com.deviceinfoapp.controller.WifiController;
import com.deviceinfoapp.data.Elements;
import com.deviceinfoapp.item.AbsItem1;
import com.deviceinfoapp.item.AbsItem2;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ItemExpandableListAdapter;

import java.util.LinkedHashMap;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainFragment
        extends Fragment
        implements
            ActiveElementController.Callbacks,
            BatteryController.Callbacks,
            BluetoothController.Callbacks,
            CellularController.Callbacks,
            CpuController.Callbacks,
            GraphicsController.Callbacks,
            LocationController.Callbacks,
            RamController.Callbacks,
            SensorsController.Callbacks,
            UptimeController.Callbacks,
            WifiController.Callbacks
        {
    private static final String LOG_TAG = MainFragment.class.getSimpleName();

    public static final String ARG_ITEM_ID = "item_id";

    private static final int INDICATOR_DURATION = 200;

    private int mItem;

    private AbsElementController mController;
    private boolean mIsActionable, mIsPlaying;
    private MenuItem mIndicatorMenuItem, mPlayPauseMenuItem;
    private Handler mHandler;
    private boolean mIsShowing;
    private boolean mPlayImmediately;

    private ItemExpandableListAdapter mAdapter;
    protected ExpandableListView mListView;

    private boolean mIsVisibleInPager;

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

    public void setIsVisibleInPager(boolean visible) {
        mIsVisibleInPager = visible;
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
                case Elements.OVERVIEW:
                    break;
                case Elements.PROCESSORS:
                    mController = new CpuController(getActivity(), this);
                    break;
                case Elements.RAM:
                    mController = new RamController(getActivity(), this);
                    break;
                case Elements.STORAGE:
                    mController = new StorageController(getActivity());
                    break;
                case Elements.DISPLAY:
                    mController = new DisplayController(getActivity());
                    break;
                case Elements.CAMERAS:
                    mController = new CameraController(getActivity());
                    break;
                case Elements.BATTERY:
                    mController = new BatteryController(getActivity(), this);
                    break;
                case Elements.SENSORS:
                    mController = new SensorsController(getActivity(), this);
                    break;
                case Elements.AUDIO:
                    mController = new AudioController(getActivity());
                    break;
                case Elements.GRAPHICS:
                    mController = new GraphicsController(getActivity(), this);
                    break;
                case Elements.LOCATION:
                    mController = new LocationController(getActivity(), this);
                    break;
                case Elements.NETWORK:
                    mController = new NetworkController(getActivity());
                    break;
                case Elements.CELLULAR:
                    mController = new CellularController(getActivity(), this);
                    break;
                case Elements.WIFI:
                    mController = new WifiController(getActivity(), this);
                    break;
                case Elements.BLUETOOTH:
                    mController = new BluetoothController(getActivity(), this);
                    break;
                case Elements.UPTIME:
                    mController = new UptimeController(getActivity(), this);
                    break;
                case Elements.PLATFORM:
                    mController = new PlatformController(getActivity());
                    break;
                case Elements.IDENTIFIERS:
                    mController = new IdentifiersController(getActivity());
                    break;
                case Elements.FEATURES:
                    mController = new FeaturesController(getActivity());
                    break;
                case Elements.PROPERTIES:
                    mController = new PropertiesController(getActivity());
                    break;
                case Elements.KEYS:
                    mController = new KeysController(getActivity());
                    break;
            }

            if (mController != null) {
                if (mController instanceof ActiveElementController) {
                    mIsActionable = ((ActiveElementController) mController).isActionable();
                    mPlayImmediately = false;
                }
                mAdapter = new ItemExpandableListAdapter(getActivity(), mController.getData());
                mListView.setAdapter(mAdapter);

                for (int i = 0; i < mAdapter.getGroupCount(); ++i) {
                    mListView.expandGroup(i);
                }
            }
        }

        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mIsActionable) {
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
    public void onResume() {
        super.onResume();
        if (!mIsVisibleInPager) return;

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mIsVisibleInPager) return;
        if (mIsActionable) {

            mHandler.removeCallbacksAndMessages(null);
            stop();
//            mIndicatorMenuItem.setIcon(R.drawable.indicator_inactive);
        }
    }

    private void start() {
        if (mIsPlaying) return;
        ((ActiveElementController) mController).start();
        mPlayPauseMenuItem.setIcon(R.drawable.pause);
        mIsPlaying = true;
    }

    private void stop() {
        if (mIsVisibleInPager && !mIsPlaying) return;
        ((ActiveElementController) mController).stop();
        if (mPlayPauseMenuItem != null) mPlayPauseMenuItem.setIcon(R.drawable.play);
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
    public void onAction(int action) {
//        mAdapter.notifyDataSetChanged();
//        mListView.invalidate();

//        mAdapter = new ItemExpandableListAdapter(getActivity(), mController.getData());
//        mListView.setAdapter(mAdapter);
//        mListView.expandGroup(0);

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

        Item item = null;
        TextView tv = null;
        while (count <= lastVis)
        {
            long longposition = mListView.getExpandableListPosition(count);
            int type = mListView.getPackedPositionType(longposition);
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                int groupPosition = mListView.getPackedPositionGroup(longposition);
                int childPosition = mListView.getPackedPositionChild(longposition);
                Log.d("Test", "group: " + groupPosition + " and child: " + childPosition);

                item = (Item) mAdapter.getChild(groupPosition, childPosition);
                if (item.hasChanged()) {
                    if (item instanceof AbsItem1) {
                        tv = (TextView) mListView.getChildAt(count).findViewById(R.id.text);
                        if (tv != null) tv.setText(((AbsItem1) item).getText());
                    }
                    else if (item instanceof AbsItem2) {
                        tv = (TextView) mListView.getChildAt(count).findViewById(R.id.text2);
                        if (tv != null) tv.setText(((AbsItem2) item).getText2());
                    }
                }
            }
            count++;

        }
    }


    //
    // BatteryController
    //
    @Override public void onReceive(Context context, Intent intent) {}

    //
    // BluetoothController
    //
    @Override public void onServiceConnected(int profile, BluetoothProfile proxy) {}
    @Override public void onServiceDisconnected(int profile) {}

    //
    // CellularController
    //
    @Override public void onCallForwardingIndicatorChanged(boolean cfi) {}
    @Override public void onCallStateChanged(int state, String incomingNumber) {}
    @Override public void onCellLocationChanged(CellLocation location) {}
    @Override public void onDataActivity(int direction) {}
    @Override public void onDataConnectionStateChanged(int state, int networkType) {}
    @Override public void onMessageWaitingIndicatorChanged(boolean mwi) {}
    @Override public void onServiceStateChanged(ServiceState serviceState) {}
    @Override public void onSignalStrengthsChanged(SignalStrength signalStrength) {}

    //
    // CpuController
    //
    @Override public void onCpuUpdated(int numCpuStatsUpdated) {}

    //
    // GraphicsController
    //
    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {}
    @Override public void onSurfaceChanged(GL10 gl, int width, int height) {}
    @Override public void onDrawFrame(GL10 gl) {}

    //
    // LocationController
    //
    @Override public void onLocationChanged(Location location) {}
    @Override public void onProviderDisabled(String provider) {}
    @Override public void onProviderEnabled(String provider) {}
    @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override public void onAddressChanged(Address address, Location location) {}
    @Override public void onGpsStatusChanged(int event) {}
    @Override public void onNmeaReceived(long timestamp, String nmea) {}

    //
    // RamController
    //
    @Override public void onRamUpdated(LinkedHashMap<String, String> meminfo) {}

    //
    // SensorController
    //
    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override public void onSensorChanged(SensorEvent event) {}

    //
    // UptimeController
    //
    @Override public void onUptimeUpdated(float uptimeTotal, float uptimeAsleep) {}

    //
    // WifiController
    //
    @Override public void onScanCompleted(List<ScanResult> results) {}
    @Override public void onNetworkIdsChanged(List<WifiConfiguration> configurations) {}
    @Override public void onNetworkStateChanged(NetworkInfo networkInfo, String bssid, WifiInfo wifiInfo) {}
    @Override public void onRssiChanged(int rssi) {}
    @Override public void onSupplicantConnectionChanged(boolean connected) {}
    @Override public void onSupplicantStateChanged(SupplicantState state, int error) {}
}
