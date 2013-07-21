package com.deviceinfoapp.element;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.deviceinfoapp.R;

// TODO add microphone
public class Sensors extends ActiveElement implements SensorEventListener {
	private static final String LOG_TAG = Sensors.class.getSimpleName();
	
	public static final int
        TYPE_UNDEFINED = 0,
        TYPE_ACCELEROMETER = Sensor.TYPE_ACCELEROMETER,
        TYPE_GYROSCOPE = Sensor.TYPE_GYROSCOPE,
        TYPE_LIGHT = Sensor.TYPE_LIGHT,
        TYPE_MAGNETIC_FIELD = Sensor.TYPE_MAGNETIC_FIELD,
        TYPE_PRESSURE = Sensor.TYPE_PRESSURE,
        TYPE_PROXIMITY = Sensor.TYPE_PROXIMITY,
        TYPE_ORIENTATION = Sensor.TYPE_ORIENTATION,
        TYPE_TEMPERATURE = Sensor.TYPE_TEMPERATURE,
        // API 9
        TYPE_GRAVITY = 9,
        TYPE_LINEAR_ACCELERATION = 10,
        TYPE_ROTATION_VECTOR = 11,
        // API 14
        TYPE_RELATIVE_HUMIDITY = 12,
        TYPE_AMBIENT_TEMPERATURE = 13,

        FREQUENCY_HIGH     = 100,
        FREQUENCY_MEDIUM   = 200,
        FREQUENCY_LOW      = 500,

        ACTION_ACCURACY    = 0,
        ACTION_SENSOR      = 1;

    private static final int
        ACTIVE_ACTIONS     = 3;


    public interface Callbacks extends ActiveElement.Callbacks {
		/** Corresponds to SensorEventListener.onAccuracyChanged() */
		void onAccuracyChanged(Sensor sensor, int accuracy);
		/** Corresponds to SensorEventListener.onSensorChanged() */
		void onSensorChanged(SensorEvent event);
	}

    public final String
        SENSOR_ACCELEROMETER,
        SENSOR_AMBIENT_TEMPERATURE,
        SENSOR_GRAVITY,
        SENSOR_GYROSCOPE,
        SENSOR_LIGHT,
        SENSOR_LINEAR_ACCELERATION,
        SENSOR_MAGNETIC_FIELD,
        SENSOR_PRESSURE,
        SENSOR_PROXIMITY,
        SENSOR_RELATIVE_HUMIDITY,
        SENSOR_ROTATION_VECTOR,
        SENSOR_ORIENTATION,
        SENSOR_TEMPERATURE,

        UNIT_NONE,
        UNIT_CELSIUS,
        UNIT_METERS_PER_SEC_SQUARED,
        UNIT_RADIANS_PER_SEC,
        UNIT_LUX,
        UNIT_MICRO_TESLA,
        UNIT_DEGREES,
        UNIT_HECTO_PASCAL,
        UNIT_CM,
        UNIT_PERCENT,
        UNIT_GRAMS_PER_CUBIC_METER,

        ACCURACY_HIGH,
        ACCURACY_MEDIUM,
        ACCURACY_LOW,
        ACCURACY_UNRELIABLE,

        CATEGORY_OTHER,
        CATEGORY_MOTION,
        CATEGORY_POSITION,
        CATEGORY_ENVIRONMENT;

    private float[]
        mAccelerometerValues,
        mMagneticFieldValues,
        mRelativeHumidityValues,
        mAmbientTemperatureValues;

	
    
	private SensorManager mSensorManager;
    
	public Sensors(Context context, Sensors.Callbacks callbacks) {
		super(context, callbacks);
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);	
		
		SENSOR_ACCELEROMETER = context.getString(R.string.sensor_type_accelerometer);
        SENSOR_AMBIENT_TEMPERATURE = context.getString(R.string.sensor_type_ambient_temperature);
        SENSOR_GRAVITY = context.getString(R.string.sensor_type_gravity);
        SENSOR_GYROSCOPE = context.getString(R.string.sensor_type_gyroscope);
        SENSOR_LIGHT = context.getString(R.string.sensor_type_light);
        SENSOR_LINEAR_ACCELERATION = context.getString(R.string.sensor_type_linear_acceleration);
        SENSOR_MAGNETIC_FIELD = context.getString(R.string.sensor_type_magnetic_field);
        SENSOR_RELATIVE_HUMIDITY = context.getString(R.string.sensor_type_relative_humidity);
        SENSOR_ORIENTATION = context.getString(R.string.sensor_type_orientation);
        SENSOR_PRESSURE = context.getString(R.string.sensor_type_pressure);
        SENSOR_PROXIMITY = context.getString(R.string.sensor_type_proximity);
        SENSOR_ROTATION_VECTOR = context.getString(R.string.sensor_type_rotation_vector);
        SENSOR_TEMPERATURE = context.getString(R.string.sensor_type_temperature);

        UNIT_NONE = context.getString(R.string.unit_unitless);
        UNIT_CELSIUS = context.getString(R.string.unit_degrees_celsius);
		UNIT_METERS_PER_SEC_SQUARED = context.getString(R.string.unit_meters_per_second_squared);
        UNIT_RADIANS_PER_SEC = context.getString(R.string.unit_radians_per_second);
        UNIT_LUX = context.getString(R.string.unit_lux);
        UNIT_MICRO_TESLA = context.getString(R.string.unit_micro_tesla);
        UNIT_DEGREES = context.getString(R.string.unit_degrees);
        UNIT_HECTO_PASCAL = context.getString(R.string.unit_hecto_pascal);
        UNIT_CM = context.getString(R.string.unit_centimeter);
        UNIT_PERCENT = context.getString(R.string.unit_percent);
        UNIT_GRAMS_PER_CUBIC_METER = context.getString(R.string.unit_grams_per_cubic_meter);

