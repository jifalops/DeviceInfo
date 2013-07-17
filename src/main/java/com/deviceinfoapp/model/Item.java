package com.deviceinfoapp.model;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Jake on 7/17/13.
 */
public interface Item {
    int TYPE_HEADER = 0;
    int TYPE_LIST_1 = 1;
    int TYPE_LIST_2 = 2;
    int TYPE_SUBHEADER = 3;
    int TYPE_SUBLIST_1 = 4;
    int TYPE_SUBLIST_2 = 5;

    // A count of the above items
    int VIEW_TYPE_COUNT = 6;

    int getViewType();
    View getView(LayoutInflater inflater, View convertView);
}
