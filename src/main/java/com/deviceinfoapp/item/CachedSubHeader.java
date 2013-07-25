package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class CachedSubHeader extends AbsCachedItem1 {

    public CachedSubHeader(CharSequence text) {
        super(text, R.layout.subheader_item);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_SUBHEADER;
    }
}
