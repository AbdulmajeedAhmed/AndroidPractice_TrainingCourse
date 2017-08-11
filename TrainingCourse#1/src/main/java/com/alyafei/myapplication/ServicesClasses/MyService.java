package com.alyafei.myapplication.ServicesClasses;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by DELL on 3/23/2017.
 */

//extends Service for services in background on LONG run and  you CAN  interact with UI directly.
public class MyService extends Service {

    private final Binder binder= new LocalBinder();

    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    /**
     *
     * @param intent
     * @return
     * The system calls this method when another component wants to bind with the service by calling bindService().
     * If you implement this method, you must provide an interface that clients use to communicate with the service,
       by returning an IBinder object.
     * You must always implement this method, but if you don't want to allow binding, then you should return null.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    public String geTMessageFromServiceToUI(){
        String msg="This is the a message from MyService";
        return msg;
    }
}
