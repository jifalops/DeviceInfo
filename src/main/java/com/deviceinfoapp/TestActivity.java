package com.deviceinfoapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.deviceinfoapp.controller.SensorsController;
import com.deviceinfoapp.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jake on 7/28/13.
 */
public class TestActivity extends FragmentActivity implements SensorsController.Callbacks {
    private SensorsController mController;
    private List<View> mViews;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_detail_cached);

        mViews = new ArrayList<View>();

        (new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Looper.prepare();
                mController = new SensorsController(TestActivity.this, TestActivity.this);
                List<Item> items = mController.getData();
                mLinearLayout = (LinearLayout) findViewById(R.id.list);
                LayoutInflater inflater = LayoutInflater.from(TestActivity.this);
                for (int i = 0, len = items.size(); i < len; ++i) {
                    mViews.add(items.get(i).getView(inflater, null, null));
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for (int i = 0, len = mViews.size(); i < len; ++i) {
                    mLinearLayout.addView(mViews.get(i));
                }

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mController.start();
                    }
                }, 10000);
            }
        }).execute();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mController != null) mController.stop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAction(int action) {

    }
}
