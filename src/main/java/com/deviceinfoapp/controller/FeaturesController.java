package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Features;
import com.deviceinfoapp.item.ExpandableListHeader;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class FeaturesController extends AbsElementController {

    public FeaturesController(Context context) {
        super(context);
        mElement = new Features(context);
    }

    @Override
    public List<Item> getData() {
        Features e = (Features) mElement;

        List<Item> data = new ArrayList<Item>();

        int size = e.getAvailableFeatures().size();
        data.add(new ExpandableListHeader("Available Features (" + size + ")"));
        for (int i = 0; i < e.getAvailableFeatures().size(); ++i) {
            data.add(new ListItem1(e.getAvailableFeatures().get(i)));
        }

        size = e.getUnavailableFeatures().size();
        data.add(new ExpandableListHeader("Unavailable Features (" + size + ")"));
        for (int i = 0; i < e.getUnavailableFeatures().size(); ++i) {
            data.add(new ListItem1(e.getUnavailableFeatures().get(i)));
        }

        return data;
    }
}
