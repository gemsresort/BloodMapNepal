package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/2/2016.
 */
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Belal on 5/27/2016.
 */


//Class extending FirebaseInstanceIdService
public  class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
      private static final String REGISTERED_URL = "http://192.168.0.100/fcm/register.php";
     public static final String KEY_USERNAME = "username";
    public static final String KEY_TOKEN = "token";


    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);


    }

    public void sendRegistrationToServer(final String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
        //Intent startsecondactivity=new Intent(MyFirebaseInstanceIDService.this,SignUp.class);
       // startsecondactivity.putExtra("Editid_text11",token);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MyFirebaseInstanceIDService.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyFirebaseInstanceIDService.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
               // params.put(KEY_USERNAME,username);
                params.put(KEY_TOKEN,token);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }






}