package com.deviceinfoapp.item;

/**
 * Created by Jake on 7/17/13.
 */
public interface ListItem extends Item {
    /**
     * Whether the item's data has changed since the last call to getView().
     */
    boolean hasChanged();

    /**
     * Set to false after updating a corresponding View's data.
     */
    void setHasChanged(boolean changed);
}
