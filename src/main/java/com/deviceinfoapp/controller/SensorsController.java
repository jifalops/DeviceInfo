package com.deviceinfoapp.controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.deviceinfoapp.element.Sensors;
import com.deviceinfoapp.viewable.Item;

import java.util.ArrayList;
import java.util.List;

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

    public SensorsController(Context context, SensorsController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Sensors(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Sensors e = (Sensors) mElement;



        // Main

//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//            LinkedHashMap<String, String> subcontents;
//            int i = 0;
//            for (AccelerometerSensor s : mAccelerometerSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("AccelerometerSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (AmbientTemperatureSensor s : mAmbientTemperatureSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("AmbientTemperatureSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (GravitySensor s : mGravitySensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("GravitySensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (GyroscopeSensor s : mGyroscopeSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("GyroscopeSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (LightSensor s : mLightSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("LightSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (LinearAccelerationSensor s : mLinearAccelerationSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("LinearAccelerationSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (MagneticFieldSensor s : mMagneticFieldSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("MagneticFieldSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (OrientationSensor s : mOrientationSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("OrientationSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (PressureSensor s : mPressureSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("PressureSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (ProximitySensor s : mProximitySensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("ProximitySensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (RelativeHumiditySensor s : mRelativeHumiditySensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("RelativeHumiditySensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (RotationVectorSensor s : mRotationVectorSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("RotationVectorSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            i = 0;
//            for (TemperatureSensor s : mTemperatureSensors) {
//                subcontents = s.getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("TemperatureSensor " + i + " " + e.getKey(), e.getValue());
//                }
//                ++i;
//            }
//
//            float[] coords = getOrientationInWorldCoordinateSystem();
//            if (coords != null) {
//                i = 0;
//                for (float v : coords) {
//                    contents.put("Orientation in World Coordinate System " + i, String.valueOf(v));
//                    ++i;
//                }
//            }
//
//            contents.put("Dew Point (C)", String.valueOf(getDewPoint()));
//            contents.put("Absolute Humidity (g/m^3)", String.valueOf(getAbsoluteHumidity()));
//
//            return contents;
//        }


        // SensorWrapper

//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            contents.put("Type", getTypeString());
//            contents.put("Category", getCategoryString());
//            contents.put("Unit", getUnit());
//            contents.put("Default for Type", String.valueOf(isDefaultForType()));
//            contents.put("Name", getName());
//            contents.put("Vendor", getVendor());
//            contents.put("Version", String.valueOf(getVersion()));
//            contents.put("Power (mA)", String.valueOf(getPower()));
//            contents.put("Resolution (" + getUnit() + ")", String.valueOf(getResolution()));
//            contents.put("Max Range (" + getUnit() + ")", String.valueOf(getMaximumRange()));
//            contents.put("Min Delay (us)", String.valueOf(getMinDelay()));
//            contents.put("Is Listening", String.valueOf(isListening()));
//            contents.put("Is Listening Stopped By Pause", String.valueOf(isPaused()));
//            contents.put("MinUpdateFrequency (ms)", String.valueOf(getMinUpdateFrequency()));
//            contents.put("Last Event Timestmp (ns)", String.valueOf(getLastEventTimestamp()));
////			contents.put("Last Event Accuracy", String.valueOf(getLastAccuracy()));
//            //FIXME accuracy shit
//            contents.put("Last Accuracy Status", getAccuracyString(getLastAccuracy()));
//            contents.put("Last Accuracy Status Timestamp (ms)", String.valueOf(getLastAccuracyTimestamp()));
//
//            contents.putAll(getValuesContents());
//
//            return contents;
//        }
//
//        protected LinkedHashMap<String, String> getValuesContents() {
//            if (mLastValues != null) return null;
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//            for (int i = 0; i < mLastValues.length; ++i) {
//                contents.put("Last Event Values " + i, String.valueOf(mLastValues[i]));
//            }
//            return contents;
//        }





//        /** Minimum delay in microseconds (us) between two events. May only report value when data changes. */
//        public int getMinDelay() {
//            return API >= 9 ? mSensor.getMinDelay() : 0;
//        }


        // Specific sensors



//        public class AccelerometerSensor extends SensorWrapper {
//            private AccelerometerSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_accelerometer),
//                        CATEGORY_MOTION,
//                        context.getString(R.string.unit_meters_per_second_squared));
//            }
//
//            /** Acceleration minus Gx on the x-axis in m/s² */
//            public float getAccelerationX() {
//                return getLastValue(0);
//            }
//
//            /** Acceleration minus Gy on the y-axis in m/s² */
//            public float getAccelerationY() {
//                return getLastValue(1);
//            }
//
//            /** Acceleration minus Gz on the z-axis in m/s² */
//            public float getAccelerationZ() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("X Acceleration (" + getUnit() + ")", String.valueOf(getAccelerationX()));
//                contents.put("Y Acceleration (" + getUnit() + ")", String.valueOf(getAccelerationY()));
//                contents.put("Z Acceleration (" + getUnit() + ")", String.valueOf(getAccelerationZ()));
//                return contents;
//            }
//        }
//
//        public class AmbientTemperatureSensor extends SensorWrapper {
//            private AmbientTemperatureSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_ambient_temperature),
//                        CATEGORY_ENVIRONMENT,
//                        context.getString(R.string.unit_degrees_celsius));
//            }
//
//            /** Ambient (room) temperature in °C */
//            public float getAmbientTemperature() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Ambient temperature (" + getUnit() + ")", String.valueOf(getAmbientTemperature()));
//                return contents;
//            }
//        }
//
//        public class GravitySensor extends SensorWrapper {
//            private GravitySensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_gravity),
//                        CATEGORY_MOTION,
//                        context.getString(R.string.unit_meters_per_second_squared));
//            }
//
//            /** Gravity on the x-axis in m/s² */
//            public float getGravityX() {
//                return getLastValue(0);
//            }
//
//            /** Gravity on the y-axis in m/s² */
//            public float getGravityY() {
//                return getLastValue(1);
//            }
//
//            /** Gravity on the z-axis in m/s² */
//            public float getGravityZ() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("X Gravity (" + getUnit() + ")", String.valueOf(getGravityX()));
//                contents.put("Y Gravity (" + getUnit() + ")", String.valueOf(getGravityY()));
//                contents.put("Z Gravity (" + getUnit() + ")", String.valueOf(getGravityZ()));
//                return contents;
//            }
//        }
//
//        public class GyroscopeSensor extends SensorWrapper {
//            private GyroscopeSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_gyroscope),
//                        CATEGORY_MOTION,
//                        context.getString(R.string.unit_radians_per_second));
//            }
//
//            /** Angular speed around the x-axis in rad/s */
//            public float getAngularSpeedX() {
//                return getLastValue(0);
//            }
//
//            /** Angular speed around the y-axis in rad/s */
//            public float getAngularSpeedY() {
//                return getLastValue(1);
//            }
//
//            /** Angular speed around the z-axis in rad/s */
//            public float getAngularSpeedZ() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("X AngularSpeed (" + getUnit() + ")", String.valueOf(getAngularSpeedX()));
//                contents.put("Y AngularSpeed (" + getUnit() + ")", String.valueOf(getAngularSpeedY()));
//                contents.put("Z AngularSpeed (" + getUnit() + ")", String.valueOf(getAngularSpeedZ()));
//                return contents;
//            }
//        }
//
//        public class LightSensor extends SensorWrapper {
//            private LightSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_light),
//                        CATEGORY_ENVIRONMENT,
//                        context.getString(R.string.unit_lux));
//            }
//
//            /** Ambient light level in lux */
//            public float getLightLevel() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Ambient light level (" + getUnit() + ")", String.valueOf(getLightLevel()));
//                return contents;
//            }
//        }
//
//        public class LinearAccelerationSensor extends SensorWrapper {
//            private LinearAccelerationSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_linear_acceleration),
//                        CATEGORY_MOTION,
//                        context.getString(R.string.unit_meters_per_second_squared));
//            }
//
//            /** Linear acceleration on the x-axis in m/s² */
//            public float getLinearAccelerationX() {
//                return getLastValue(0);
//            }
//
//            /** Linear acceleration on the y-axis in m/s² */
//            public float getLinearAccelerationY() {
//                return getLastValue(1);
//            }
//
//            /** Linear acceleration on the z-axis in m/s² */
//            public float getLinearAccelerationZ() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("X LinearAcceleration (" + getUnit() + ")", String.valueOf(getLinearAccelerationX()));
//                contents.put("Y LinearAcceleration (" + getUnit() + ")", String.valueOf(getLinearAccelerationY()));
//                contents.put("Z LinearAcceleration (" + getUnit() + ")", String.valueOf(getLinearAccelerationZ()));
//                return contents;
//            }
//        }
//
//        public class MagneticFieldSensor extends SensorWrapper {
//            private MagneticFieldSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_magnetic_field),
//                        CATEGORY_POSITION,
//                        context.getString(R.string.unit_micro_tesla));
//            }
//
//            /** Ambient magnetic field on the x-axis in μT */
//            public float getMagneticFieldX() {
//                return getLastValue(0);
//            }
//
//            /** Ambient magnetic field on the y-axis in μT */
//            public float getMagneticFieldY() {
//                return getLastValue(1);
//            }
//
//            /** Ambient magnetic field on the z-axis in μT */
//            public float getMagneticFieldZ() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("X MagneticField (" + getUnit() + ")", String.valueOf(getMagneticFieldX()));
//                contents.put("Y MagneticField (" + getUnit() + ")", String.valueOf(getMagneticFieldY()));
//                contents.put("Z MagneticField (" + getUnit() + ")", String.valueOf(getMagneticFieldZ()));
//                return contents;
//            }
//        }
//
//        public class OrientationSensor extends SensorWrapper {
//            private OrientationSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_orientation),
//                        CATEGORY_POSITION,
//                        context.getString(R.string.unit_degrees));
//            }
//
//            /**
//             * Angle between the magnetic north direction and the y-axis,
//             * around the z-axis (0 to 359). 0=North, 90=East, 180=South, 270=West
//             */
//            public float getAzimuth() {
//                return getLastValue(0);
//            }
//
//            /**
//             * Rotation around x-axis (-180 to 180), with positive
//             * values when the z-axis moves toward the y-axis.
//             */
//            public float getPitch() {
//                return getLastValue(1);
//            }
//
//            /**
//             * Rotation around y-axis (-90 to 90), with positive
//             * values when the x-axis moves toward the z-axis.
//             */
//            public float getRoll() {
//                return getLastValue(2);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Azimuth (" + getUnit() + ")", String.valueOf(getAzimuth()));
//                contents.put("Pitch (" + getUnit() + ")", String.valueOf(getPitch()));
//                contents.put("Roll (" + getUnit() + ")", String.valueOf(getRoll()));
//                return contents;
//            }
//        }
//
//        public class PressureSensor extends SensorWrapper {
//            private PressureSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_pressure),
//                        CATEGORY_ENVIRONMENT,
//                        context.getString(R.string.unit_hecto_pascal));
//            }
//
//            /** Atmospheric pressure in hPa (millibar) */
//            public float getPressure() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Atmospheric pressure (" + getUnit() + ")", String.valueOf(getPressure()));
//                return contents;
//            }
//        }
//
//        public class ProximitySensor extends SensorWrapper {
//            private ProximitySensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_proximity),
//                        CATEGORY_POSITION,
//                        context.getString(R.string.unit_centimeter));
//            }
//
//            /**
//             * Proximity sensor distance in cm.
//             * Note: Some proximity sensors only support a binary near or far
//             * measurement. In this case, the sensor should report its maximum
//             * range value in the far state and a lesser value in the near state.
//             */
//            public float getProximity() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Proximity (" + getUnit() + ") (may only indicate 'near' or 'far')", String.valueOf(getProximity()));
//                return contents;
//            }
//        }
//
//        public class RelativeHumiditySensor extends SensorWrapper {
//            private RelativeHumiditySensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_relative_humidity),
//                        CATEGORY_ENVIRONMENT,
//                        context.getString(R.string.unit_percent));
//            }
//
//            /** Relative ambient air humidity in % */
//            public float getRelativeHumidity() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Relative humidity (" + getUnit() + ")", String.valueOf(getRelativeHumidity()));
//                return contents;
//            }
//        }
//
//        // TODO Math!
//        public class RotationVectorSensor extends SensorWrapper {
//            private RotationVectorSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_rotation_vector),
//                        CATEGORY_MOTION,
//                        context.getString(R.string.unit_unitless));
//            }
//
//            /** x*sin(θ/2) (unitless) */
//            public float getRotationVectorX() {
//                return getLastValue(0);
//            }
//
//            /** y*sin(θ/2) (unitless) */
//            public float getRotationVectorY() {
//                return getLastValue(1);
//            }
//
//            /** z*sin(θ/2) (unitless) */
//            public float getRotationVectorZ() {
//                return getLastValue(2);
//            }
//
//            /** cos(θ/2) (unitless) */
//            public float getRotationVectorExtra() {
//                if (mLastValues == null || mLastValues.length < 4) return 0;
//                return mLastValues[3];
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("RotationVectorX (unitless)", String.valueOf(getRotationVectorX()));
//                contents.put("RotationVectorY (unitless)", String.valueOf(getRotationVectorY()));
//                contents.put("RotationVectorZ (unitless)", String.valueOf(getRotationVectorZ()));
//                contents.put("RotationVectorExtra (unitless)", String.valueOf(getRotationVectorExtra()));
//                return contents;
//            }
//        }
//
//        public class TemperatureSensor extends SensorWrapper {
//            private TemperatureSensor(Context context, Sensor sensor, boolean isDefault) {
//                super (context, sensor, isDefault,
//                        context.getString(R.string.sensor_type_temperature),
//                        CATEGORY_ENVIRONMENT,
//                        context.getString(R.string.unit_degrees_celsius));
//            }
//
//            /** Temperature in °C */
//            public float getTemperature() {
//                return getLastValue(0);
//            }
//
//            @Override
//            protected LinkedHashMap<String, String> getValuesContents() {
//                if (mLastValues != null) return null;
//                LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//                contents.put("Temperature (" + getUnit() + ")", String.valueOf(getTemperature()));
//                return contents;
//            }
//        }

        return data;
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
        ((Callbacks) mCallbacks).onAccuracyChanged(sensor, accuracy);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ((Callbacks) mCallbacks).onSensorChanged(event);
    }
}
