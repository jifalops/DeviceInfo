package com.deviceinfoapp.controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.deviceinfoapp.element.Battery;
import com.deviceinfoapp.element.Bluetooth;
import com.deviceinfoapp.element.UnavailableFeatureException;
import com.deviceinfoapp.viewable.Item;

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
    public boolean isActionable() {
        return mElement != null;
    }

    @Override
    public List<Item> getData() {
        Bluetooth e = (Bluetooth) mElement;
        mData.clear();

        // BluetoothAdapter
        String address = mBluetoothAdapter.getAddress();
        boolean isValid = BluetoothAdapter.checkBluetoothAddress(address);
        contents.put("MAC Address", address);
        contents.put("MAC Is Valid", String.valueOf(isValid));
        contents.put("Name", mBluetoothAdapter.getName());
        contents.put("Scan Mode", getScanMode());
        contents.put("Adapter State", getAdapterState());
        contents.put("Is Discovering", String.valueOf(mBluetoothAdapter.isDiscovering()));
        contents.put("Is Enabled", String.valueOf(mBluetoothAdapter.isEnabled()));

        if (API >= 14) {
            contents.put("A2DP Profile Connection State", getProfileState(
                    mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP)));
            contents.put("Headset Profile Connection State", getProfileState(
                    mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)));
            contents.put("Health Profile Connection State", getProfileState(
                    mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH)));
        }
        if (API >= 11 && mA2dpProfile == null) contents.put("Local A2DP Profile", null);
        if (API >= 11 && mHeadsetProfile == null) contents.put("Local Headset Profile", null);
        if (API >= 14 && mHealthProfile == null) contents.put("Local Health Profile", null);

        // BluetoothDevice
        if (isValid) {
            BluetoothDevice dev = mBluetoothAdapter.getRemoteDevice(address);
            if (dev == null) contents.put("Adapter Device", null);
            else {
                LinkedHashMap<String, String> subcontents = getBluetoothDeviceContents(dev);
                for (Map.Entry<String, String> e : subcontents.entrySet()) {
                    contents.put("Adapter Device " + e.getKey(), e.getValue());
                }
            }
        }

        Set<BluetoothDevice> devs = mBluetoothAdapter.getBondedDevices();
        if (devs == null || devs.isEmpty()) contents.put("Bonded Devices", null);
        else {
            LinkedHashMap<String, String> subcontents;
            int i = 0;
            for (BluetoothDevice d : devs) {
                subcontents = getBluetoothDeviceContents(d);
                for (Map.Entry<String, String> e : subcontents.entrySet()) {
                    contents.put("Device " + i + " " + e.getKey(), e.getValue());
                }
                ++i;
            }
        }

        return contents;
    }

    private LinkedHashMap<String, String> getBluetoothDeviceContents(BluetoothDevice device) {
        if (device == null) return null;
        LinkedHashMap<String, String> contents = super.getContents();

        String address = device.getAddress();
        contents.put("Address", address);
        contents.put("Address Valid", String.valueOf(BluetoothAdapter.checkBluetoothAddress(address)));
        contents.put("Bond State", getBondState(device.getBondState()));
        contents.put("Name", device.getName());
        // BluetoothClass
        BluetoothClass btclass = device.getBluetoothClass();
        if (btclass == null) contents.put("BluetoothClass", null);
        else {
            contents.put("Major Class", getDeviceMajorType(btclass.getMajorDeviceClass()));
            contents.put("Minor Class", getDeviceType(btclass.getDeviceClass()));
            contents.put("Has Service Audio", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.AUDIO)));
            contents.put("Has Service Capture", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.CAPTURE)));
            contents.put("Has Service Information", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.INFORMATION)));
            contents.put("Has Service Limited Discoverability", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.LIMITED_DISCOVERABILITY)));
            contents.put("Has Service Networking", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.NETWORKING)));
            contents.put("Has Service Object Transfer", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.OBJECT_TRANSFER)));
            contents.put("Has Service Positioning", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.POSITIONING)));
            contents.put("Has Service Render", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.RENDER)));
            contents.put("Has Service Telephony", String.valueOf(
                    btclass.hasService(BluetoothClass.Service.TELEPHONY)));
        }
        if (API >= 11 && mA2dpProfile != null) {
            contents.put("A2DP Profile Connection State", getProfileState(mA2dpProfile.getConnectionState(device)));
            // I reported bug, issue 29394
//			contents.put("A2DP Profile Is Playing", String.valueOf(mA2dpProfile.isA2dpPlaying(device)));
        }
        if (API >= 11 && mHeadsetProfile != null) {
            contents.put("Headset Profile Connection State", getProfileState(mHeadsetProfile.getConnectionState(device)));
            contents.put("Headset Profile Is Audio Connected", String.valueOf(mHeadsetProfile.isAudioConnected(device)));
        }
        if (API >= 14 && mHealthProfile != null) {
            contents.put("Health Profile Connection State", getProfileState(mHealthProfile.getConnectionState(device)));
        }

        return contents;
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
