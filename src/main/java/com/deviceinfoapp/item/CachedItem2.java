package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class CachedItem2 extends AbsCachedItem2 {

    public CachedItem2(CharSequence text1, CharSequence text2) {
        super(text1, text2, R.layout.list_item_2);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_ITEM_2;
    }
}
