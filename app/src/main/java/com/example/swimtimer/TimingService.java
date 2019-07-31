package com.example.swimtimer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TimingService extends Service {
    private StopwatchManager myManager;
    private final MyBinder mBinder = new MyBinder();



    @Override
    public void onCreate(){
        super.onCreate();

        myManager = new StopwatchManager(6);
        Toast.makeText(getApplicationContext(), "service created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //myManager = new StopwatchManager();
        //myManager.initStopwatches(6);
        Toast.makeText(getApplicationContext(), "service started...", Toast.LENGTH_SHORT).show();
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    public IBinder onBind(Intent intent)
    {

        return mBinder;
    }



    public class MyBinder extends Binder {
        TimingService getService() {
            return TimingService.this;
        }

    }
    public StopwatchManager getMyManager()
    {
        return myManager;
    }


}


