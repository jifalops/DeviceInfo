package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class Header extends AbsItem1 {

    public Header(CharSequence text) {
        super(text, R.layout.expandable_header);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_HEADER;
    }
}
