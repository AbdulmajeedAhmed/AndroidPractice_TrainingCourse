package com.alyafei.myapplication.ServicesClasses;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by DELL on 3/23/2017.
 */

public class MyIntentService extends IntentService {

    // extends MyIntentService for services in background on SHORT run and you can NOT interact with UI directly.
    // extends Service for services in background on LONG run and  you CAN  interact with UI directly.

    public static boolean isRunning=false;
    public MyIntentService(){
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        while (isRunning){

         /*   you cant display any thing to user because this service is working in the background, so you need to pass
            data through BroadcastReceiver*/ //MyReceiver is the class we want..
            Intent broadCastIntent= new Intent();
            broadCastIntent.setAction("example.Broadcast"); //example.Broadcast is the name of the receiver.
            broadCastIntent.putExtra("msg","hello from SERVICE to broadcast receiver");
            sendBroadcast(broadCastIntent); // this is the difference .

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
