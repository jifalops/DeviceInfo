package com.deviceinfoapp.controller;

import android.content.Context;
import android.text.TextUtils;

import com.deviceinfoapp.element.Audio;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class AudioController extends AbsElementController {

    public AudioController(Context context) {
        super(context);
        mElement = new Audio(context);
    }

    @Override
    public List<Item> getData() {
        Audio e = (Audio) mElement;
        mData.clear();
        mData.add(new Item2("Mode", e.getMode()));
        mData.add(new Item2("Ringer Mode", e.getRingerMode()));
        mData.add(new Item2("System Volume", e.getSystemVolume() + "/" + e.getSystemVolumeMax()));
        mData.add(new Item2("Ring Volume", e.getRingVolume() + "/" + e.getRingVolumeMax()));
        mData.add(new Item2("Call Volume", e.getCallVolume() + "/" + e.getCallVolumeMax()));
        mData.add(new Item2("Music Volume", e.getMusicVolume() + "/" + e.getMusicVolumeMax()));
        mData.add(new Item2("Alarm Volume", e.getAlarmVolume() + "/" + e.getAlarmVolumeMax()));
        mData.add(new Item2("DTMF Volume", e.getDtmfVolume() + "/" + e.getDtmfVolumeMax()));
        mData.add(new Item2("Notification Volume", e.getNotificationVolume() + "/" + e.getNotificationVolumeMax()));
        mData.add(new Item2("Ringer Vibrate", e.getRingerVibrateSetting()));
        mData.add(new Item2("Notification Vibrate", e.getNotificationVibrateSetting()));
        mData.add(new Item2("Bluetooth A2DP On", String.valueOf(e.isBluetoothA2dpOn())));
        mData.add(new Item2("Bluetooth SCO On", String.valueOf(e.isBluetoothScoOn())));
        mData.add(new Item2("Bluetooth SCO Available Off Call", String.valueOf(e.isBluetoothScoAvailableOffCall())));
        mData.add(new Item2("Microphone Muted", String.valueOf(e.isMicrophoneMute())));
        mData.add(new Item2("Music Active", String.valueOf(e.isMusicActive())));
        mData.add(new Item2("Speakerphone On", String.valueOf(e.isSpeakerphoneOn())));
        mData.add(new Item2("Wired Headset Connected", String.valueOf(e.isWiredHeadsetConnected())));
        mData.add(new Item2("Supported Formats", TextUtils.join(", ", e.getSupportedFormats())));
        mData.add(new Item2("Supported Sources", TextUtils.join(", ", e.getSupportedSources())));
        return mData;
    }
}
