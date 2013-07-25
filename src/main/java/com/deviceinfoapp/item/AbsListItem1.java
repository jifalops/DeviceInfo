package com.deviceinfoapp.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsListItem1 extends AbsListItem implements Item1 {
    private CharSequence mText;

    public AbsListItem1(CharSequence text, int layoutRes) {
        super(layoutRes);
        mText = text;
    }

    @Override
    public void setText(CharSequence text) {
        mText = text;
        mHasChanged = true;
    }

    @Override
    public CharSequence getText() {
        return mText;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = inflater.inflate(mLayoutRes, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(mText);
        mHasChanged = false;
        return convertView;
    }

    private static class ViewHolder {
        public TextView text;
    }
}
