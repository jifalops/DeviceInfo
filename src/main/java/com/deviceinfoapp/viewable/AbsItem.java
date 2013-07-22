package com.deviceinfoapp.viewable;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsItem implements Item {
    protected boolean mHasChanged;
    protected final int mLayoutRes;

    public AbsItem(int layoutRes) {
        mLayoutRes = layoutRes;
    }

    @Override
    public boolean hasChanged() {
        return mHasChanged;
    }
}
