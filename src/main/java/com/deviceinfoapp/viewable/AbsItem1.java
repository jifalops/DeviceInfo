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
    private final int mLayoutRes;

    public AbsItem1(CharSequence text, int layoutRes, int[] actions) {
        super(actions);
        mText = text;
        mLayoutRes = layoutRes;
    }

//    public void setText(final CharSequence text) {
//        mText = text;
//    }
//
//    public CharSequence getText() {
//        return mText;
//    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(mLayoutRes, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(mText);
        return convertView;
    }

    private static class ViewHolder {
        public TextView text;
    }
}
