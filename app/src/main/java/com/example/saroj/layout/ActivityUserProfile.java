package com.androidhive.longitude;
//package com.example.saroj.layout;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityUserProfile extends AppCompatActivity  {
    private static final String TAG =ActivityUserProfile.class.getSimpleName() ;
    private TextView textView,textView1;
    // User Session Manager Class
    private static final String REGISTERED_URL="http://192.168.0.100/registerdemo/updatelocation.php";
    public static final String key_LAT="lat";
    public static final String key_LNG="lng";
    public static final String KEY_id="id";
    GPSTracker gps;
    public static final String KEY_LATITUDE = "lat2";
    public static final String KEY_LONGITUDE = "lng2";
    public static final String KEY_USERNAME = "username";


    Location mLastLocation;
    Marker mCurrentLocationMarker;
    LocationRequest mLocationRequest;
   private String username;
    private String lat2;
    private String lng2;


    UserSessionManager session;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        session = new UserSessionManager(getApplicationContext());

       TextView text = (TextView) findViewById(R.id.textViewUsername);

        TextView lblName = (TextView) findViewById(R.id.lblName);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        if (session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(UserSessionManager.KEY_NAME);
        String username = user.get(UserSessionManager.KEY_USERNAME);
        Log.i(TAG, "onCreate:" + username);

        lblName.setText(Html.fromHtml("Name: <b>" + username + "</b>"));
        getData();

    }

    private void getData() {
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(UserSessionManager.KEY_USERNAME);
        String url = Config.RETURN_LOCATION + username;

        Log.i(TAG, "onCreate1:" + username);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // loading.dismiss();

               // Log.i(TAG, "onCreate2:" + response);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityUserProfile.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        //String name="";
        // String address="";
        // String vc = "";
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);



                JSONObject collegeData = result.getJSONObject(0);
            String name = collegeData.getString(Config.KEY_NAME);
            username=String.valueOf(name);


                String old_lat = collegeData.getString(Config.KEY_ADDRESS);
                String old_lng = collegeData.getString(Config.KEY_VC);


                //String Output=name +"-" +address;
                // list.add(createEmployee("employee",Output));

               // Log.i(TAG, "onCreate1:" + lat);

            gps = new GPSTracker(ActivityUserProfile.this);
            if (gps.canGetLocation()) {

                final double latitude = gps.getLatitude();
                final double longitude = gps.getLongitude();
                gps.getAddress(latitude, longitude);

                lat2 = String.valueOf(latitude);
                lng2 = String.valueOf(longitude);}
Log.i(TAG,"SEE"+lat2  +lng2);

            if(old_lat!=lat2 && old_lng!=lng2){

               StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                            Toast.makeText(ActivityUserProfile.this, response, Toast.LENGTH_LONG).show();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override

                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ActivityUserProfile.this, error.toString(), Toast.LENGTH_LONG).show();

                            }}){
                    @Override
                    public Map<String, String> getParams(){
                        Map<String,String> params=new HashMap<String,String>();


                        HashMap<String, String> user = session.getUserDetails();
                      final   String username1 = user.get(UserSessionManager.KEY_USERNAME);


                        params.put(KEY_USERNAME, username);
                        params.put(KEY_LATITUDE, lat2);
                        params.put(KEY_LONGITUDE, lng2);


                        return params;

                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
                startActivity(new Intent(this,MainActivity.class));

            }





               // textView.setText("name"+address);
                //textView.setText("Name:\t" + name + "\nlng:\t" + address + "\nLatr:\t" + vc);






        } catch (JSONException e) {
            e.printStackTrace();
        }



       // lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));

        //Intent intent=getIntent();
      //  textView.setText("welcome user"+intent.getStringExtra(login.KEY_USERNAME));
    }



}
