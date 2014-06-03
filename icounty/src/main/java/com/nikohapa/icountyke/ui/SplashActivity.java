package com.nikohapa.icountyke.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nikohapa.icountyke.R;
import com.nikohapa.icountyke.constant.Constants;


public class SplashActivity extends ActionBarActivity {

    private static final String LOG_TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TODO init component => db, prefs, etc

        try{
            getSupportActionBar().hide();
        }catch (Exception ex){
            Log.e(LOG_TAG, "" + ex);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSplash();
            }
        }, Constants.SPLASH_TIMER);
    }

    void hideSplash(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
