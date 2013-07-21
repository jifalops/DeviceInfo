package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Ram;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jake on 7/18/13.
 */
public class RamController extends ActiveElementController implements Ram.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onRamUpdated(LinkedHashMap<String, String> meminfo);
    }

    public RamController(Context context, RamController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Ram(context, this);
    }

    @Override
    public List<Item> getData() {
        Ram e = (Ram) mElement;
        int[] actions = new int[] {Ram.ACTION_UPDATE};

        List<Item> data = new ArrayList<Item>();

        Map<String, String> map = e.getMeminfo();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            data.add(new Item2(entry.getKey(), entry.getValue(), actions));
        }

        return data;
    }

    @Override
    public void onUpdated(LinkedHashMap<String, String> meminfo) {
        ((Callbacks) mCallbacks).onRamUpdated(meminfo);
    }
}
