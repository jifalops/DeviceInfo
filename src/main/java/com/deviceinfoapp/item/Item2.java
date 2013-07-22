package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class Item2 extends AbsItem2 {

    public Item2(CharSequence text1, CharSequence text2) {
        super(text1, text2, R.layout.list_item_2);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_ITEM_2;
    }
}
