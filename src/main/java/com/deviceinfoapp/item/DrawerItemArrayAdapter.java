package com.deviceinfoapp.item;

import android.content.Context;

import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class DrawerItemArrayAdapter extends ItemArrayAdapter {
    public DrawerItemArrayAdapter(Context context, List<Item> items) {
        super(context, items);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != Item.TYPE_HEADER;
    }
}
