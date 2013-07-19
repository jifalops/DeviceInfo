package com.deviceinfoapp.element;

import android.content.Context;
import android.os.Build;


public abstract class AbsElement {
//	private static final String LOG_TAG = AbsElement.class.getSimpleName();

    protected static final int API = Build.VERSION.SDK_INT;

	private Context mContext;
	
	public AbsElement(Context context) {
		mContext = context;
	}
	
	public final Context getContext() {
		return mContext;
    }
}
