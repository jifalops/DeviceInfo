package com.deviceinfoapp.element;

import android.content.Context;

import com.deviceinfoapp.util.ShellHelper;

import java.util.LinkedHashMap;


public class Properties extends Element {

	public Properties(Context context) {
		super(context);
	}

	@Override
	public LinkedHashMap<String, String> getContents() {
//		LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
		return ShellHelper.getProp();
//		return contents;
	}
 
}
