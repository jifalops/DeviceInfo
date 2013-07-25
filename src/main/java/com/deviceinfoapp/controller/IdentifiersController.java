package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Identifiers;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem2;

import java.util.ArrayList;
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

        data.add(new ListItem2("Device ID", e.DEVICE_ID));
        data.add(new ListItem2("Device Serial", e.DEVICE_SERIAL));
        data.add(new ListItem2("Android ID", e.ANDROID_ID));
        data.add(new ListItem2("Phone ID", e.PHONE_ID));
        data.add(new ListItem2("SIM Serial", e.SIM_SERIAL));
        data.add(new ListItem2("Line 1 Number", e.LINE_1_NUMBER));
        data.add(new ListItem2("Subscriber ID", e.SUBSCRIBER_ID));

        return data;
    }
}
