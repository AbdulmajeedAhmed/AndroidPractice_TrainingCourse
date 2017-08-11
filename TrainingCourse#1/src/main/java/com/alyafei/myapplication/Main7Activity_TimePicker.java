package com.alyafei.myapplication;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alyafei.myapplication.others.PopTime;

public class Main7Activity_TimePicker extends AppCompatActivity {
    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7__time_picker);
    }

    public void setTime(String time){
        textViewTime=(TextView)findViewById(R.id.textViewTime);
        //Toast.makeText(this,time,Toast.LENGTH_SHORT).show();
        textViewTime.setText("Time->  "+time);
    }


    public void showTime(View view) {
       android.app.FragmentManager fm=getFragmentManager();
        PopTime p= new PopTime();
        p.show(fm,"dd");

    }

    public void deleteTime(View view) {
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation")
                .setMessage("Are you sure to delete the time?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textViewTime.setText("");
                    }
                }).setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    public void error(){
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle("Error..Choose rather than 3 again")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
