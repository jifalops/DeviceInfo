package com.deviceinfoapp.controller;

import android.content.Context;
import android.content.Intent;

import com.deviceinfoapp.element.Battery;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.Item2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class BatteryController extends ActiveElementController implements Battery.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onReceive(Context context, Intent intent);
    }

    private Item2
        mLevel,
        mTemp,
        mCharging,
        mPlugged,
        mVoltage,
        mTech,
        mHealth;

    public BatteryController(Context context, BatteryController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Battery(context, this);

        mLevel = new Item2("Level", "");
        mTemp = new Item2("Temperature (C)", "");
        mCharging = new Item2("Status", "");
        mPlugged = new Item2("Plugged In Status", "");
        mVoltage = new Item2("Voltage (mV)", "");
        mTech = new Item2("Technology", "");
        mHealth = new Item2("Health", "");
    }

    @Override
    protected void update(int action) {
        Battery e = (Battery) mElement;
        if (action == Battery.ACTION_BATTERY) {
            mLevel.setText2(e.getLevel() + "/" + e.getLevelMax());
            mTemp.setText2(String.valueOf(e.getTemperature()));
            mCharging.setText2(e.getChargingStatus());
            mPlugged.setText2(e.getPluggedInStatus());
            mVoltage.setText2(String.valueOf(e.getVoltage()));
            mTech.setText2(e.getTechnology());
            mHealth.setText2(e.getHealth());
        }
    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        data.add(mLevel);
        data.add(mTemp);
        data.add(mCharging);
        data.add(mPlugged);
        data.add(mVoltage);
        data.add(mTech);
        data.add(mHealth);

        update(Battery.ACTION_BATTERY);

        return data;
    }

    @Override
    public void start() {
        ((Battery) mElement).start();
    }

    @Override
    public void stop() {
        ((Battery) mElement).stop();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ((Callbacks) mCallbacks).onReceive(context, intent);
    }
}
