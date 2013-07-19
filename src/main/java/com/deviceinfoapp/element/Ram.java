package com.deviceinfoapp.element;

import android.content.Context;

import com.deviceinfoapp.DeviceInfo;
import com.deviceinfoapp.util.BackgroundRepeatingTask;
import com.deviceinfoapp.util.Convert;
import com.deviceinfoapp.util.ShellHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Ram extends ActiveElement {
	private static final String LOG_TAG = Ram.class.getSimpleName();
	
	public interface Callback extends Callbacks {
		void onUpdated(LinkedHashMap<String, String> meminfo);
	}
	
	private final LinkedHashMap<String, String> mMeminfo;
	private final BackgroundRepeatingTask mUpdateTask;
	
	public Ram(Context context) {
		super(context);
		mMeminfo = new LinkedHashMap<String, String>();
		mUpdateTask = new BackgroundRepeatingTask(new Runnable() {			
			@Override
			public void run() {
				updateMeminfo();
			}
		});		
		mUpdateTask.setInterval(2000);
		mUpdateTask.setCallback(new Runnable() {			
			@Override
			public void run() {
				if (getCallbacks() != null) ((Callback) getCallbacks()).onUpdated(mMeminfo);
			}
		});
	}
	
	/** Get the current meminfo from /proc */
	public boolean updateMeminfo() {
        List<String> meminfo = ShellHelper.getProc("meminfo");
        if (meminfo == null || meminfo.isEmpty()) return false;        
        String[] parts = null;
        mMeminfo.clear();
        for (String s : meminfo) {
        	parts = s.split(":");
        	if (parts.length != 2) continue;
        	mMeminfo.put(parts[0].trim(), parts[1].trim());
        }
               
        return !mMeminfo.isEmpty();
    }
	
	public Map<String, String> getMeminfo() {
		return mMeminfo;
	}
	
	public String getMeminfo(String key) {
        if (key == null || !mMeminfo.containsKey(key)) return null;
        return mMeminfo.get(key);        
    }
	
	public String getTotal() {
		return getMeminfo("MemTotal");
    }
	
	public String getFree() {
        return getMeminfo("MemFree");     
    }
	
	public String getUsagePercent() {
		long total = getLongFromValue(getTotal());		
		long using = total - getLongFromValue(getFree());
		return Convert.round(DeviceInfo.getPercent(using, total), 1);
	}
	
	public long getLongFromValue(String value) {
		if (value == null) return 0;
		String[] parts = value.split("\\s+");
		if (parts == null || parts.length == 0) return 0;
		try { return Long.valueOf(parts[0]); }
		catch (NumberFormatException e) {
			return 0;
		}
	}
	
	@Override
	public boolean startListening(boolean onlyIfCallbackSet) {
		if (!super.start(onlyIfCallbackSet)) return false;
		mUpdateTask.start();
		return setListening(true);
	}
	
	@Override
	public boolean stop() {
		if (!super.stop()) return false;
		mUpdateTask.stop();
		return !setListening(false);
	}
	
	
	@Override
	public LinkedHashMap<String, String> getContents() {
//		LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();			
		return mMeminfo;
//		return contents;
	}
}
