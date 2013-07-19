package com.deviceinfoapp.element;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.deviceinfoapp.util.ShellHelper;

import java.util.LinkedHashMap;
import java.util.List;


public class Identifiers extends AbsElement {

	public final String ANDROID_ID;
	public final String DEVICE_ID;
	public final String PHONE_ID;
	public final String SIM_SERIAL;
	public final String LINE_1_NUMBER;
	public final String DEVICE_SERIAL;
	public final String SUBSCRIBER_ID;
	
	public Identifiers(Context context) {
		super(context);
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		DEVICE_ID = tm.getDeviceId();
		SIM_SERIAL = tm.getSimSerialNumber();
		LINE_1_NUMBER = tm.getLine1Number();
		SUBSCRIBER_ID = tm.getSubscriberId();
		ANDROID_ID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		DEVICE_SERIAL = getDeviceSerial();		
		List<String> list = ShellHelper.getProc("phoneid");
		if (list == null || list.isEmpty()) PHONE_ID = null;
		else PHONE_ID = list.get(0);
	}
	
	private String getDeviceSerial() {
		String s = null;
		if (Build.VERSION.SDK_INT >= 9) {
			s = Build.SERIAL;
			if (s != null && s.length() > 0 
				&& !s.equals(Build.UNKNOWN) && !s.equalsIgnoreCase("null")) {
				return s;
			}
		}
		return ShellHelper.getProp("ro.serialno");		
	}
	
	@Override
	public LinkedHashMap<String, String> getContents() {
		LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
		
		contents.put("Device ID", DEVICE_ID);
		contents.put("Device Serial", DEVICE_SERIAL);
		contents.put("Android ID", ANDROID_ID);
		contents.put("Phone ID", PHONE_ID);
		contents.put("SIM Serial", SIM_SERIAL);
		contents.put("Line 1 Number", LINE_1_NUMBER);
		contents.put("Subscriber ID", SUBSCRIBER_ID);
		
		return contents;
	}

}
