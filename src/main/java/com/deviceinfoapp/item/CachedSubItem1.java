package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class CachedSubItem1 extends AbsCachedItem1 {

    public CachedSubItem1(CharSequence text) {
        super(text, R.layout.sublist_item_1);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBITEM_1;
    }
}
