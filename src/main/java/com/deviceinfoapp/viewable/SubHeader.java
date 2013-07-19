package com.deviceinfoapp.viewable;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class SubHeader extends AbsItem1 {

    public SubHeader(CharSequence text) {
        this(text, null);
    }

    public SubHeader(CharSequence text, int[] actions) {
        super(text, R.layout.subheader_item, actions);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBHEADER;
    }
}
