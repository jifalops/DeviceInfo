package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class CachedItem1 extends AbsCachedItem1 {

    public CachedItem1(CharSequence text) {
        super(text, R.layout.list_item_1);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_ITEM_1;
    }
}
