package com.example.saroj.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shudeepslove on 8/2/2016.
 */
public class notification extends AppCompatActivity {

    private static final String TAG =null ;
    TextView textv;
    private static final String REGISTERED_URL = "http://192.168.0.112/fcm/message.php";
    public static final String KEY_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

       // Intent intent=getIntent();
        //String message=intent.getExtras().getString("constant.message");

        textv = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();


        final String message = intent.getStringExtra("message");
        textv.setText("Your message is: " + message + " ");


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(notification.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(notification.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                // params.put(KEY_USERNAME,username);
                params.put(KEY_MESSAGE,message);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    // Log.i(TAG,"onCreate:"+message);


}
