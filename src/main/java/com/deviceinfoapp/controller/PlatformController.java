package com.deviceinfoapp.controller;

import android.content.Context;
import android.os.Build;

import com.deviceinfoapp.element.Cellular;
import com.deviceinfoapp.element.Graphics;
import com.deviceinfoapp.element.Platform;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class PlatformController extends AbsElementController {

    public PlatformController(Context context) {
        super(context);
        mElement = new Platform(context);
    }

    @Override
    public List<Item> getData() {
        Platform e = (Platform) mElement;

        List<Item> data = new ArrayList<Item>();

        data.add(new ListItem2("Manufacturer", Build.MANUFACTURER));
        data.add(new ListItem2("Model", Build.MODEL));
        data.add(new ListItem2("Device", Build.DEVICE));
        data.add(new ListItem2("Brand", Build.BRAND));
        data.add(new ListItem2("Android Name", e.getVersionName(API)));
        data.add(new ListItem2("Android Version", Build.VERSION.RELEASE));
        data.add(new ListItem2("Android API Level", String.valueOf(API)));
        data.add(new ListItem2("Build Display", Build.DISPLAY));
        data.add(new ListItem2("Build ID", Build.ID));
        data.add(new ListItem2("Build Incremental Version", Build.VERSION.INCREMENTAL));
        data.add(new ListItem2("Build Fingerprint", Build.FINGERPRINT));
        data.add(new ListItem2("OpenGL Version", Graphics.openGlesVersion(mContext)));
        data.add(new ListItem2("Kernel", e.getKernelVersion()));
        data.add(new ListItem2("Radio Version", Cellular.getRadioVersion()));
        data.add(new ListItem2("Radio Interface Version", Cellular.getRilVersion()));
        data.add(new ListItem2("Baseband", Cellular.getBaseband()));
        data.add(new ListItem2("Bootloader", Build.BOOTLOADER));
        data.add(new ListItem2("Hardware", Build.HARDWARE));
        data.add(new ListItem2("Board", Build.BOARD));

        return data;
    }
}
