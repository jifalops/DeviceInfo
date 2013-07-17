package com.deviceinfoapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsItem2 implements Item {
    private final CharSequence mText1;
    private final CharSequence mText2;
    private final int mLayoutRes;

    public AbsItem2(CharSequence text1, CharSequence text2, int layoutRes) {
        mText1 = text1;
        mText2 = text2;
        mLayoutRes = layoutRes;
    }

    public CharSequence getText1() {
        return mText1;
    }

    public CharSequence getText2() {
        return mText2;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;
        if (convertView == null) {
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
        return convertView;
    }

    private static class ViewHolder {
        public TextView text1;
        public TextView text2;
    }
}
