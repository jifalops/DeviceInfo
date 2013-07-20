package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Properties;
import com.deviceinfoapp.util.ShellHelper;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jake on 7/18/13.
 */
public class PropertiesController extends AbsElementController {

    public PropertiesController(Context context) {
        super(context);
        mElement = new Properties(context);
    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        LinkedHashMap<String, String> map = ShellHelper.getProp();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            data.add(new Item2(entry.getKey(), entry.getValue()));
        }

        return data;
    }
}
