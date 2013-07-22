package com.deviceinfoapp.viewable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsItem2 extends AbsItem {
    private CharSequence mText1;
    private CharSequence mText2;

    public AbsItem2(CharSequence text1, CharSequence text2, int layoutRes) {
        super(layoutRes);
        mText1 = text1;
        mText2 = text2;
    }

    public CharSequence getText1() {
        return mText1;
    }

    public CharSequence getText2() {
        return mText2;
    }

    public void setText1(CharSequence text1) {
        mText1 = text1;
        mHasChanged = true;
    }

    public void setText2(CharSequence text2) {
        mText2 = text2;
        mHasChanged = true;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;
        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = inflater.inflate(mLayoutRes, null);
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
