package com.deviceinfoapp;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;

/**
 * Created by Jake on 6/24/13.
 */
public class BaseActivity extends FragmentActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
