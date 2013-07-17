package com.deviceinfoapp.model;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class SubHeader extends AbsItem1 {
    public SubHeader(CharSequence text) {
        super(text, R.layout.subheader_item);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBHEADER;
    }
}
