package com.deviceinfoapp.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsCachedItem2 extends AbsItem implements Item2 {
    private CharSequence mText1, mText2;
    private TextView mTextView1, mTextView2;

    public AbsCachedItem2(CharSequence text1, CharSequence text2, int layoutRes) {
        super(layoutRes);
        mText1 = text1;
        mText2 = text2;
        mTextView1 = mTextView2 = null;
    }

    @Override
    public void setText1(CharSequence text1) {
        mText1 = text1;
        if (mTextView1 != null) {
            mTextView1.setText(mText1);
        }
    }

    @Override
    public CharSequence getText1() {
        return mText1;
    }

    @Override
    public void setText2(CharSequence text2) {
        mText2 = text2;
        if (mTextView2 != null) {
            mTextView2.setText(mText2);
        }
    }

    @Override
    public CharSequence getText2() {
        return mText2;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(mLayoutRes, parent, true);
        }
        mTextView1 = (TextView) convertView.findViewById(R.id.text1);
        mTextView2 = (TextView) convertView.findViewById(R.id.text2);
        mTextView1.setText(mText1);
        mTextView2.setText(mText2);
        return convertView;
    }
}
