package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class CachedSubItem2 extends AbsCachedItem2 {

    public CachedSubItem2(CharSequence text1, CharSequence text2) {
        super(text1, text2, R.layout.sublist_item_2);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBITEM_2;
    }
}
