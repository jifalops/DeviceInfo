package com.deviceinfoapp;

import android.app.Activity;
import android.view.Menu;

/**
 * Created by Jake on 6/24/13.
 */
public class BaseActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
