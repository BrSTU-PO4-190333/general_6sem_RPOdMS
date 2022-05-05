package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab10__geolocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Date;

public class MainActivity extends Activity {
    TextView tvOut;
    TextView tvLon;
    TextView tvLat;
    TextView tvTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.textView_status);
        tvLon = findViewById(R.id.textView_longitude);
        tvLat = findViewById(R.id.textView_latitude);
        tvTim = findViewById(R.id.textView_time);

        int permissionStatus_ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionStatus_ACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        int permissionStatus_ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionStatus_ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitudeD = location.getLatitude();
                String latitideS = Double.toString(latitudeD);
                tvLat.setText(latitideS);

                double longitudeD = location.getLongitude();
                String longitudeS = Double.toString(longitudeD);
                tvLon.setText(longitudeS);

                Date DateD = new Date(location.getTime());
                String DateS = DateD.toString();
                tvTim.setText(DateS);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText(R.string.GPS_on);
        }

        if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut.setText(R.string.GPS_off);
        }
    }
}
