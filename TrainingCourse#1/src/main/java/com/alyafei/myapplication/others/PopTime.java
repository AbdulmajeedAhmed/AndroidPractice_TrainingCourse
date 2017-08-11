package com.alyafei.myapplication.others;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alyafei.myapplication.Main7Activity_TimePicker;
import com.alyafei.myapplication.R;

/**
 * Created by DELL on 3/19/2017.
 */

public class PopTime extends DialogFragment implements View.OnClickListener {
// you can implement OnClickListener by two ways: one is the known one and the other is explained in this class.
    View view;
    TimePicker timePicker;
    Main7Activity_TimePicker main;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        main = (Main7Activity_TimePicker) getActivity();
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.pop_time, container, false);
        timePicker=(TimePicker) view.findViewById(R.id.timePicker);
        Button doneButton=(Button)view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        String time=timePicker.getHour()+":"+timePicker.getMinute();
String check=timePicker.getHour()+"";
        Log.d("Time is:",check);

        if(check.equals("3"))
        Toast.makeText(main,"Error",Toast.LENGTH_SHORT).show();
        else {
            this.dismiss();
            main.setTime(time);
        }

    }
    private void createDialoge(){
       main.error();
    }
}
