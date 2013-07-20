package com.deviceinfoapp.controller;

import android.content.Context;
import android.os.Build;

import com.deviceinfoapp.element.AbsElement;
import com.deviceinfoapp.viewable.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsElementController {
    protected static final int API = Build.VERSION.SDK_INT;

    // TODO move to child classes
    public abstract List<Item> getData();

    protected Context mContext;
    protected AbsElement mElement;
    protected final List<Item> mData;

    public AbsElementController(Context context) {
        mContext = context;
        mData = new ArrayList<Item>();
    }

    public AbsElement getElement() {
        return mElement;
    }
}
