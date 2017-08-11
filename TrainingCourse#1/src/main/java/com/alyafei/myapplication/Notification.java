
package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void showNoti(View view) {

        int notCounter=0;
        NewMessageNotification newMessageNotification= new NewMessageNotification();
        newMessageNotification.notify(this,"hi there",notCounter++);


    }
}
