package com.deviceinfoapp.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsCachedItem1 extends AbsItem implements Item1 {
    private CharSequence mText;
    private TextView mTextView;

    public AbsCachedItem1(CharSequence text, int layoutRes) {
        super(layoutRes);
        mText = text;
        mTextView = null;
    }

    @Override
    public void setText(CharSequence text) {
        mText = text;
        if (mTextView != null) {
            mTextView.setText(mText);
        }
    }

    @Override
    public CharSequence getText() {
        return mText;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(mLayoutRes, parent, true);
        }
        mTextView = (TextView) convertView.findViewById(R.id.text);
        mTextView.setText(mText);
        return convertView;
    }
}
