package com.deviceinfoapp.controller;

import android.content.Context;
import android.os.Build;

import com.deviceinfoapp.element.Cellular;
import com.deviceinfoapp.element.Graphics;
import com.deviceinfoapp.element.Platform;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

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

        data.add(new Item2("Manufacturer", Build.MANUFACTURER));
        data.add(new Item2("Model", Build.MODEL));
        data.add(new Item2("Device", Build.DEVICE));
        data.add(new Item2("Brand", Build.BRAND));
        data.add(new Item2("Android Name", e.getVersionName(API)));
        data.add(new Item2("Android Version", Build.VERSION.RELEASE));
        data.add(new Item2("Android API Level", String.valueOf(API)));
        data.add(new Item2("Build Display", Build.DISPLAY));
        data.add(new Item2("Build ID", Build.ID));
        data.add(new Item2("Build Incremental Version", Build.VERSION.INCREMENTAL));
        data.add(new Item2("Build Fingerprint", Build.FINGERPRINT));
        data.add(new Item2("OpenGL Version", Graphics.openGlesVersion(mContext)));
        data.add(new Item2("Kernel", e.getKernelVersion()));
        data.add(new Item2("Radio Version", Cellular.getRadioVersion()));
        data.add(new Item2("Radio Interface Version", Cellular.getRilVersion()));
        data.add(new Item2("Baseband", Cellular.getBaseband()));
        data.add(new Item2("Bootloader", Build.BOOTLOADER));
        data.add(new Item2("Hardware", Build.HARDWARE));
        data.add(new Item2("Board", Build.BOARD));

        return data;
    }
}
