package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class ExpandableCachedHeader extends AbsCachedItem1 {

    public ExpandableCachedHeader(CharSequence text) {
        super(text, R.layout.expandable_header);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_HEADER;
    }
}
