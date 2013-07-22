package com.deviceinfoapp.viewable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.deviceinfoapp.R;

/**
 * Created by Jake on 7/17/13.
 */
public abstract class AbsItem1 extends AbsItem {
    private CharSequence mText;

    public AbsItem1(CharSequence text, int layoutRes) {
        super(layoutRes);
        mText = text;
    }

    public void setText(CharSequence text) {
        mText = text;
        mHasChanged = true;
    }

    public CharSequence getText() {
        return mText;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;
        if (convertView == null || !(convertView.getTag() instanceof ViewHolder)) {
            convertView = inflater.inflate(mLayoutRes, null);
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
