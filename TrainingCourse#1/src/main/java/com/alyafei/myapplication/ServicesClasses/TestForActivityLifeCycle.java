package com.alyafei.myapplication.ServicesClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alyafei.myapplication.Main6Activity_Spinner;
import com.alyafei.myapplication.R;

public class TestForActivityLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_life_cycle);
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart()",Toast.LENGTH_SHORT).show();
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume()",Toast.LENGTH_SHORT).show();
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause()",Toast.LENGTH_SHORT).show();

    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop()",Toast.LENGTH_SHORT).show();

    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show();
    }

    public void goToNextActivity(View view) {
        Intent intent = new Intent(this, Main6Activity_Spinner.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart()",Toast.LENGTH_SHORT).show();
    }
}
