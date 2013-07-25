package com.deviceinfoapp.item;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class AbsListItem extends AbsItem implements ListItem {
    protected boolean mHasChanged;

    public AbsListItem(int layoutRes) {
        super(layoutRes);
    }

    @Override
    public boolean hasChanged() {
        return mHasChanged;
    }

    @Override
    public void setHasChanged(boolean changed) {
        mHasChanged = changed;
    }
}
