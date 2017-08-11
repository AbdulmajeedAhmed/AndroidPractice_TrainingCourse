package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alyafei.myapplication.others.SharedPreferenceClass;

public class SharedPreferences extends AppCompatActivity {
    EditText username;
    EditText password;
    SharedPreferenceClass sharedPreferenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrences);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        sharedPreferenceClass= new SharedPreferenceClass(this);
    }
    public void buRegister(View view) {
        sharedPreferenceClass.saveData(username.getText().toString(),password.getText().toString());
        Toast.makeText(this,"You've registered successfully",Toast.LENGTH_SHORT).show();
    }

    public void buLoad(View view) {
        Toast.makeText(this,sharedPreferenceClass.loadData(),Toast.LENGTH_SHORT).show();
    }
}
