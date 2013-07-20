package com.deviceinfoapp.controller;

import android.content.Context;
import android.text.TextUtils;

import com.deviceinfoapp.element.Audio;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.ArrayList;
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
        List<Item> data = new ArrayList<Item>();
        
        data.add(new Item2("Mode", e.getMode()));
        data.add(new Item2("Ringer Mode", e.getRingerMode()));
        data.add(new Item2("System Volume", e.getSystemVolume() + "/" + e.getSystemVolumeMax()));
        data.add(new Item2("Ring Volume", e.getRingVolume() + "/" + e.getRingVolumeMax()));
        data.add(new Item2("Call Volume", e.getCallVolume() + "/" + e.getCallVolumeMax()));
        data.add(new Item2("Music Volume", e.getMusicVolume() + "/" + e.getMusicVolumeMax()));
        data.add(new Item2("Alarm Volume", e.getAlarmVolume() + "/" + e.getAlarmVolumeMax()));
        data.add(new Item2("DTMF Volume", e.getDtmfVolume() + "/" + e.getDtmfVolumeMax()));
        data.add(new Item2("Notification Volume", e.getNotificationVolume() + "/" + e.getNotificationVolumeMax()));
        data.add(new Item2("Ringer Vibrate", e.getRingerVibrateSetting()));
        data.add(new Item2("Notification Vibrate", e.getNotificationVibrateSetting()));
        data.add(new Item2("Bluetooth A2DP On", String.valueOf(e.isBluetoothA2dpOn())));
        data.add(new Item2("Bluetooth SCO On", String.valueOf(e.isBluetoothScoOn())));
        data.add(new Item2("Bluetooth SCO Available Off Call", String.valueOf(e.isBluetoothScoAvailableOffCall())));
        data.add(new Item2("Microphone Muted", String.valueOf(e.isMicrophoneMute())));
        data.add(new Item2("Music Active", String.valueOf(e.isMusicActive())));
        data.add(new Item2("Speakerphone On", String.valueOf(e.isSpeakerphoneOn())));
        data.add(new Item2("Wired Headset Connected", String.valueOf(e.isWiredHeadsetConnected())));
        data.add(new Item2("Supported Formats", TextUtils.join(", ", e.getSupportedFormats())));
        data.add(new Item2("Supported Sources", TextUtils.join(", ", e.getSupportedSources())));
        return data;
    }
}
