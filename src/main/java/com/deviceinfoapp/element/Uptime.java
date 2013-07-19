package com.deviceinfoapp.element;

import android.content.Context;

import com.deviceinfoapp.DeviceInfo;
import com.deviceinfoapp.util.ForegroundRepeatingTask;
import com.deviceinfoapp.util.ShellHelper;

import java.util.LinkedHashMap;
import java.util.List;


public class Uptime extends ActiveElement {
	
	public interface Callback extends Callbacks {
		void onUptimeUpdated(float uptimeTotal, float uptimeAsleep);
	}
	
	private float mUptimeTotal;
	private float mUptimeAsleep;
	
	private final ForegroundRepeatingTask mUpdateTask;

	public Uptime(Context context) {
		super(context);
		mUpdateTask = new ForegroundRepeatingTask(new Runnable() {
			public void run() {		        
				updateUptime();
		   }
		});
		mUpdateTask.setInterval(1000);		
	}
	
	public float getUptimeTotal() {
		return mUptimeTotal;
	}
	
	public float getUptimeAsleep() {
		return mUptimeAsleep;
	}
	
	public float getUptimeAwake() {
		return mUptimeTotal - mUptimeAsleep;
	}
	
	
	private void updateUptime() {
		List<String> list = ShellHelper.getProc("uptime");
		if (list == null || list.isEmpty()) return;
		String[] parts = list.get(0).split("\\s+");
		try {
			if (parts.length >= 2) {
				mUptimeTotal = Float.valueOf(parts[0]);
				mUptimeAsleep = Float.valueOf(parts[1]);
			}
			else if (parts.length == 1) mUptimeTotal = Float.valueOf(parts[0]);
		}
		catch (NumberFormatException ignored) {}	
		if (getCallbacks() != null) ((Callback) getCallbacks()).onUptimeUpdated(mUptimeTotal, mUptimeAsleep);
	}
	
	@Override
	public LinkedHashMap<String, String> getContents() {
		LinkedHashMap<String, String> contents = super.getContents();
		contents.put("Uptime Total", DeviceInfo.getDuration((long) mUptimeTotal));
		contents.put("Uptime Sleep", DeviceInfo.getDuration((long) mUptimeAsleep));
		contents.put("Uptime Awake", DeviceInfo.getDuration((long) (mUptimeTotal - mUptimeAsleep)));
		return contents;
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


//	@Override
//	public boolean pause() {
//		if (mIsPaused) return false;
//		mIsPaused = stop();
//		return mIsPaused;
//	}
//
//	@Override
//	public boolean resume() {
//		if (!mIsPaused) return false;
//		mIsPaused = !start();
//		return !mIsPaused;
//	}
//
//	@Override
//	public boolean isPaused() {
//		return mIsPaused;
//	}
//
//	@Override
//	public Object getCallbacks() {
//		return mCallback;
//	}
//
//	@Override
//	public boolean setCallbacks(Object callback) {
//		if (callback instanceof Callbacks) {
//			mCallback = (Callbacks) callback;
//			return true;
//		}
//		return false;
//	}

}
