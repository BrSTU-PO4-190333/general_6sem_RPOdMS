package com.example.lab10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class MainActivity extends Activity {
    TextView tvOut;
    TextView tvLon;
    TextView tvLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.textView1);
        tvLon = (TextView) findViewById(R.id.longitude);
        tvLat = (TextView) findViewById(R.id.latitude);
        LocationManager mlocManager = (LocationManager) getSystemService
                (Context.LOCATION_SERVICE);
        LocationListener mlocListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Double latitudeD = location.getLatitude();
                tvLat.setText(latitudeD.toString());
                Double longitudeD = location.getLongitude();
                tvLon.setText(longitudeD.toString());
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {
            }
            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mlocManager.requestLocationUpdates
                (LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText("GPS is turned on...");
        } else {
            tvOut.setText("GPS is not turned on...");
        }
    }
}
