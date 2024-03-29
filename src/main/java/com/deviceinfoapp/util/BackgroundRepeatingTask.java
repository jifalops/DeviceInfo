package com.deviceinfoapp.util;

import android.os.AsyncTask;

import com.deviceinfoapp.util.RepeatingTask;

public class BackgroundRepeatingTask extends RepeatingTask {
	private BackgroundTask mBackgroundTask;
	
	public BackgroundRepeatingTask() {
		this(null);
	}
	
	public BackgroundRepeatingTask(Runnable task) {
		super(task);		
	}
	
	@Override
	public boolean start() {
		if (mIsRunning) return false;
		mBackgroundTask = new BackgroundTask();
		mBackgroundTask.execute();
		mIsRunning = true;
		return true;
	}
	
	@Override
	public boolean stop() {
		if (!mIsRunning) return false;
		mBackgroundTask.cancel(true);
		mIsRunning = false;
		return true;
	}
	
	@Override
	public synchronized void setInterval(int interval) {
		mInterval = interval;
	}
	
	@Override
	public synchronized int getInterval() {
		return mInterval;
	}

    // TODO use handler

	private class BackgroundTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			while (true) {
				mTask.run();
				publishProgress();
				try { Thread.sleep(mInterval); } 
				catch (InterruptedException e) {
                    Thread.interrupted();
					break;
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			if (mCallback != null) mCallback.run();
		}
	}
}
