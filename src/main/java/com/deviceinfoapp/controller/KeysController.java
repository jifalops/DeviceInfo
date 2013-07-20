package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.R;
import com.deviceinfoapp.element.Keys;
import com.deviceinfoapp.element.UnavailableFeatureException;
import com.deviceinfoapp.viewable.Header;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class KeysController extends AbsElementController {

    public KeysController(Context context) {
        super(context);
        try {
            mElement = new Keys(context);
        } catch (UnavailableFeatureException e) {
            mElement = null;
        }
    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        if (mElement == null) {
            data.add(new Item1(mContext.getString(R.string.unavailable_feature)));
            return data;
        }

        Keys e = (Keys) mElement;

        int key;
        int size = e.getAvailableKeys().size();

        data.add(new Header("Available Keys (" + size + ")"));

        for (int i = 0; i < size; ++i) {
            key = e.getAvailableKeys().get(i);
            data.add(new Item1(e.getKeyName(key) + " (" + key + ")"));
        }

        size = e.getUnavailableKeys().size();
        data.add(new Header("Unavailable Keys (" + size + ")"));
        for (int i = 0; i < size; ++i) {
            key = e.getUnavailableKeys().get(i);
            data.add(new Item1(e.getKeyName(key) + " (" + key + ")"));
        }

        return data;
    }
}
