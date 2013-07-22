package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class Item1 extends AbsItem1 {

    public Item1(CharSequence text) {
        super(text, R.layout.list_item_1);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_ITEM_1;
    }
}
