package com.deviceinfoapp.item;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public class ExpandableListHeader extends AbsListItem1 {

    public ExpandableListHeader(CharSequence text) {
        super(text, R.layout.expandable_header);
    }

    @Override
    public int getViewType() {
        return Item.TYPE_HEADER;
    }
}
