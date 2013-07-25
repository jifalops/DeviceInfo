package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.DeviceInfo;
import com.deviceinfoapp.element.Uptime;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class UptimeController extends ActiveElementController implements Uptime.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onUptimeUpdated(float uptimeTotal, float uptimeAsleep);
    }

    public UptimeController(Context context, UptimeController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Uptime(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        Uptime e = (Uptime) mElement;

        List<Item> data = new ArrayList<Item>();

        data.add(new ListItem2("Uptime Total", DeviceInfo.getDuration((long) e.getUptimeTotal())));
        data.add(new ListItem2("Uptime Sleep", DeviceInfo.getDuration((long) e.getUptimeAsleep())));
        data.add(new ListItem2("Uptime Awake", DeviceInfo.getDuration((long) (e.getUptimeTotal() - e.getUptimeAsleep()))));

        return data;
    }

    @Override
    public void start() {
        ((Uptime) mElement).start();
    }

    @Override
    public void stop() {
        ((Uptime) mElement).stop();
    }

    @Override
    public void onUptimeUpdated(float uptimeTotal, float uptimeAsleep) {
        ((Callbacks) mCallbacks).onUptimeUpdated(uptimeTotal, uptimeAsleep);
    }
}
