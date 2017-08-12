package com.alyafei.myapplication.Practice;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alyafei.myapplication.R;

public class UI_Controls extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rootLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui__controls);

        Button button= (Button) findViewById(R.id.myControlButton);
       rootLayout= (RelativeLayout) findViewById(R.id.myRelativeLayoutTest);

        button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Snackbar.make(rootLayout,"Hi... how are you....?",Snackbar.LENGTH_SHORT).show();
            }
        });

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rootLayout,"Hi... how are you....?",Snackbar.LENGTH_SHORT).show();
            }
        });*/

    }
    int counter=0;
    @Override
    public void onClick(View view) {
        Snackbar.make(rootLayout,"Hi... how are youuuu....?",Snackbar.LENGTH_SHORT).show();
        NewMessageNotification msg= new NewMessageNotification();
        msg.notify(this,"hi",counter++);
    }
}