		ACCURACY_HIGH = context.getString(R.string.sensor_accuracy_high);
		ACCURACY_MEDIUM = context.getString(R.string.sensor_accuracy_medium);
		ACCURACY_LOW = context.getString(R.string.sensor_accuracy_low);
		ACCURACY_UNRELIABLE = context.getString(R.string.sensor_accuracy_unreliable);

        CATEGORY_MOTION = context.getString(R.string.sensor_category_motion);
        CATEGORY_POSITION = context.getString(R.string.sensor_category_position);
        CATEGORY_ENVIRONMENT = context.getString(R.string.sensor_category_environment);
        CATEGORY_OTHER = context.getString(R.string.sensor_category_other);

        setActiveActionCount(ACTIVE_ACTIONS);
        setActionThrottle(ACTION_SENSOR, FREQUENCY_MEDIUM);
	}

    public SensorManager getSensorManager() {
        return mSensorManager;
    }

    public String getCategory(int type) {
        switch (type) {
        case TYPE_UNDEFINED: return CATEGORY_OTHER;

        case TYPE_GRAVITY:
        case TYPE_GYROSCOPE:
        case TYPE_LINEAR_ACCELERATION:
        case TYPE_ROTATION_VECTOR:
        case TYPE_ACCELEROMETER: return CATEGORY_MOTION;

        case TYPE_LIGHT:
        case TYPE_RELATIVE_HUMIDITY:
        case TYPE_PRESSURE:
        case TYPE_TEMPERATURE:
        case TYPE_AMBIENT_TEMPERATURE: return CATEGORY_ENVIRONMENT;


        case TYPE_ORIENTATION:
        case TYPE_PROXIMITY:
        case TYPE_MAGNETIC_FIELD: return CATEGORY_POSITION;
        }
        return CATEGORY_OTHER;
    }
    
    public String getUnit(int type) {
        switch (type) {
        case TYPE_ROTATION_VECTOR:
        case TYPE_UNDEFINED: return UNIT_NONE;

        case TYPE_GRAVITY:
        case TYPE_LINEAR_ACCELERATION:
        case TYPE_ACCELEROMETER: return UNIT_METERS_PER_SEC_SQUARED;

        case TYPE_TEMPERATURE:
        case TYPE_AMBIENT_TEMPERATURE: return UNIT_CELSIUS;

        case TYPE_GYROSCOPE: return UNIT_RADIANS_PER_SEC;
        case TYPE_LIGHT: return UNIT_LUX;
        case TYPE_MAGNETIC_FIELD: return UNIT_MICRO_TESLA;
        case TYPE_PRESSURE: return UNIT_HECTO_PASCAL;
        case TYPE_PROXIMITY: return UNIT_CM;
        case TYPE_RELATIVE_HUMIDITY: return UNIT_PERCENT;
        case TYPE_ORIENTATION: return UNIT_DEGREES;
        }
        return UNIT_NONE;
    }
    
    public String getAccuracy(int accuracy) {
        switch(accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH: return ACCURACY_HIGH;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW: return ACCURACY_LOW;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM: return ACCURACY_MEDIUM;
            case SensorManager.SENSOR_STATUS_UNRELIABLE: return ACCURACY_UNRELIABLE;
        }
        return null;
    }


    //
    // Aggregate sensor info
    //

    public float[] getOrientationInWorldCoordinateSystem() {
		if (mAccelerometerValues == null || mMagneticFieldValues == null) return null;
		
		float[] rotationMatrix = new float[9], 
				orientation = new float[3];

		if (SensorManager.getRotationMatrix(rotationMatrix, null, mAccelerometerValues, mMagneticFieldValues)) {
			SensorManager.getOrientation(rotationMatrix, orientation);
		}
		return orientation;
	}
	
	/** Calculates the dew point in degrees Celsius */
	public float getDewPoint() {
		if (mRelativeHumidityValues == null || mAmbientTemperatureValues == null) return 0f;
		float rh = mRelativeHumidityValues[0];
		float t = mAmbientTemperatureValues[0];
		double h = Math.log(rh / 100.0) + (17.62 * t) / (243.12 + t);
		return (float) (243.12 * h / (17.62 - h));
	}
	
	/** Calculates the absolute humidity in g/m^3 */
	public float getAbsoluteHumidity() {
        if (mRelativeHumidityValues == null || mAmbientTemperatureValues == null) return 0f;
        float rh = mRelativeHumidityValues[0];
        float t = mAmbientTemperatureValues[0];
		return (float) (216.7 * (rh / 100.0 * 6.112 * Math.exp(17.62 * t / (243.12 + t)) / (273.15 + t)));
	}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (!isActionAllowed(ACTION_ACCURACY)) return;

        setActionTime(ACTION_ACCURACY);
        ((Callbacks) mCallbacks).onAccuracyChanged(sensor, accuracy);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isActionAllowed(ACTION_SENSOR)) return;

        setActionTime(ACTION_SENSOR);
        ((Callbacks) mCallbacks).onSensorChanged(event);
    }
	
	@Override
	public void start() {
		if (isActive()) return;
        for (Sensor s : mSensorManager.getSensorList(Sensor.TYPE_ALL)) {
            mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
        }
        super.start();
	}
	
	@Override
	public void stop() {
		if (!isActive()) return;
        mSensorManager.unregisterListener(this);
		super.stop();
    }

}
