package com.rarmash.prac2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Camera;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Camera mCamera;
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    int mCurrentScore, mCurrentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        } else {

        }
    }

    private void setUpActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Debug.stopMethodTracing();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mCamera != null) {
            // mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mCamera == null) {
            initializeCamera();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        ContentValues values = new ContentValues();
        // values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
        // values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());

        getContentResolver().update(
                null, // mUri,
                values,
                null,
                null
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            //Intent с действием android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
    }

    private void initializeCamera() {}
}