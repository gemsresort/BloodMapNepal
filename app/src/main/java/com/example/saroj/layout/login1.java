package com.example.saroj.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shudeepslove on 6/26/2016.
 */
public class login1  extends AppCompatActivity implements View.OnClickListener {
    private static final String LOGIN_URL="http://192.168.1.111/registerdemo/login.php";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";


    private EditText editTextUsername;
    private EditText editTextPassword;

    private String username;
    private String password;
    Button b;
    UserSessionManager session;
    // private Button buttonlogin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        editTextUsername=(EditText)findViewById(R.id.editTextUsername);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        // buttonlogin1=(Button)findViewById(R.id.buttonLogin1);
        session = new UserSessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        b=(Button)findViewById(R.id.buttonLogin1);
        b.setOnClickListener(this);


        //buttonlogin1.setOnClickListener(this);

        //buttonlogin=(Button)findViewById(R.id.buttonLogin);
        //buttonlogin.setOnClickListener(this);





    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        if (username.equals("")) {
            Toast.makeText(this, "Please enter an username and password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(this, "Please enter an  password", Toast.LENGTH_LONG).show();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(login1.this, response, Toast.LENGTH_LONG).show();
                        if(response.trim().equals("sucess")){
                        openProfile();}
//                        if (response.trim().equals("success")) {
//                           openProfile();
//                        } else {
//                            Toast.makeText(login1.this, response, Toast.LENGTH_LONG).show();
//                           //openProfile();
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login1.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);

                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void openProfile(){
       // Intent i=new Intent(this,ActivityUserProfile.class);
     //   session.createUserLoginSession("Android Example",
         //       "androidexample84@gmail.com");

        session.createUserLoginSession(KEY_USERNAME,username);
        // Starting MainActivity
        Intent i = new Intent(getApplicationContext(), ActivityUserProfile.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

        finish();

    }



    @Override
    public void onClick(View v) {

        userLogin();
    }
}
