package com.deviceinfoapp.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.deviceinfoapp.element.Bluetooth;
import com.deviceinfoapp.element.UnavailableFeatureException;
import com.deviceinfoapp.viewable.Header;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item1;
import com.deviceinfoapp.viewable.Item2;
import com.deviceinfoapp.viewable.SubHeader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        }
    }

    @Override
    public List<Item> getData() {
        Bluetooth bt = (Bluetooth) mElement;
        BluetoothAdapter ba = bt.getBluetoothAdapter();
        mData.clear();

        // BluetoothAdapter
        String address = ba.getAddress();
        boolean isValid = BluetoothAdapter.checkBluetoothAddress(address);
        mData.add(new Header("Adapter"));
        mData.add(new Item2("MAC Address", address));
        mData.add(new Item2("MAC Is Valid", String.valueOf(isValid)));
        mData.add(new Item2("Name", ba.getName()));
        mData.add(new Item2("Scan Mode", bt.getScanMode()));
        mData.add(new Item2("Adapter State", bt.getAdapterState()));
        mData.add(new Item2("Is Discovering", String.valueOf(ba.isDiscovering())));
        mData.add(new Item2("Is Enabled", String.valueOf(ba.isEnabled())));

        if (API >= 14) {
            mData.add(new Item2("A2DP Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.A2DP))));
            mData.add(new Item2("Headset Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.HEADSET))));
            mData.add(new Item2("Health Profile Connection State", bt.getProfileState(
                    ba.getProfileConnectionState(BluetoothProfile.HEALTH))));
        }
        if (API >= 11 && bt.getA2dpProfile() == null) mData.add(new Item2("Local A2DP Profile", null));
        if (API >= 11 && bt.getHeadsetProfile() == null) mData.add(new Item2("Local Headset Profile", null));
        if (API >= 14 && bt.getHealthProfile() == null) mData.add(new Item2("Local Health Profile", null));

        // BluetoothDevice
        if (isValid) {
            BluetoothDevice dev = ba.getRemoteDevice(address);
            if (dev == null) mData.add(new Item2("Adapter Device", null));
            else {
                addBluetoothDeviceContents(bt, dev);
            }
        }

        Set<BluetoothDevice> devs = ba.getBondedDevices();
        mData.add(new Header("Devices"));
        if (devs == null || devs.isEmpty()) mData.add(new Item1("Bonded Devices"));
        else {
            int i = 0;
            for (BluetoothDevice d : devs) {
                mData.add(new SubHeader("Device " + i + 1));
                addBluetoothDeviceContents(bt, d);
                ++i;
            }
        }

        return mData;
    }

    private void addBluetoothDeviceContents(Bluetooth bt, BluetoothDevice device) {
        if (device == null) return;


        String address = device.getAddress();
        mData.add(new Item2("Address", address));
        mData.add(new Item2("Address Valid", String.valueOf(BluetoothAdapter.checkBluetoothAddress(address))));
        mData.add(new Item2("Bond State", bt.getBondState(device.getBondState())));
        mData.add(new Item2("Name", device.getName()));
        // BluetoothClass
        BluetoothClass btclass = device.getBluetoothClass();
        if (btclass == null) mData.add(new Item2("BluetoothClass", null));
        else {
            mData.add(new Item2("Major Class", bt.getDeviceMajorType(btclass.getMajorDeviceClass())));
            mData.add(new Item2("Minor Class", bt.getDeviceType(btclass.getDeviceClass())));
            mData.add(new Item2("Has Service Audio", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.AUDIO))));
            mData.add(new Item2("Has Service Capture", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.CAPTURE))));
            mData.add(new Item2("Has Service Information", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.INFORMATION))));
            mData.add(new Item2("Has Service Limited Discoverability", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.LIMITED_DISCOVERABILITY))));
            mData.add(new Item2("Has Service Networking", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.NETWORKING))));
            mData.add(new Item2("Has Service Object Transfer", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.OBJECT_TRANSFER))));
            mData.add(new Item2("Has Service Positioning", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.POSITIONING))));
            mData.add(new Item2("Has Service Render", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.RENDER))));
            mData.add(new Item2("Has Service Telephony", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.TELEPHONY))));
        }
        if (API >= 11 && bt.getA2dpProfile() != null) {
            mData.add(new Item2("A2DP Profile Connection State", bt.getProfileState(bt.getA2dpProfile().getConnectionState(device))));
            // I reported bug, issue 29394
//			mData.add(new Item2("A2DP Profile Is Playing", String.valueOf(mA2dpProfile.isA2dpPlaying(device))));
        }
        if (API >= 11 && bt.getHeadsetProfile() != null) {
            mData.add(new Item2("Headset Profile Connection State", bt.getProfileState(bt.getHeadsetProfile().getConnectionState(device))));
            mData.add(new Item2("Headset Profile Is Audio Connected", String.valueOf(bt.getHeadsetProfile().isAudioConnected(device))));
        }
        if (API >= 14 && bt.getHealthProfile() != null) {
            mData.add(new Item2("Health Profile Connection State", bt.getProfileState(bt.getHealthProfile().getConnectionState(device))));
        }
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
