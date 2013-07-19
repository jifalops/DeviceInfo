package com.deviceinfoapp.viewable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import com.deviceinfoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/17/13.
 */
public class ItemExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private GroupedItems mGroupedItems;

    public ItemExpandableListAdapter(Context context, List<Item> items) {
        mInflater = LayoutInflater.from(context);
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
        if (groupPosition == 0) {
            return mInflater.inflate(R.layout.empty_view, null);
        } else {
            View v = ((Item) getGroup(groupPosition)).getView(mInflater, convertView);
            int res = isExpanded
                    ? R.drawable.collapse
                    : R.drawable.expand;
            ((ImageView) v.findViewById(R.id.expandCollapse)).setImageResource(res);
            return v;
        }
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


        public GroupedItems(List<Item> items) {
            mItems = items;

            List<Integer> groups = findGroups();
            int size = groups.size();

            mGroups = new int[size + 1][2];
            mGroups[0][POSITION] = 0;

            if (size == 0) {
                mGroups[0][CHILDREN] = mItems.size();
            } else {
                int pos = groups.get(0);
                mGroups[0][CHILDREN] = pos;
                mGroups[1][POSITION] = pos;
                for (int i = 1; i < size; ++i) {
                    pos = groups.get(i);
                    mGroups[i + 1][POSITION] = pos;
                    mGroups[i][CHILDREN] = pos - mGroups[i][POSITION] - 1;
                }
                mGroups[size][CHILDREN] = mItems.size() - pos - 1;
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

        public int getGroupCount() {
            return mGroups.length;
        }

        public int getChildrenCount(int groupPosition) {
            return mGroups[groupPosition][CHILDREN];
        }

        public Object getGroup(int groupPosition) {
            return mItems.get(mGroups[groupPosition][POSITION]);
        }

        public Object getChild(int groupPosition, int childPosition) {
            int pos;
            if (groupPosition == 0) {
                pos = childPosition;
            } else {
                pos = mGroups[groupPosition][POSITION] + childPosition + 1;
            }
            return mItems.get(pos);
        }

        public long getGroupId(int groupPosition) {
            return mGroups[groupPosition][POSITION];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return mGroups[groupPosition][POSITION] + childPosition + 1;
        }
    }
}
