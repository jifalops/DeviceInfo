package com.deviceinfoapp.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.deviceinfoapp.R;
import com.deviceinfoapp.element.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jake on 6/22/13.
 */
public class ElementAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private final Element mElement;
    private final List<Pair<String, String>> mContents;

    public ElementAdapter(Context context, Element element) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mElement = element;
        mContents = new ArrayList<Pair<String, String>>();
        for (Map.Entry<String, String> e : mElement.getContents().entrySet()) {
            mContents.add(new Pair<String, String>(e.getKey(), e.getValue()));
        }
    }

    @Override
    public int getCount() {
        return mContents.size();
    }

    @Override
    public Object getItem(int i) {
        return mContents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mContents.get(i).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_2, null);

            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(mContents.get(position).first);
        holder.text2.setText(mContents.get(position).second);

        return convertView;
    }

    private static class ViewHolder {
        public TextView text1;
        public TextView text2;
    }
}
