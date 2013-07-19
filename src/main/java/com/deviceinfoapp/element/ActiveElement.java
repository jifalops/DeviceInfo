package com.deviceinfoapp.element;

import android.content.Context;

public abstract class ActiveElement extends AbsElement {

    private static final int DEFAULT_ACTION_THROTTLE = 1; // ms

	public interface Callbacks {
        void onStarted();
        void onStopped();
        void onAction(int item, long timestamp);
    }

	protected Callbacks mCallbacks;
    private boolean mIsActive;
    private long[] mActionTimestamps;
    private int[] mActionThrottles;


	public ActiveElement(Context context, Callbacks callbacks) {
		super(context);
        mCallbacks = callbacks;
        mIsActive = false;
	}

    public final boolean isActive() {
        return mIsActive;
    }

    /**
     * Call super at the END of subclass implementation only if element was started.
     */
	public void start() {
        mIsActive = true;
		mCallbacks.onStarted();
	}

    /**
     * Call super at the END of subclass implementation only if element was stopped.
     */
	public void stop() {
        mIsActive = false;
        mCallbacks.onStopped();
	}

    public void setActiveActionCount(int count) {
        mActionTimestamps = new long[count];
        mActionThrottles = new int[count];
        long time = System.currentTimeMillis() - (DEFAULT_ACTION_THROTTLE + 1);
        for (int i = 0; i < count; ++i) {
            mActionTimestamps[i] = time;
            mActionThrottles[i] = DEFAULT_ACTION_THROTTLE;
        }
    }

    public int getActiveActionCount() {
        return mActionThrottles.length;
    }



    public boolean isActionAllowed(int action) {
        return mActionThrottles[action] < (System.currentTimeMillis() - mActionTimestamps[action]);
    }

    public void setActionTime(int action) {
        long time = System.currentTimeMillis();
        mActionTimestamps[action] = time;
        mCallbacks.onAction(action, time);
    }

    public long getActionTime(int action) {
        return mActionTimestamps[action];
    }

    public void setActionThrottle(int action, int millis) {
        mActionThrottles[action] = millis;
    }

    public int getActionThrottle(int action) {
        return mActionThrottles[action];
    }
}
