package com.alyafei.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

/**
 * Always make sure to disable sensors you don't need, especially when your
 * activity is paused. Failing to do so can drain the battery in just a few
 * hours. Note that the system will not disable sensors automatically when
 * the screen turns off.
 */
public class LightSensor extends AppCompatActivity implements SensorEventListener {
    Sensor sensor;
    SensorManager msensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);
        msensorManager= (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=msensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


    @Override
    protected void onResume() {
        super.onResume();
        msensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        msensorManager.unregisterListener(this);
    }

    boolean isrunning=false;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]<10 && isrunning==false){ // < 10 = dark ,, >40 = light
            isrunning=true;
            Toast.makeText(this,"Hi, sensor",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
