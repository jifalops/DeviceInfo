package com.deviceinfoapp.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsListItem2 extends AbsListItem implements Item2 {
    private CharSequence mText1, mText2;

    public AbsListItem2(CharSequence text1, CharSequence text2, int layoutRes) {
        super(layoutRes);
        mText1 = text1;
        mText2 = text2;
    }

    @Override
    public void setText1(CharSequence text1) {
        mText1 = text1;
        mHasChanged = true;
    }

    @Override
    public CharSequence getText1() {
        return mText1;
    }

    @Override
    public void setText2(CharSequence text2) {
        mText2 = text2;
        mHasChanged = true;
    }

    @Override
    public CharSequence getText2() {
        return mText2;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = inflater.inflate(mLayoutRes, parent, false);
            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(mText1);
        holder.text2.setText(mText2);
        mHasChanged = false;
        return convertView;
    }

    private static class ViewHolder {
        public TextView text1;
        public TextView text2;
    }
}
