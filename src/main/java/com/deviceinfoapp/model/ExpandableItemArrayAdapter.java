package com.deviceinfoapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/17/13.
 */
public class ExpandableItemArrayAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private GroupedItems mGroupedItems;

    public ExpandableItemArrayAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setItems(List<Item> items) {
        mGroupedItems = new GroupedItems(items);
    }

    @Override
    public int getGroupCount() {
        return mGroupedItems.getGroupCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupedItems.getChildrenCount(groupPosition);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupedItems.getGroup(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupedItems.getChild(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mGroupedItems.getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mGroupedItems.getChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return ((Item) getGroup(groupPosition)).getView(mInflater, convertView);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return ((Item) getChild(groupPosition, childPosition)).getView(mInflater, convertView);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class GroupedItems {
        private static final int POSITION = 0;
        private static final int CHILDREN = 1;

        private List<Item> mItems;
        private int[][] mGroups;
        private int mUngroupedItems;


        public GroupedItems(List<Item> items) {
            mItems = items;

            List<Integer> groups = findGroups();
            int size = groups.size();

            mGroups = new int[size][2];

            if (size == 0) {
                mUngroupedItems = mItems.size();
            } else {
                mUngroupedItems = groups.get(0);
                populateGroups(groups);
            }
        }

        private List<Integer> findGroups() {
            List<Integer> groups = new ArrayList<Integer>();
            int i = 0;
            for (Item item : mItems) {
                if (item.getViewType() == Item.TYPE_HEADER) {
                    groups.add(i);
                }
                ++i;
            }
            return groups;
        }

        private void populateGroups(List<Integer> groups) {
            int size = mGroups.length;
            int pos = groups.get(0);
            mGroups[0][POSITION] = pos;
            for (int i = 1; i < size; ++i) {
                pos = groups.get(i);
                mGroups[i][POSITION] = pos;
                mGroups[i - 1][CHILDREN] = pos - mGroups[i - 1][POSITION] - 1;
            }
            mGroups[size - 1][CHILDREN] = mItems.size() - pos - 1;
        }

        public int getUngroupedItemCount() {
            return mUngroupedItems;
        }

        public int getGroupCount() {
            return mGroups.length;
        }

        public int getChildrenCount(int groupPosition) {
            return mGroups[groupPosition][CHILDREN];
        }

        public Object getGroup(int groupPosition) {
            return (Header) mItems.get(mGroups[groupPosition][POSITION]);
        }

        public Object getChild(int groupPosition, int childPosition) {
            return mItems.get(mGroups[groupPosition][POSITION] + childPosition + 1);
        }

        public long getGroupId(int groupPosition) {
            return mGroups[groupPosition][POSITION];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return mGroups[groupPosition][POSITION] + childPosition + 1;
        }
    }
}
