package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void displayMessage(View view) {
        Button testButton= (Button)findViewById(R.id.testButton);
        testButton.setText("Clicked");
       // Toast.makeText(this,getString(R.string.msg).toString(),Toast.LENGTH_SHORT).show();// for one string
    // Toast.makeText(this,,Toast.LENGTH_SHORT).show();// for one string
        String arr[]=getResources().getStringArray(R.array.myArray);
        int i=0;
        while (i<arr.length){
            Toast.makeText(this,arr[i].toString(),Toast.LENGTH_SHORT).show();// for one string
            i++;
        }

    }
}
