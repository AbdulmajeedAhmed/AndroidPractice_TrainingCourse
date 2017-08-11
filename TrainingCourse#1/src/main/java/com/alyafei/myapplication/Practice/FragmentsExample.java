package com.alyafei.myapplication.Practice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.alyafei.myapplication.Practice.FragmentOne;
import com.alyafei.myapplication.Practice.FragmentTwo;
import com.alyafei.myapplication.R;

public class FragmentsExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_example);

        FloatingActionButton fl= (FloatingActionButton) findViewById(R.id.myFloatButton);

        final CoordinatorLayout rootLayout= (CoordinatorLayout)findViewById(R.id.myCoordinatorLayout2);

        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rootLayout,"Hello",Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    public void changeFragment(View view) {
        Fragment fragment;

        if(view==findViewById(R.id.fragment1_button)){
            fragment= new FragmentOne();
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }
        else if(view==findViewById(R.id.fragment2_button)){
            fragment= new FragmentTwo();
            FragmentManager fm= getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.commit();
        }

    }

    public void testIntent(View view) {


        //1  Implicit Intent
        /*
        String q = "tutorialspoint";
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
        intent.putExtra(SearchManager.QUERY, q);
        startActivity(intent);*/
        /*
        Above example will search as tutorialspoint on android search engine
         and it gives the result of tutorialspoint in your an activity
         */

        //2  Implicit Intent
       /* Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE ); //Show settings for choosing wallpaper
        startActivity(intent);*/


       //3   Implicit Intent
      /*  Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, "");
        email.putExtra(Intent.EXTRA_SUBJECT, "");
        email.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(email, "Choose an email client from..."));*/

      //4    Implicit Intent
       /* Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://www.example.com"));
        startActivity(i);*/

       //5   Implicit Intent
      /*  Intent read1=new Intent();
        read1.setAction(android.content.Intent.ACTION_VIEW);
        read1.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivity(read1);*/


        //6
       /* Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("tel:9510300000"));
        startActivity(i);*/


    }
}
