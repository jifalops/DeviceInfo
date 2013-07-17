package com.deviceinfoapp.model;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class ListItem1 extends AbsItem1 {
    public ListItem1(CharSequence text) {
        super(text, R.layout.list_item_1);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_LIST_1;
    }
}
