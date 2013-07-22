package com.deviceinfoapp.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.deviceinfoapp.R;
import com.deviceinfoapp.element.Sensors;
import com.deviceinfoapp.item.AbsItem2;
import com.deviceinfoapp.item.Header;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.SubHeader;
import com.deviceinfoapp.item.SubItem2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jake on 7/18/13.
 */
public class SensorsController extends ActiveElementController implements Sensors.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        /** Corresponds to SensorEventListener.onAccuracyChanged() */
        void onAccuracyChanged(Sensor sensor, int accuracy);
        /** Corresponds to SensorEventListener.onSensorChanged() */
        void onSensorChanged(SensorEvent event);
    }

    private AbsItem2
            mWorldX,
            mWorldY,
            mWorldZ,
            mWorldAccuracy,
            mDewPoint,
            mHumidity,
            mHumidityAccuracy;

    private Map<Sensor, AbsItem2[]> mSensorMap;

    private boolean mHasAggregateData;

    public SensorsController(Context context, Callbacks callbacks) {
        super(context, callbacks);
        Sensors e = new Sensors(context, this);
        mElement = e;

        List<Sensor> acc = e.getSensors(Sensors.TYPE_ACCELEROMETER);
        List<Sensor> mag = e.getSensors(Sensors.TYPE_MAGNETIC_FIELD);

        List<Sensor> amb = e.getSensors(Sensors.TYPE_AMBIENT_TEMPERATURE);
        List<Sensor> hum = e.getSensors(Sensors.TYPE_RELATIVE_HUMIDITY);

        if (acc.size() > 0 && mag.size() > 0) {
            mHasAggregateData = true;
            String unit = e.getUnit(Sensors.TYPE_ORIENTATION);
            mWorldX = new SubItem2("Azimuth (" + unit + ")", "");
            mWorldY = new SubItem2("Pitch (" + unit + ")", "");
            mWorldZ = new SubItem2("Roll (" + unit + ")", "");
            mWorldAccuracy = new SubItem2("Accuracy", "");
        }

        if (amb.size() > 0 || hum.size() > 0) {
            mHasAggregateData = true;
            mDewPoint = new SubItem2("Dew Point (" + context.getString(R.string.unit_degrees_celsius) + ")", "");
            mHumidity = new SubItem2("Absolute Humidity (" + context.getString(R.string.unit_grams_per_cubic_meter) + ")", "");
            mHumidityAccuracy = new SubItem2("Accuracy", "");
        }

        Sensor[] sensors = e.getSensors();
        int size = sensors.length;

        mSensorMap = new HashMap<Sensor, AbsItem2[]>();
        AbsItem2[] items = null;
        int type;
        String unit;
        for (int i = 0; i < size; ++i) {
            type = sensors[i].getType();
            unit = e.getUnit(type);

            switch (type) {
                case Sensors.TYPE_ACCELEROMETER:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("X Acceleration (" + unit + ")", "");
                    items[2] = new SubItem2("Y Acceleration (" + unit + ")", "");
                    items[3] = new SubItem2("Z Acceleration (" + unit + ")", "");
                    break;
                case Sensors.TYPE_AMBIENT_TEMPERATURE:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Ambient Temp (" + unit + ")", "");
                    break;
                case Sensors.TYPE_GRAVITY:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("X Gravity (" + unit + ")", "");
                    items[2] = new SubItem2("Y Gravity (" + unit + ")", "");
                    items[3] = new SubItem2("Z Gravity (" + unit + ")", "");
                    break;
                case Sensors.TYPE_GYROSCOPE:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("X Angular Speed (" + unit + ")", "");
                    items[2] = new SubItem2("Y Angular Speed (" + unit + ")", "");
                    items[3] = new SubItem2("Z Angular Speed (" + unit + ")", "");
                    break;
                case Sensors.TYPE_LIGHT:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Ambient light level (" + unit + ")", "");
                    break;
                case Sensors.TYPE_LINEAR_ACCELERATION:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("X Linear Acceleration (" + unit + ")", "");
                    items[2] = new SubItem2("Y Linear Acceleration (" + unit + ")", "");
                    items[3] = new SubItem2("Z Linear Acceleration (" + unit + ")", "");
                    break;
                case Sensors.TYPE_MAGNETIC_FIELD:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("X Magnetic Field (" + unit + ")", "");
                    items[2] = new SubItem2("Y Magnetic Field (" + unit + ")", "");
                    items[3] = new SubItem2("Z Magnetic Field (" + unit + ")", "");
                    break;
                case Sensors.TYPE_ORIENTATION:
                    items = new AbsItem2[4];
                    items[1] = new SubItem2("Azimuth (" + unit + ")", "");
                    items[2] = new SubItem2("Pitch (" + unit + ")", "");
                    items[3] = new SubItem2("Roll (" + unit + ")", "");
                    break;
                case Sensors.TYPE_PRESSURE:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Atmospheric Pressure (" + unit + ")", "");
                    break;
                case Sensors.TYPE_PROXIMITY:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Proximity (" + unit + ")", "");
                    break;
                case Sensors.TYPE_RELATIVE_HUMIDITY:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Relative humidity (" + unit + ")", "");
                    break;
                case Sensors.TYPE_ROTATION_VECTOR:
                    items = new AbsItem2[5];
                    items[1] = new SubItem2("X Rotation Vector (" + unit + ")", "");
                    items[2] = new SubItem2("Y Rotation Vector (" + unit + ")", "");
                    items[3] = new SubItem2("Z Rotation Vector (" + unit + ")", "");
                    items[4] = new SubItem2("Extra Rotation Vector (" + unit + ")", "");
                    break;
                case Sensors.TYPE_TEMPERATURE:
                    items = new AbsItem2[2];
                    items[1] = new SubItem2("Temperature (" + unit + ")", "");
                    break;
            }

            items[0] = new SubItem2("Accuracy", "");
            mSensorMap.put(sensors[i], items);
        }
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Sensors e = (Sensors) mElement;

        if (mHasAggregateData) {
            data.add(new Header("Aggregate Data"));
            if (mWorldX != null) {
                data.add(new SubHeader("World Coordinates (" + e.getUnit(Sensors.TYPE_ORIENTATION) + ")"));
                data.add(mWorldX);
                data.add(mWorldY);
                data.add(mWorldZ);
                data.add(mWorldAccuracy);
            }
            if (mDewPoint != null) {
                data.add(new SubHeader("Environment Info"));
                data.add(mDewPoint);
                data.add(mHumidity);
                data.add(mHumidityAccuracy);
            }

        }

        String usec = mContext.getString(R.string.unit_micro_seconds);
        String mamp = mContext.getString(R.string.unit_milli_amps);

        addSensors(e, data, Sensors.TYPE_ACCELEROMETER, usec, mamp);
        addSensors(e, data, Sensors.TYPE_AMBIENT_TEMPERATURE, usec, mamp);
        addSensors(e, data, Sensors.TYPE_GRAVITY, usec, mamp);
        addSensors(e, data, Sensors.TYPE_GYROSCOPE, usec, mamp);
        addSensors(e, data, Sensors.TYPE_LIGHT, usec, mamp);
        addSensors(e, data, Sensors.TYPE_LINEAR_ACCELERATION, usec, mamp);
        addSensors(e, data, Sensors.TYPE_MAGNETIC_FIELD, usec, mamp);
        addSensors(e, data, Sensors.TYPE_ORIENTATION, usec, mamp);
        addSensors(e, data, Sensors.TYPE_PRESSURE, usec, mamp);
        addSensors(e, data, Sensors.TYPE_PROXIMITY, usec, mamp);
        addSensors(e, data, Sensors.TYPE_RELATIVE_HUMIDITY, usec, mamp);
        addSensors(e, data, Sensors.TYPE_ROTATION_VECTOR, usec, mamp);
        addSensors(e, data, Sensors.TYPE_TEMPERATURE, usec, mamp);

        return data;
    }

    private void addSensors(Sensors sensors, List<Item> data, int type, String usec, String mamp) {
        List<Sensor> sensorList = sensors.getSensors(type);
        if (sensorList.isEmpty()) return;

        data.add(new Header(sensors.getSensorName(type)));

        String unit = sensors.getUnit(type);
        boolean first = true;
        for (Sensor s : sensorList) {
            for (Item item : mSensorMap.get(s)) {
                data.add(item);
            }
            data.add(new SubItem2("Name", s.getName()));
            data.add(new SubItem2("Vendor", s.getVendor()));
            data.add(new SubItem2("Version", String.valueOf(s.getVersion())));
            data.add(new SubItem2("Default", String.valueOf(first)));
            data.add(new SubItem2("Power (" + mamp + ")", String.valueOf(s.getPower())));
            data.add(new SubItem2("Resolution (" + unit + ")", String.valueOf(s.getResolution())));
            data.add(new SubItem2("Max Range (" + unit + ")", String.valueOf(s.getMaximumRange())));
            data.add(new SubItem2("Min Delay (" + usec + ")", String.valueOf(s.getMinDelay())));
            first = false;
        }
    }

    @Override
    public void start() {
        ((Sensors) mElement).start();
    }

    @Override
    public void stop() {
        ((Sensors) mElement).stop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        mSensorMap.get(sensor)[0].setText2(((Sensors) mElement).getAccuracy(accuracy));
        ((Callbacks) mCallbacks).onAccuracyChanged(sensor, accuracy);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensors s = (Sensors) mElement;
        String acc = s.getAccuracy(event.accuracy);
        mSensorMap.get(event.sensor)[0].setText2(acc);
        AbsItem2[] items = mSensorMap.get(event.sensor);
        for (int i = 1, len1 = items.length, len2 = event.values.length + 1; i < len1 && i < len2; ++i) {
            items[i].setText2(String.valueOf(event.values[i - 1]));
        }

        if (mHasAggregateData) {
            switch (event.sensor.getType()) {
                case Sensors.TYPE_ACCELEROMETER:
                case Sensors.TYPE_MAGNETIC_FIELD:
                    float[] coords = s.getOrientationInWorldCoordinateSystem();
                    if (coords != null) {
                        mWorldX.setText2(String.valueOf(coords[0]));
                        mWorldY.setText2(String.valueOf(coords[1]));
                        mWorldZ.setText2(String.valueOf(coords[2]));
                        mWorldAccuracy.setText2(acc);
                    }
                    break;
                case Sensors.TYPE_AMBIENT_TEMPERATURE:
                case Sensors.TYPE_RELATIVE_HUMIDITY:
                    mDewPoint.setText2(String.valueOf(s.getDewPoint()));
                    mHumidity.setText2(String.valueOf(s.getAbsoluteHumidity()));
                    mHumidityAccuracy.setText2(acc);
                    break;
            }
        }

        ((Callbacks) mCallbacks).onSensorChanged(event);
    }
}
