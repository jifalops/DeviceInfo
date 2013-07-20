package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Display;
import com.deviceinfoapp.element.Features;
import com.deviceinfoapp.element.Identifiers;
import com.deviceinfoapp.viewable.Header;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item1;
import com.deviceinfoapp.viewable.Item2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class IdentifiersController extends AbsElementController {

    public IdentifiersController(Context context) {
        super(context);
        mElement = new Identifiers(context);
    }

    @Override
    public List<Item> getData() {
        Identifiers e = (Identifiers) mElement;

        List<Item> data = new ArrayList<Item>();

        data.add(new Item2("Device ID", e.DEVICE_ID));
        data.add(new Item2("Device Serial", e.DEVICE_SERIAL));
        data.add(new Item2("Android ID", e.ANDROID_ID));
        data.add(new Item2("Phone ID", e.PHONE_ID));
        data.add(new Item2("SIM Serial", e.SIM_SERIAL));
        data.add(new Item2("Line 1 Number", e.LINE_1_NUMBER));
        data.add(new Item2("Subscriber ID", e.SUBSCRIBER_ID));

        return data;
    }
}