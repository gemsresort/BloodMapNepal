package com.example.saroj.layout;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener,NavigationView.OnNavigationItemSelectedListener {
    private static final String REGISTERED_URL="http://192.168.0.126/registerdemo/updatelocation.php";
    private static final String TAG =null ;
    private static Button btnfirstaid, btnhelpline, btnsignup, btnlogin, btnaboutus,btnsearch;
    private EditText edit_search1;
    String bloodgroup,edit_search;
    GPSTracker gps;
    public static final String key_LAT="lat";
    public static final String key_LNG="lng";

    private String lat2;
    private String lng2;

    ViewPager viewPager;
    CustomSwipeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);


       // edit_search1=(EditText)findViewById(R.id.edit_Search);


        //startService(new Intent(MainActivity.this, ActivityUserProfile.class));
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        startService(new Intent(this, myservice.class));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
       //edit_search=(EditText)findViewById(R.id.edit_Search);
        //Intent searchactivity=new Intent(MainActivity.this,search.class);
       // searchactivity.putExtra("edit_search",bloodgroup);

       // startActivity(searchactivity);
//
//        btnfirstaid=(Button)findViewById(R.id.btnFirstAid);
//        btnhelpline=(Button)findViewById(R.id.btnHelpLine);
//        btnlogin=(Button)findViewById(R.id.btnLogIn);
//        btnsignup=(Button)findViewById(R.id.btnSignUp);
//        btnaboutus=(Button)findViewById(R.id.btnAboutUs);
        // OnClickButtonListenerForFirstAid();
        //OnClickButtonListenerForHelpline();
        OnClickButtonListenerForSignUp();
        OnClickButtonListenerForLogin();
        OnClickButtonListenerForSearch();
        //OnClickButtonListenerForAboutUs();

    }




//    public void OnClickButtonListenerForFirstAid() {
//        btnfirstaid=(Button)findViewById(R.id.btnFirstAid);
//        btnfirstaid.setOnClickListener(
//                new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//                        Intent intent = new Intent("com.example.saroj.layout.FirstAid");
//                        startActivity(intent);
//                    }
//                }
//        );
//    }




//    public void OnClickButtonListenerForHelpline() {
//        btnhelpline = (Button) findViewById(R.id.btnHelpLine);
//        btnhelpline.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent("com.example.saroj.layout.HelpLine");
//                        startActivity(intent);
//                    }
//                }
//        );
//    }

    public void OnClickButtonListenerForSignUp() {
        btnsignup = (Button) findViewById(R.id.btnSignUp);
        btnsignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.saroj.layout.SignUp");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickButtonListenerForLogin() {
        btnlogin = (Button) findViewById(R.id.btnLogIn);
        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.saroj.layout.login1");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnClickButtonListenerForSearch() {

        btnsearch = (Button) findViewById(R.id.search_button);
        btnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent("com.example.saroj.layout.listview");



                         // edit_search = edit_search1.getText().toString();
                      // edit_search=edit_search1.getText().toString().trim();
                        //intent.putExtra(Config.bloodgroup,edit_search);
                     // intent.putExtra("Editid_text11",edit_search);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onLocationChanged(Location location) {


        gps = new GPSTracker(MainActivity.this);
       double lat = location.getLatitude();
       double lng = location.getLongitude();
        gps.getAddress(lat, lng);
        lat2 = String.valueOf(lat);
        lng2 = String.valueOf(lng);

        Log.i(TAG,"onCreate:"+lat +"  " +lng);
        //LatLng latLng = new LatLng(latitude, longitude);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put(key_LAT,lat2);
                params.put(key_LNG,lng2);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
       // startActivity(new Intent(this,login.class));

    }




    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }  else if (id == R.id.firstAid) {
            Intent i = new Intent(this,image.class);
            startActivity(i);

        } else if (id == R.id.map) {
            Intent i = new Intent(this,hospital.class);
            startActivity(i);

        } else if (id == R.id.helpLine) {
            Intent i = new Intent(this,image.class);
            startActivity(i);

        } else if (id == R.id.aboutUs) {
            Intent i = new Intent(this,AboutUs.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }




//    public void OnClickButtonListenerForAboutUs() {
//        btnaboutus=(Button)findViewById(R.id.btnAboutUs);
//        btnaboutus.setOnClickListener(
//                new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//                        Intent intent = new Intent("com.example.saroj.layout.AboutUs");
//                        startActivity(intent);
//                    }
//                }
//        );
//    }




}
