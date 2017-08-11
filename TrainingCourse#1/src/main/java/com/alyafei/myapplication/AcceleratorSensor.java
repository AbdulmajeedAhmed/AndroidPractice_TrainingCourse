package com.alyafei.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AcceleratorSensor extends AppCompatActivity   implements SensorEventListener {
    Sensor sensor;
    SensorManager msensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerator_sensor);
        msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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

    float xOld = 0;
    float yOld = 0;
    float zOld = 0;
    float desiredSpeed = 3000;
    long OldTime = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        long CurrentTime = System.currentTimeMillis();

        if ((CurrentTime - OldTime) > 100) { // i want the time to be longer a little bit.. not instantly ..

            long TimeDiff = CurrentTime - OldTime;
            OldTime = CurrentTime;

            float speed = Math.abs(x + y + z - xOld - yOld - zOld) / TimeDiff * 10000; /*10000 to convert to seconds*/
            if (speed > desiredSpeed) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
                Toast.makeText(getApplicationContext(), "shock!!", Toast.LENGTH_LONG).show();
                ;

            }
        }
        }

        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){

        }

}
