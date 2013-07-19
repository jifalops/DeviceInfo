package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.ActiveElement;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class ActiveElementController extends AbsElementController implements ActiveElement.Callbacks {

    public interface Callbacks {
        void onStarted();
        void onStopped();
        void onAction(int action, long timestamp);
    }

    protected Callbacks mCallbacks;

    public ActiveElementController(Context context, Callbacks callbacks) {
        super(context);
        mCallbacks = callbacks;
    }

    public void start() {
        ((ActiveElement) mElement).start();
    }

    public void stop() {
        ((ActiveElement) mElement).stop();
    }

    public void isActive() {
        ((ActiveElement) mElement).isActive();
    }

    public boolean isActionable() {
        return true;
    }


    @Override
    public void onStarted() {
        mCallbacks.onStarted();
    }

    @Override
    public void onStopped() {
        mCallbacks.onStopped();
    }

    @Override
    public void onAction(int action, long timestamp) {
        mCallbacks.onAction(action, timestamp);
    }
}
