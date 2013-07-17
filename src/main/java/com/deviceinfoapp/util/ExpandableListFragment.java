package com.deviceinfoapp.util;

/**
 * Created by Jake on 7/11/13.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.deviceinfoapp.R;
import com.deviceinfoapp.model.ExpandableItemArrayAdapter;

public class ExpandableListFragment extends Fragment {

    private ExpandableItemArrayAdapter mAdapter;
    protected ExpandableListView mListView;

    public void setAdapter(ExpandableItemArrayAdapter adapter) {
        mAdapter = adapter;
        mListView.setAdapter(mAdapter);
    }

    public ExpandableItemArrayAdapter getAdapter() {
        return mAdapter;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View root = inflater.inflate(R.layout.fragment_item_detail, container, false);

        mListView = (ExpandableListView) root.findViewById(R.id.list);
//        setAdapter(new ExpandableItemArrayAdapter(getActivity()));

        mListView.setGroupIndicator(null);
//        View h = LayoutInflater.from(getActivity()).inflate(R.layout.header_item, (ViewGroup) root.findViewById(R.id.root), false);
//        mListView.setPinnedHeaderView(h);

        //mListView.setDividerHeight(0);

//        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//
//                // we need to obtain the relative y coordinate of the child view,
//                // not its clicked subview, thus first we try to calculate its true index
//                long packedPos = ExpandableListView.getPackedPositionForChild(groupPosition, childPosition);
//                int viewPos = mListView.getFlatListPosition(packedPos) - mListView.
//                        getFirstVisiblePosition();
//                View childView = parent.getChildAt(viewPos); // got it
//
//
//                if (childView.getTop() < mListView.getHeaderViewHeight() * .75) {
//                    // if the clicked child item overlaps more than 25%
//                    //  of pinned header_item, consider it being underneath
//                    long groupPackedPos = ExpandableListView.getPackedPositionForGroup(groupPosition);
//                    int groupFlatPos = mListView.getFlatListPosition(groupPackedPos);
//                    mListView.smoothScrollToPosition(groupFlatPos);
//                }
//                return true;
//            }
//        });

        return root;
    }
}
