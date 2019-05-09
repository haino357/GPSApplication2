package com.example.gpsapplication2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LocationService extends Service {
    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
