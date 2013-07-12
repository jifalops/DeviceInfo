package com.deviceinfoapp.model;

import android.content.Context;


public abstract class Element implements ContentsMapper {
//	private static final String LOG_TAG = Element.class.getSimpleName();

	private final Context mContext;
	
	public Element(Context context) {
		mContext = context;
	}
	
	public final Context getContext() {
		return mContext;
    }
}
