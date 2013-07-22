package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class DrawerHeader extends AbsItem1 {

    public DrawerHeader(CharSequence text) {
        super(text, R.layout.drawer_header);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_DRAWER_HEADER;
    }
}
