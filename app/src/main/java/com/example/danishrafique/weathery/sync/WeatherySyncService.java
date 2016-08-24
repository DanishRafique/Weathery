package com.example.danishrafique.weathery.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeatherySyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WeatherySyncAdapter sWeatherySyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("WeatherySyncService", "onCreate - WeatherySyncService");
        synchronized (sSyncAdapterLock) {
            if (sWeatherySyncAdapter == null) {
                sWeatherySyncAdapter = new WeatherySyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sWeatherySyncAdapter.getSyncAdapterBinder();
    }
}