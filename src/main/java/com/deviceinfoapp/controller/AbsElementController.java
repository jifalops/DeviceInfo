package com.deviceinfoapp.controller;

import android.content.Context;
import android.os.Build;

import com.deviceinfoapp.element.AbsElement;
import com.deviceinfoapp.item.Item;

import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsElementController {
    protected static final int API = Build.VERSION.SDK_INT;
    protected boolean mUsesCachedViews;

    public abstract List<Item> getData();

    protected Context mContext;
    protected AbsElement mElement;

    public AbsElementController(Context context) {
        mContext = context;
        mUsesCachedViews = false;
    }

    public AbsElement getElement() {
        return mElement;
    }

    public boolean usesCachedViews() {
        return mUsesCachedViews;
    }
}
