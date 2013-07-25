package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.ActiveElement;

/**
 * Created by Jake on 7/18/13.
 */
public abstract class ActiveElementController extends AbsElementController implements ActiveElement.Callbacks {

    public interface Callbacks {
        void onAction(int action);
    }

    protected Callbacks mCallbacks;


    public ActiveElementController(Context context, Callbacks callbacks) {
        super(context);
        mCallbacks = callbacks;
    }


    protected abstract void update(int action);

    public void isActive() {
        ((ActiveElement) mElement).isActive();
    }

    public boolean isActionable() {
        return mElement != null;
    }

    @Override
    public void onAction(int action) {
        update(action);
        mCallbacks.onAction(action);
    }

    public abstract void start();
    public abstract void stop();
}
