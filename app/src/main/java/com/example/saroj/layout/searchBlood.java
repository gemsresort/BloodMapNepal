package com.example.saroj.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by shudeepslove on 8/8/2016.
 */
public class searchBlood extends AppCompatActivity {

    public static final String REGISTERED_URL = "http://192.168.0.126/simplifiedcrud/getEmp.php";
   // private static final String REGISTERED_URL="http://192.168.0.114/volly/take_order.php";
    public static final String KEY_BOOODGROUP="bloodGroup";
    private String JSON_STRING,response;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        Intent intent = getIntent();
        final String bloodgroup = intent.getStringExtra("Editid_text11");





    }}