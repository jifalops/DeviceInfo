package com.deviceinfoapp.element;

import android.content.Context;

import java.util.LinkedHashMap;

public abstract class ThrottledListeningElement extends TimestampedListeningElement implements ThrottledEvents {
	
	private final int[] mThrottles;
	
	public ThrottledListeningElement(Context context, int numThrottles) {		
		super(context, numThrottles);
		mThrottles = new int[numThrottles];
	}
	
	@Override
	public void setUpdateThrottle(int index, int milliseconds) {
		if (!isValidIndex(index)) return;
		mThrottles[index] = milliseconds;
	}

	@Override
	public int getUpdateThrottle(int index) {
		if (!isValidIndex(index)) return 0;		
		return mThrottles[index];
	}

	@Override
	public boolean isUpdateAllowed(int index) {
		if (!isValidIndex(index)) return false;
		return System.currentTimeMillis() - getTimestamp(index) >= mThrottles[index];
	}
	
	@Override
	public LinkedHashMap<String, String> getContents() {
		LinkedHashMap<String, String> contents = super.getContents();
		for (int i = 0; i < mThrottles.length; ++i) {
			contents.put("Throttle " + i, String.valueOf(mThrottles[i]));
		}
		return contents;
	}
}
