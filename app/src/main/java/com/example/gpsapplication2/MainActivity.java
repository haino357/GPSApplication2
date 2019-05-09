package com.example.gpsapplication2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager;
    private String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG", "called onCreate START");
        initLocationManager();
        Log.d("DEBUG", "called onCreate END");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("DEBUG", "called onStart START");
        locationStart();
        Log.d("DEBUG", "called onStart END");
    }

    @Override
    protected void  onStop() {
        super.onStop();

        locationStop();
    }

    private void initLocationManager() {
        //インスタンス作成
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Log.d("DEBUG", "called initLocationManager START");
        //詳細設定　※要調査
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setSpeedRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        bestProvider = mLocationManager.getBestProvider(criteria, true);

        Log.d("DEBUG", "called initLocationManager END");
    }

    private void checkPermission() {
        Log.d("DEBUG", "called checkPermission START");
        Log.d("DEBUG", "called checkPermission " + ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION));
        Log.d("DEBUG", "called checkPermission " + ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION));
        Log.d("DEBUG", "called checkPermission " + PackageManager.PERMISSION_GRANTED);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //パーミッションの許可を取得する
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            Log.d("DEBUG", "called checkPermission 1");
        }
        Log.d("DEBUG", "called checkPermission END");
    }

    private void locationStart() {
        Log.d("DEBUG", "called locationStart START");
        checkPermission();
        mLocationManager.requestLocationUpdates(
                bestProvider, 60000, 3, this);
        Log.d("DEBUG", "called locationStart END");
    }

    private void locationStop() {
        Log.d("DEBUG", "called locationStop START");
        mLocationManager.removeUpdates(this);
        Log.d("DEBUG", "called locationStop END");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("DEBUG", "called onLocationChanged");
        Log.d("DEBUG", "lat : " + location.getLatitude());
        Log.d("DEBUG", "lon : " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("DEBUG", "called onStatusChanged");
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("DEBUG", "AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("DEBUG", "OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("DEBUG", "TEMPORARILY_UNAVAILABLE");
                break;
            default:
                Log.d("DEBUG", "DEFAULT");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("DEBUG", "called onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("DEBUG", "called onProviderDisabled");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d("DEBUG", "called onPointerCaptureChanged");
    }
}
