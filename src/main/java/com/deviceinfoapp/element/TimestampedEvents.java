package com.deviceinfoapp.element;

public interface TimestampedEvents {
	long getTimestamp(int index);
	void setTimestamp(int index);
	int getNumTimestamps();
	boolean isValidIndex(int index);	
}
