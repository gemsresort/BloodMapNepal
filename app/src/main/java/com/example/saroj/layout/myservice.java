package com.example.saroj.layout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class myservice extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Intent(this, ActivityUserProfile.class);

        //stopSelf();
        return START_STICKY;


    }

   // @Nullable
    @Override
    public IBinder onBind(Intent intent) {

          return null;
    }

    @Override
    public void onDestroy() {
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000 * 15),
                PendingIntent.getService(this, 0, new Intent(this, ActivityUserProfile.class), 0)
        );
    }
}
