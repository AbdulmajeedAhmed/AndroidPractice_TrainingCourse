package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    LayoutInflater inflater;
    View view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        inflater= getLayoutInflater();
        view1=inflater.inflate(R.layout.toastshow,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.setting:
                Toast.makeText(this,"Setting is clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(this,"Search is clicked",Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showToast(View view) {
        createToast("Dont do it!");
    }

    private void createToast(String msg) {
        TextView name=(TextView)view1.findViewById(R.id.name);
        name.setText(msg);
        Toast toast= new Toast(getApplicationContext());
        toast.setView(view1);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public void test(View view) {
        createToast("Test");
    }
}
