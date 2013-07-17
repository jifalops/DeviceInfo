package com.deviceinfoapp.model;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class ListItem2 extends AbsItem2 {
    public ListItem2(CharSequence text1, CharSequence text2) {
        super(text1, text2, R.layout.list_item_2);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_LIST_2;
    }
}
