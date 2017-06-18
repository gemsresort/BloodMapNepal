package com.example.saroj.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartingPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    Intent openMainActivity = new Intent("com.example.saroj.layout.MainActivity");
                    startActivity(openMainActivity);

                }
            }

        };
        timer.start();
    }
    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
