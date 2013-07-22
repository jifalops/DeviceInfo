package com.deviceinfoapp.item;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Jake on 7/17/13.
 */
public interface Item {
    int TYPE_HEADER = 0;
    int TYPE_ITEM_1 = 1;
    int TYPE_ITEM_2 = 2;
    int TYPE_SUBHEADER = 3;
    int TYPE_SUBITEM_1 = 4;
    int TYPE_SUBITEM_2 = 5;
    int TYPE_DRAWER_HEADER = 6;
    int TYPE_DRAWER_ITEM = 7;

    // A count of the above items
    int VIEW_TYPE_COUNT = 8;

    int getViewType();
    View getView(LayoutInflater inflater, View convertView);

    /**
     * Whether the item's data has changed since the last call to getView().
     */
    boolean hasChanged();
}
