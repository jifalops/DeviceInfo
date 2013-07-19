package com.deviceinfoapp.viewable;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsItem implements Item {
    int[] mActions;

    public AbsItem(int[] actions) {
        mActions = actions;
    }

    @Override
    public int[] getActions() {
        return mActions;
    }

    @Override
    public boolean respondsTo(int action) {
        if (mActions == null) return false;
        for (int a : mActions) {
            if (a == action) return true;
        }
        return false;
    }
}
