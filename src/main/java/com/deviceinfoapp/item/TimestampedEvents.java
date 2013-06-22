package com.deviceinfoapp.item;

public interface TimestampedEvents {
	long getTimestamp(int index);
	void setTimestamp(int index);
	int getNumTimestamps();
	boolean isValidIndex(int index);	
}
