package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/8/2016.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewEmployee extends AppCompatActivity {

    String phone_num;
    Button btn_call;




    private TextView txtName;
    private TextView txtPhone;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id, pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        btn_call = (Button) findViewById(R.id.btn_call);



        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);
        pn = intent.getStringExtra(Config.EMP_PHONE);

        txtName = (TextView) findViewById(R.id.editTextId1);
        txtPhone = (TextView) findViewById(R.id.txtPhone);


        txtPhone.setText(pn);


        txtName.setText(id);

        findViewById(R.id.btn_call).setOnClickListener(new View.OnClickListener() {


            // public static final String TAG =MainActivity.class.getSimpleName() ;

            @Override
            public void onClick(View v) {

                phone_num=txtPhone.getText().toString();
                dialContactPhone(phone_num);

            }
        });
//
//        editTextId.setText(id);



    }

    private void dialContactPhone(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

}




