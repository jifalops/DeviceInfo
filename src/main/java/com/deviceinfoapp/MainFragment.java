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
import android.widget.LinearLayout;
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
import com.deviceinfoapp.item.AbsListItem1;
import com.deviceinfoapp.item.AbsListItem2;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ItemExpandableListAdapter;
import com.deviceinfoapp.item.ListItem;

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

    private int mType;

    private AbsElementController mController;
    private boolean mIsActionable, mIsPlaying;
    private MenuItem mIndicatorMenuItem, mPlayPauseMenuItem;
    private Handler mHandler;
    private boolean mIsShowing;
    private boolean mPlayImmediately;

    private ItemExpandableListAdapter mExpandableAdapter;
    private ExpandableListView mListView;

    private List<Item> mItems;
    private LinearLayout mLinearLayout;

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
        if (container == null || !getArguments().containsKey(ARG_ITEM_ID)) {
            return null;
        }

        mType = getArguments().getInt(ARG_ITEM_ID);

        mController = getController(mType);

        if (mController == null) {
            return null;
        }

        if (mController instanceof ActiveElementController) {
            mIsActionable = ((ActiveElementController) mController).isActionable();
            mPlayImmediately = false;
        }

        View root = null;

        if (mController.usesCachedViews()) {
            mItems = mController.getData();

            root = inflater.inflate(R.layout.fragment_item_detail_cached, container, false);
            mLinearLayout = (LinearLayout) root.findViewById(R.id.list);

            for (int i = 0, len = mItems.size(); i < len; ++i) {
                mLinearLayout.addView(mItems.get(i).getView(inflater, null, null));
            }

        } else {
            root = inflater.inflate(R.layout.fragment_item_detail, container, false);

            mListView = (ExpandableListView) root.findViewById(R.id.list);
            mListView.setGroupIndicator(null);

            mExpandableAdapter = new ItemExpandableListAdapter(getActivity(), mController.getData());
            mListView.setAdapter(mExpandableAdapter);

            for (int i = 0; i < mExpandableAdapter.getGroupCount(); ++i) {
                mListView.expandGroup(i);
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
//        showIndicator();
//        if (mController.usesCachedViews()) return;

//        mExpandableAdapter.notifyDataSetChanged();
//        mListView.invalidate();

//        mExpandableAdapter = new ItemExpandableListAdapter(getActivity(), mController.getData());
//        mListView.setAdapter(mExpandableAdapter);
//        mListView.expandGroup(0);

//        listVisibleRowsForExpandableGroup();
//        mExpandableAdapter.notifyDataSetChanged();


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

        ListItem item = null;
        TextView tv = null;
        while (count <= lastVis)
        {
            long longposition = mListView.getExpandableListPosition(count);
            int type = mListView.getPackedPositionType(longposition);
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                int groupPosition = mListView.getPackedPositionGroup(longposition);
                int childPosition = mListView.getPackedPositionChild(longposition);
                Log.d("Test", "group: " + groupPosition + " and child: " + childPosition);

                item = (ListItem) mExpandableAdapter.getChild(groupPosition, childPosition);
                if (item.hasChanged()) {
                    if (item instanceof AbsListItem1) {
                        tv = (TextView) mListView.getChildAt(count).findViewById(R.id.text);
                        tv.setText(((AbsListItem1) item).getText());
                    }
                    else if (item instanceof AbsListItem2) {
                        tv = (TextView) mListView.getChildAt(count).findViewById(R.id.text2);
                        tv.setText(((AbsListItem2) item).getText2());
                    }
                    item.setHasChanged(false);
                }
            }
            count++;

        }
    }


    private AbsElementController getController(int type) {
        switch (mType) {
            case Elements.OVERVIEW:
                return null;
            case Elements.PROCESSORS:
                return new CpuController(getActivity(), this);
            case Elements.RAM:
                return new RamController(getActivity(), this);
            case Elements.STORAGE:
                return new StorageController(getActivity());
            case Elements.DISPLAY:
                return new DisplayController(getActivity());
            case Elements.CAMERAS:
                return new CameraController(getActivity());
            case Elements.BATTERY:
                return new BatteryController(getActivity(), this);
            case Elements.SENSORS:
                return new SensorsController(getActivity(), this);
            case Elements.AUDIO:
                return new AudioController(getActivity());
            case Elements.GRAPHICS:
                return new GraphicsController(getActivity(), this);
            case Elements.LOCATION:
                return new LocationController(getActivity(), this);
            case Elements.NETWORK:
                return new NetworkController(getActivity());
            case Elements.CELLULAR:
                return new CellularController(getActivity(), this);
            case Elements.WIFI:
                return new WifiController(getActivity(), this);
            case Elements.BLUETOOTH:
                return new BluetoothController(getActivity(), this);
            case Elements.UPTIME:
                return new UptimeController(getActivity(), this);
            case Elements.PLATFORM:
                return new PlatformController(getActivity());
            case Elements.IDENTIFIERS:
                return new IdentifiersController(getActivity());
            case Elements.FEATURES:
                return new FeaturesController(getActivity());
            case Elements.PROPERTIES:
                return new PropertiesController(getActivity());
            case Elements.KEYS:
                return new KeysController(getActivity());
            default:
                return null;
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
