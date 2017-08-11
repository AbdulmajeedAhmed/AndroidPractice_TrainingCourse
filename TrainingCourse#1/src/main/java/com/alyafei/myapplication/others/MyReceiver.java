package com.alyafei.myapplication.others;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by DELL on 3/23/2017.
 */

public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle= intent.getExtras();

        if(intent.getAction().equalsIgnoreCase("example.Broadcast")){
            String msg=bundle.getString("msg");
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
