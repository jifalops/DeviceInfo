package com.deviceinfoapp.model;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class SubItem1 extends AbsItem1 {
    public SubItem1(CharSequence text) {
        super(text, R.layout.sublist_item_1);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBLIST_1;
    }
}
