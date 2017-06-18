package com.example.saroj.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final String REGISTERED_URL="http://192.168.1.111/registerdemo/signUp.php";
    private EditText edit_firstname,edit_lastname,edit_username,edit_password,edit_repassword,edit_bloodgroup;
    private EditText edit_dateofbirth,edit_home,edit_office,edit_phhome,edit_phoffice,edit_mobile;
    private RadioButton radiobtn;
    GPSTracker gps;



    public static final String KEY_LATITUDE = "lat2";
    public static final String KEY_LONGITUDE = "lng2";
    private GoogleApiClient client;


    private String lat2;
    private String lng2;

    //public final String FirstName,LastName,UserName,Password,RePassword,BloodGroup;
    //public final String DateOfBirth,Home,Office,PhHome,PhOffice,Mobile,RadioBtnGender;

    public static final String key_FirstName="FirstName";
    public static final String key_lastName="LastName";
    public static final String key_Username="UserName";
    public static final String key_Password="Password";
    public static final String key_Repassword="RePassword";
    public static final String key_BloodGroup="BloodGroup";
    public static final String key_DateOfBirth="DateOfBirth";
    public static final String key_Home="Home";
    public static final String key_Office="Office";
    public static final String key_PhHome="PhHome";
    public static final String key_PhOffice="PhOffice";
    public static final String key_Mobile="Mobile";




    private static final String TAG = MainActivity.class.getSimpleName();

  //  private static RadioGroup radiogrp_gender;
  //  private static RadioButton radiobtngender;
    private static Button btnsignuup ,btnalreadyregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_firstname = (EditText) findViewById(R.id.edit_FirstName);
        edit_lastname = (EditText) findViewById(R.id.edit_LastName);
        edit_username = (EditText) findViewById(R.id.edit_UserName);
        edit_password = (EditText) findViewById(R.id.edit_Password);
        edit_repassword = (EditText) findViewById(R.id.edit_Repassword);
        edit_bloodgroup = (EditText) findViewById(R.id.edit_BloodGroup);
        edit_dateofbirth = (EditText) findViewById(R.id.edit_DateOfBirth);
        edit_home = (EditText) findViewById(R.id.edit_Home);
       // edit_office = (EditText) findViewById(R.id.edit_Office);
        //edit_phhome = (EditText) findViewById(R.id.edit_PhHome);
        //edit_phoffice = (EditText) findViewById(R.id.edit_PhOffice);
        //edit_mobile = (EditText) findViewById(R.id.edit_Mobile);


      //  radiogrp_gender = (RadioGroup) findViewById(R.id.RadioGroup_Gender);

        btnalreadyregister = (Button) findViewById(R.id.btnAlreadyRegister);
        btnsignuup = (Button) findViewById(R.id.btnSignUp);


        btnsignuup.setOnClickListener(this);
        btnalreadyregister.setOnClickListener(this);
    }

    private void registerUser() {
        final String FirstName = edit_firstname.getText().toString().trim();
        final String LastName = edit_lastname.getText().toString().trim();
        final String UserName = edit_username.getText().toString().trim();
        final String Password = edit_password.getText().toString().trim();
        final String RePassword = edit_repassword.getText().toString().trim();
        final String BloodGroup = edit_bloodgroup.getText().toString().trim();
        final String DateOfBirth = edit_dateofbirth.getText().toString().trim();
        final String Home = edit_home.getText().toString().trim();
//        final String Office = edit_office.getText().toString().trim();
//        final String PhHome = edit_phhome.getText().toString().trim();
//        final String PhOffice = edit_phoffice.getText().toString().trim();
//        final String Mobile = edit_mobile.getText().toString().trim();


       // int selected_id = radiogrp_gender.getCheckedRadioButtonId();
        //radiobtngender = (RadioButton) findViewById(selected_id);
      //  radiobtn = (RadioButton) findViewById(selected_id);


     //   final String RadioBtnGender = radiobtn.getText().toString().trim();

        gps = new GPSTracker(SignUp.this);
        if (gps.canGetLocation()) {

            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();
            gps.getAddress(latitude, longitude);

            lat2 = String.valueOf(latitude);
            lng2 = String.valueOf(longitude);}

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUp.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put(key_FirstName,FirstName);
                params.put(key_lastName,LastName);
                params.put(key_Username,UserName);
                params.put(key_Password,Password);
                params.put(key_Repassword,RePassword);
                params.put(key_BloodGroup,BloodGroup);
                params.put(key_DateOfBirth,DateOfBirth);
                params.put(key_Home,Home);
//                params.put(key_Office,Office);
//                params.put(key_PhHome,PhHome);
//                params.put(key_PhOffice,PhOffice);
//                params.put(key_Mobile,Mobile);
                params.put(KEY_LATITUDE, lat2);
                params.put(KEY_LONGITUDE, lng2);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        startActivity(new Intent(this,login1.class));

    }

    @Override
    public void onClick(View v) {

        if (v== btnsignuup){
            registerUser();
        }

        if (v==btnalreadyregister){
            //Intent startloginactivity=new Intent(MainActivity.this,loginActivity.class)startActivity(startloginactivity);

            startActivity(new Intent(this,login1.class));
        }


    }




}
