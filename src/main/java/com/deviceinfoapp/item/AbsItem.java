package com.deviceinfoapp.item;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsItem implements Item {
    protected final int mLayoutRes;

    public AbsItem(int layoutRes) {
        mLayoutRes = layoutRes;
    }
}
