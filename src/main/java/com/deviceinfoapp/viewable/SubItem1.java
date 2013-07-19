package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class SubItem1 extends AbsItem1 {

    public SubItem1(CharSequence text) {
        this(text, null);
    }

    public SubItem1(CharSequence text, int[] actions) {
        super(text, R.layout.sublist_item_1, actions);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBITEM_1;
    }
}
