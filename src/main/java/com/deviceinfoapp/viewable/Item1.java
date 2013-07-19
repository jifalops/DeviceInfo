package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class Item1 extends AbsItem1 {

    public Item1(CharSequence text) {
        this(text, null);
    }

    public Item1(CharSequence text, int[] actions) {
        super(text, R.layout.list_item_1, actions);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_ITEM_1;
    }
}
