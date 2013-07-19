package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class SubItem2 extends AbsItem2 {

    public SubItem2(CharSequence text1, CharSequence text2) {
        this(text1, text2, null);
    }

    public SubItem2(CharSequence text1, CharSequence text2, int[] actions) {
        super(text1, text2, R.layout.sublist_item_2, actions);
    }


    @Override
    public int getViewType() {
        return Item.TYPE_SUBITEM_2;
    }
}
