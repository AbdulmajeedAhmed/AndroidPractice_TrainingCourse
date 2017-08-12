package com.alyafei.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarAndThread extends AppCompatActivity {
    SeekBar seekBar;
    TextView countertxt;
    int maxCounter=9;
    Thread thread;
    boolean inRunning;
    int counterUp=0;
    boolean reset=false;
    MyHandler myHandler; // another way to deal with the thead..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_and_thread);
        seekBar= (SeekBar)findViewById(R.id.seekBar);
        myHandler= new MyHandler();
        countertxt= (TextView)findViewById(R.id.countertxt);
        countertxt.setText("Counter= "+counterUp);
        seekBar.setMax(maxCounter);
    }

    public void buStart(View view) {
        inRunning=true;
        thread=new myThread();
        thread.start();
    }

    public void buStop(View view) {
        inRunning=false;
    }

    public void reset(View view) {
    reset=true;
        counterUp=0;
        countertxt.setText("Counter= "+counterUp);
    }

    private class myThread extends Thread{
        @Override
        public void run() {
            while (inRunning){
                if(reset) { // reset case.
                    reset();
                }
               if(counterUp<=maxCounter){  //count up case
                  // countUp();// #1 way using runOnUiThread(new Runnable(){}
                   handleThread(); //#2 way using Handler class.
               }
            }
        }
        // this is the first way to deal with this situation.
        private void countUp() {
            runOnUiThread(new Runnable() { // to access the UI thread
                @Override
                public void run() {
                    seekBar.setProgress(counterUp);
                    countertxt.setText("Counter= "+counterUp);
                }
            });
            counterUp++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        private void reset() {
               // counterUp=0;    // deal with reset
                runOnUiThread(new Runnable() { // to access the UI thread
                    @Override
                    public void run() {
                        countertxt.setText("Counter= "+counterUp);
                    }
                });
                reset=false;
            counterUp=0;
        }

        private void handleThread() {
            // the difference is with the follwoing 5 lines..
            Message msg= myHandler.obtainMessage();
            Bundle bundle=new Bundle();
            bundle.putInt("counter",counterUp);
            msg.setData(bundle);
            myHandler.sendMessage(msg);
            counterUp++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } // #1 way you can use  runOnUiThread(new Runnable() to send data to UI thread


    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            int counter=msg.getData().getInt("counter");
            seekBar.setProgress(counter);
            countertxt.setText("Counter= "+counterUp);
        }
    } //#2 way to send message to the UI thread using the handler class.


}
