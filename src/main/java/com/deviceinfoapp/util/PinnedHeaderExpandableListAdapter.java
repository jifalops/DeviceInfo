package com.deviceinfoapp.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * A simple adapter which maintains an ArrayList of photo resource Ids.
 * Each photo is displayed as an image. This adapter supports clearing the
 * list of photos and adding a new photo.
 *
 */
public class PinnedHeaderExpandableListAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.PinnedHeaderAdapter, AbsListView.OnScrollListener {
    private GroupedListItems mChildren = new GroupedListItems();

    // Sample data set.  children[i] contains the children (String[]) for groups[i].
    protected String[] groups = { "", };//"Dog Names", "Cat Names", "Fish Names" };
    private String[][] children = {
            { "Arnold", "Barry", "Chuck", "David", "Stas", "Oleg", "Max","Alex","Romeo", "Adolf" },
//            { "Ace", "Bandit", "Cha-Cha", "Deuce", "Nokki", "Baron", "Sharik", "Toshka","SObaka","Belka","Strelka","Zhuchka"},
//            { "Fluffy", "Snuggles","Cate", "Yasha","Bars" },
//            { "Goldy", "Bubbles","Fluffy", "Snuggles","Guffy", "Snoopy" }
    };
    private int mPinnedHeaderBackgroundColor;
    private int mPinnedHeaderTextColor;

    private Context mContext;

    public PinnedHeaderExpandableListAdapter(Context context) {
        mContext = context;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return mChildren.getItem(groupPosition, childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return mChildren.getGroup(groupPosition).size();
    }

    public void setChildren(GroupedListItems children) {
        mChildren = children;
    }

    public TextView getGenericView() {
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(mContext);
        textView.setLayoutParams(lp);
        // Center the text vertically
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        textView.setPadding(16,16,16,16);
        return textView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_2, parent, false);
        }

        //TextView textView = getGenericView();


        ((TextView) convertView.findViewById(R.id.text1)).setText(mChildren.getItem(groupPosition, childPosition).first);
        ((TextView) convertView.findViewById(R.id.text2)).setText(mChildren.getItem(groupPosition, childPosition).second);

        return convertView;
    }


    public Object getGroup(int groupPosition) {
        return mChildren.getGroupName(groupPosition);
    }

    public int getGroupCount() {
        return mChildren.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        String text = (String) mChildren.getGroupName(groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.header, parent, false);
        }

        convertView = (TextView) convertView;

        ((TextView) convertView).setText(text);

        if (text.length() == 0) {
            ((TextView) convertView).setHeight(0);
        }

        return convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }


    /**
     * ????????/?????????? ??????
     */
    public void configurePinnedHeader(View v, int position, int alpha) {
        TextView header = (TextView) v;
        final String title = (String) getGroup(position);
        header.setText(title);

        if (title == null || title.length() == 0) {
            header.setVisibility(View.GONE);
            return;
        }
        else {
            header.setVisibility(View.VISIBLE);
        }

        mPinnedHeaderBackgroundColor = mContext.getResources().getColor(android.R.color.black);
        mPinnedHeaderTextColor = mContext.getResources().getColor(android.R.color.white);

        //header.setText(title);
        if (alpha == 255) {
            header.setBackgroundColor(mPinnedHeaderBackgroundColor);
            header.setTextColor(mPinnedHeaderTextColor);
        } else {
            header.setBackgroundColor(Color.argb(alpha,
                    Color.red(mPinnedHeaderBackgroundColor),
                    Color.green(mPinnedHeaderBackgroundColor),
                    Color.blue(mPinnedHeaderBackgroundColor)));
            header.setTextColor(Color.argb(alpha,
                    Color.red(mPinnedHeaderTextColor),
                    Color.green(mPinnedHeaderTextColor),
                    Color.blue(mPinnedHeaderTextColor)));
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderExpandableListView) {
            ((PinnedHeaderExpandableListView) view).configureHeaderView(firstVisibleItem);
        }

    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }

}
