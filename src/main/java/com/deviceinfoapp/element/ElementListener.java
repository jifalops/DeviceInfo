package com.deviceinfoapp.element;

public interface ElementListener {	
	boolean startListening();
	boolean startListening(boolean onlyIfCallbackSet);
	boolean stopListening();
	boolean isListening();	
}
