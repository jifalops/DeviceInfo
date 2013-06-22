package com.deviceinfoapp.item;

public interface ElementListener {	
	boolean startListening();
	boolean startListening(boolean onlyIfCallbackSet);
	boolean stopListening();
	boolean isListening();	
}
