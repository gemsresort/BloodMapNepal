package com.example.saroj.layout;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class s extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.s);
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
        }


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;





                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(-34, 151);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {



        }



        public static final int  MY_PERMISSION_REQUEST_LOCATION=99;
        public void checkLocationpermission() {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                        } else {
                                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                        }

                }
        }




        @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }
}




//
//
//        Intent intent = getIntent();
//        final String id1 = intent.getStringExtra("id");
//
//
////String id = editTextId.getText().toString().trim();
//
//
//if (id1.equals("")) {
//        Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//        return;
//        }
//        // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
//
//        String url = Config.DATA_URL+id1;
//
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//@Override
//public void onResponse(String response) {
//        //loading.dismiss();
//        showJSON(response);
//        }
//        },
//        new Response.ErrorListener() {
//@Override
//public void onErrorResponse(VolleyError error) {
//        Toast.makeText(login.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
//        }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//        }
//
//private void showJSON(String response) {
//        //String name="";
//        // String address="";
//        // String vc = "";
//        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//
//        try {
//        JSONObject jsonObject = new JSONObject(response);
//        JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
//
//        for (int i = 0; i < result.length(); i++) {
//
//        JSONObject collegeData = result.getJSONObject(i);
//        String name = collegeData.getString(Config.KEY_NAME);
//        String address = collegeData.getString(Config.KEY_ADDRESS);
//        String vc = collegeData.getString(Config.KEY_VC);
//        Double address1=Double.parseDouble(address);
//        Double vc1=Double.parseDouble(vc);
//        LatLng ktm=new LatLng(address1,vc1);
//
//
//        // Add a marker in Sydney and move the camera
//        //  LatLng sydney = new LatLng(-34, 151);
//        //  mMap.addMarker(new MarkerOptions().position(ktm).title(name));
//        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));
//        MarkerOptions options = new MarkerOptions()
//        .position(ktm)
//        .title(name);
//        mMap.addMarker(options);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));
//
//
//        }
//        } catch (JSONException e) {
//        e.printStackTrace();
//        }
//        }