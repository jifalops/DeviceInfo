package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class Header extends AbsItem1 {

    public Header(CharSequence text) {
        this(text, null);
    }

    public Header(CharSequence text, int[] actions) {
        super(text, R.layout.expandable_header, actions);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_HEADER;
    }
}
