package com.example.saroj.layout;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by shudeepslove on 8/18/2016.
 */
public class LocationT extends Service implements LocationListener {


    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final int TWO_MINUTES = 100 * 10;

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10; // 30 seconds

    private Context context;

    double latitude;
    double longitude;

    Location location = null;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    protected LocationManager locationManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.context = this;
        get_current_location();
//      Toast.makeText(context, "Lat"+latitude+"long"+longitude,Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onLocationChanged(Location location) {
        if ((location != null) && (location.getLatitude() != 0) && (location.getLongitude() != 0)) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();


            // DO ASYNCTASK
        }
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


    public Location get_current_location(){

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(!isGPSEnabled && !isNetworkEnabled){



        }else{
            if (isGPSEnabled) {

                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //                  Toast.makeText(context, "Latgps"+latitude+"long"+longitude,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            if (isNetworkEnabled) {

                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                if (locationManager != null) {

                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        //              Toast.makeText(context, "Latgps1"+latitude+"long"+longitude,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        return location;
    }
    public double getLatitude() {
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        if(location != null){
            longitude = location.getLongitude();
        }

        return longitude;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        if(locationManager != null){
            locationManager.removeUpdates(this);
        }
        super.onDestroy();
    }
    }

