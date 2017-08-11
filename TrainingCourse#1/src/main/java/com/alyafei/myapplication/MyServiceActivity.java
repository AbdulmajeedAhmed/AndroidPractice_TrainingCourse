package com.alyafei.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alyafei.myapplication.ServicesClasses.MyService;

public class MyServiceActivity extends AppCompatActivity {
    MyService myService;
    boolean isServiceBounded=false;

    // to start connection with the service.
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder=(MyService.LocalBinder)service; // assign the binder
            myService=binder.getService(); // get the service using the binder
            isServiceBounded=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBounded = false;
        }
    };
    //  end of ServiceConnection()



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent IntentService= new Intent(this, MyService.class);
        bindService(IntentService,mConnection,Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();

        if(isServiceBounded){
            unbindService(mConnection); // it is your responsiblity to unbind this service.
            isServiceBounded=false;
        }
    }


    public void getService(View view) {
        if(isServiceBounded){ // if service is on
            String msg=myService.geTMessageFromServiceToUI();
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }

    }
}
