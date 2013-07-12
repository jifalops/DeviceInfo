package com.deviceinfoapp.util;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/12/13.
 *
 * For use with a {@link PinnedHeaderExpandableListView}.
 * Allows for multiple strings within each item.
 */
public class GroupedListItems {
    private List<Pair<CharSequence, List<Pair<CharSequence, CharSequence>>>> mData;
    //private String[][][] mData;
    private int mGroupId, mItemId, mChildId;

    public GroupedListItems() {
        //mData = new String[groups][items][children];
        mData = new ArrayList<Pair<CharSequence, List<Pair<CharSequence, CharSequence>>>>();
    }

    public void addGroup(CharSequence group) {
        mData.add(new Pair<CharSequence, List<Pair<CharSequence, CharSequence>>>(
                group, new ArrayList<Pair<CharSequence, CharSequence>>()));
    }

    public List<Pair<CharSequence, CharSequence>> getGroup(int groupIndex) {
        return mData.get(groupIndex).second;
    }

    public void addItem(CharSequence key, CharSequence value) {
        addItem(mData.size() - 1, key, value);
    }

    public void addItem(int groupId, CharSequence key, CharSequence value) {
        mData.get(groupId).second.add(new Pair<CharSequence, CharSequence>(key, value));
    }

    public Pair<CharSequence, CharSequence> getItem(int groupId, int itemId) {
        return mData.get(groupId).second.get(itemId);
    }

    public int size() {
        return mData.size();
    }

    public CharSequence getGroupName(int groupId) {
        return mData.get(groupId).first;
    }
}
