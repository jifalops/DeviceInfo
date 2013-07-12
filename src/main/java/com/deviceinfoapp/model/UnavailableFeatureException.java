package com.deviceinfoapp.model;

public class UnavailableFeatureException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnavailableFeatureException() {
		super();
	}
	
	public UnavailableFeatureException(String detailMessage) {
		super(detailMessage);
	}
}
