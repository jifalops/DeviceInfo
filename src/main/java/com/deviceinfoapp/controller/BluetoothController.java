package com.deviceinfoapp.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.deviceinfoapp.R;
import com.deviceinfoapp.element.Bluetooth;
import com.deviceinfoapp.element.UnavailableFeatureException;
import com.deviceinfoapp.item.ExpandableListHeader;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem1;
import com.deviceinfoapp.item.ListItem2;
import com.deviceinfoapp.item.ListSubHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jake on 7/18/13.
 */
public class BluetoothController extends ActiveElementController implements Bluetooth.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onServiceConnected(int profile, BluetoothProfile proxy);
        void onServiceDisconnected(int profile);
    }

    public BluetoothController(Context context, BluetoothController.Callbacks callbacks) {
        super(context, callbacks);
        try {
            mElement = new Bluetooth(context, this);
        } catch (UnavailableFeatureException e) {
            mElement = null;
            return;
        }


    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        if (mElement == null) {
            data.add(new ListItem1(mContext.getString(R.string.unavailable_feature)));
            return data;
        }

        Bluetooth bt = (Bluetooth) mElement;
        BluetoothAdapter ba = bt.getBluetoothAdapter();



        // BluetoothAdapter
        String address = ba.getAddress();
        boolean isValid = BluetoothAdapter.checkBluetoothAddress(address);
        data.add(new ExpandableListHeader("Adapter"));
        data.add(new ListItem2("MAC Address", address));
        data.add(new ListItem2("MAC Is Valid", String.valueOf(isValid)));
        data.add(new ListItem2("Name", ba.getName()));
        data.add(new ListItem2("Scan Mode", bt.getScanMode()));
        data.add(new ListItem2("Adapter State", bt.getAdapterState()));
        data.add(new ListItem2("Is Discovering", String.valueOf(ba.isDiscovering())));
        data.add(new ListItem2("Is Enabled", String.valueOf(ba.isEnabled())));

        if (API >= 14) {
            data.add(new ListItem2("A2DP Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.A2DP))));
            data.add(new ListItem2("Headset Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.HEADSET))));
            data.add(new ListItem2("Health Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.HEALTH))));
        }
        if (API >= 11 && bt.getA2dpProfile() == null) data.add(new ListItem2("Local A2DP Profile", null));
        if (API >= 11 && bt.getHeadsetProfile() == null) data.add(new ListItem2("Local Headset Profile", null));
        if (API >= 14 && bt.getHealthProfile() == null) data.add(new ListItem2("Local Health Profile", null));

        // BluetoothDevice
        if (isValid) {
            BluetoothDevice dev = ba.getRemoteDevice(address);
            if (dev == null) data.add(new ListItem2("Adapter Device", null));
            else {
                addBluetoothDeviceContents(bt, dev, data);
            }
        }

        Set<BluetoothDevice> devs = ba.getBondedDevices();
        data.add(new ExpandableListHeader("Devices"));
        if (devs == null || devs.isEmpty()) data.add(new ListItem1("Bonded Devices"));
        else {
            int i = 0;
            for (BluetoothDevice d : devs) {
                data.add(new ListSubHeader("Device " + i + 1));
                addBluetoothDeviceContents(bt, d, data);
                ++i;
            }
        }

        return data;
    }

    private void addBluetoothDeviceContents(Bluetooth bt, BluetoothDevice device, List<Item> data) {
        if (device == null) return;


        String address = device.getAddress();
        data.add(new ListItem2("Address", address));
        data.add(new ListItem2("Address Valid", String.valueOf(BluetoothAdapter.checkBluetoothAddress(address))));
        data.add(new ListItem2("Bond State", bt.getBondState(device.getBondState())));
        data.add(new ListItem2("Name", device.getName()));
        // BluetoothClass
        BluetoothClass btclass = device.getBluetoothClass();
        if (btclass == null) data.add(new ListItem2("BluetoothClass", null));
        else {
            data.add(new ListItem2("Major Class", bt.getDeviceMajorType(btclass.getMajorDeviceClass())));
            data.add(new ListItem2("Minor Class", bt.getDeviceType(btclass.getDeviceClass())));
            data.add(new ListItem2("Has Service Audio", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.AUDIO))));
            data.add(new ListItem2("Has Service Capture", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.CAPTURE))));
            data.add(new ListItem2("Has Service Information", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.INFORMATION))));
            data.add(new ListItem2("Has Service Limited Discoverability", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.LIMITED_DISCOVERABILITY))));
            data.add(new ListItem2("Has Service Networking", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.NETWORKING))));
            data.add(new ListItem2("Has Service Object Transfer", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.OBJECT_TRANSFER))));
            data.add(new ListItem2("Has Service Positioning", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.POSITIONING))));
            data.add(new ListItem2("Has Service Render", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.RENDER))));
            data.add(new ListItem2("Has Service Telephony", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.TELEPHONY))));
        }
        if (API >= 11 && bt.getA2dpProfile() != null) {
            data.add(new ListItem2("A2DP Profile Connection State", bt.getProfileState(bt.getA2dpProfile().getConnectionState(device))));
            // I reported bug, issue 29394
//			mData.add(new ListItem2("A2DP Profile Is Playing", String.valueOf(mA2dpProfile.isA2dpPlaying(device))));
        }
        if (API >= 11 && bt.getHeadsetProfile() != null) {
            data.add(new ListItem2("Headset Profile Connection State", bt.getProfileState(bt.getHeadsetProfile().getConnectionState(device))));
            data.add(new ListItem2("Headset Profile Is Audio Connected", String.valueOf(bt.getHeadsetProfile().isAudioConnected(device))));
        }
        if (API >= 14 && bt.getHealthProfile() != null) {
            data.add(new ListItem2("Health Profile Connection State", bt.getProfileState(bt.getHealthProfile().getConnectionState(device))));
        }
    }


    @Override
    public void start() {
        ((Bluetooth) mElement).start();
    }

    @Override
    public void stop() {
        ((Bluetooth) mElement).stop();
    }




    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        ((Callbacks) mCallbacks).onServiceConnected(profile, proxy);
    }

    @Override
    public void onServiceDisconnected(int profile) {
        ((Callbacks) mCallbacks).onServiceDisconnected(profile);
    }

}
