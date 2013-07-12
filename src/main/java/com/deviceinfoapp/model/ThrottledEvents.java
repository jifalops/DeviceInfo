package com.deviceinfoapp.model;

public interface ThrottledEvents extends TimestampedEvents {
	void setUpdateThrottle(int index, int milliseconds);
	int getUpdateThrottle(int index);
	boolean isUpdateAllowed(int index);
}
