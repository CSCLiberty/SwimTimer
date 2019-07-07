package com.example.swimtimer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class TimingService extends Service {
    public com.example.swimtimer.StopwatchManager myManager = new StopwatchManager();


    private final MyBinder mBinder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myManager.initStopwatches(6);
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    //public static  StopwatchManager getMyManager() {
        //return myManager;
    //}

    public class MyBinder extends Binder {
        TimingService getService() {
            return TimingService.this;
        }
    }
}


