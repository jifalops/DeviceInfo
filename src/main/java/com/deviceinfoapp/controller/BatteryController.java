package com.deviceinfoapp.controller;

import android.content.Context;
import android.content.Intent;

import com.deviceinfoapp.element.Battery;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class BatteryController extends ActiveElementController implements Battery.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onReceive(Context context, Intent intent);
    }

    public BatteryController(Context context, BatteryController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Battery(context, this);
    }

    @Override
    public List<Item> getData() {
        Battery e = (Battery) mElement;
        int[] actions = new int[] {Battery.ACTION_BATTERY};
        mData.clear();
        mData.add(new Item2("Level", e.getLevel() + "/" + e.getLevelMax(), actions));
        mData.add(new Item2("Temperature (C)", String.valueOf(e.getTemperature()), actions));
        mData.add(new Item2("Status", e.getChargingStatus(), actions));
        mData.add(new Item2("Plugged In Status", e.getPluggedInStatus(), actions));
        mData.add(new Item2("Voltage (mV)", String.valueOf(e.getVoltage()), actions));
        mData.add(new Item2("Technology", e.getTechnology(), actions));
        mData.add(new Item2("Health", e.getHealth(), actions));
//        data.add(new Item2("Battery Is Present", String.valueOf(e.isBatteryPresent())));

        return mData;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ((Callbacks) mCallbacks).onReceive(context, intent);

        // vvv  Do using ActiveElementController?
        // TODO when this is called (up to fragment), update visible views
//        You can check whether a given position is visible, using getFirstVisiblePosition() and getChildCount().
        // dont wait for getView in Item, just setText from adapter.
    }
}
