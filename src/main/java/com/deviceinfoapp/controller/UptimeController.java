package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.DeviceInfo;
import com.deviceinfoapp.element.Uptime;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

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
    public List<Item> getData() {
        Uptime e = (Uptime) mElement;
        int[] actions = new int[] {Uptime.ACTION_UPDATED};

        List<Item> data = new ArrayList<Item>();

        data.add(new Item2("Uptime Total", DeviceInfo.getDuration((long) e.getUptimeTotal()), actions));
        data.add(new Item2("Uptime Sleep", DeviceInfo.getDuration((long) e.getUptimeAsleep()), actions));
        data.add(new Item2("Uptime Awake", DeviceInfo.getDuration((long) (e.getUptimeTotal() - e.getUptimeAsleep())), actions));

        return data;
    }

    @Override
    public void onUptimeUpdated(float uptimeTotal, float uptimeAsleep) {
        ((Callbacks) mCallbacks).onUptimeUpdated(uptimeTotal, uptimeAsleep);
    }
}
