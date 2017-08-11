package com.alyafei.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alyafei.myapplication.ServicesClasses.MyIntentService;

public class BroadcastReceiverAndIntentService extends AppCompatActivity {
Intent intentService; // to stop it if you want
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        // make it in separate method.
     /*   if(!MyIntentService.isRunning){
            MyIntentService.isRunning=true;
            intentService=new Intent(this,MyIntentService.class); // to go to class MyIntentService
            startService(intentService);
        }*/
    }
    private void startMyService() {
        /*
        There are 3 things in android:
        1)Intents
        2)Broadcast receiver
        3)Services
         */
        // for dealing with service
        if(!MyIntentService.isRunning){
            MyIntentService.isRunning=true;
            intentService=new Intent(this,MyIntentService.class); // to go to class MyIntentService
            startService(intentService);
        }

    }

    void stopMyService(){
        if( MyIntentService.isRunning){
            MyIntentService.isRunning=false;
            stopService(intentService);
        }
    }
//MyReceiver is the class we want..
    public void buSendBroadcast(View view) {
        Intent intent= new Intent();
        intent.setAction("example.Broadcast");//example.Broadcast is the name of the receiver.
        intent.putExtra("msg","hello from ACTIVITY to broadcast receiver");
        sendBroadcast(intent);
    }

    public void buStopService(View view) {
        stopMyService();
    }

    public void buStartService(View view) {
        startMyService();
    }
}
