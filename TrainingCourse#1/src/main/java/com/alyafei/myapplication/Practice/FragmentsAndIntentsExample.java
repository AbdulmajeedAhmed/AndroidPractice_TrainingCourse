package com.alyafei.myapplication.Practice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alyafei.myapplication.Practice.FragmentOne;
import com.alyafei.myapplication.Practice.FragmentTwo;
import com.alyafei.myapplication.R;

public class FragmentsAndIntentsExample extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_example);

        FloatingActionButton fl= (FloatingActionButton) findViewById(R.id.myFloatButton);

        final CoordinatorLayout rootLayout= (CoordinatorLayout)findViewById(R.id.myCoordinatorLayout2);

        Button bu= (Button) findViewById(R.id.batteryCheckButton) ;

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Dynamically register a receiver.
                IntentFilter intentFilter= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent butteryStatus=registerReceiver(null,intentFilter);

                // Are we charging / charged? Full or charging.
                int status = butteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING) ||
                        (status == BatteryManager.BATTERY_STATUS_FULL);


                // How are we charging? From AC or USB.
                int chargePlug = butteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

                if(usbCharge){
                    Toast.makeText(getApplicationContext(),"Mobile is charging on USB",
                            Toast.LENGTH_LONG).show();
                } else if(acCharge) {
                    Toast.makeText(getApplicationContext(),"Mobile is charging on AC",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Not charging",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rootLayout,"Hello",Snackbar.LENGTH_SHORT).show();
            }
        });

        AutoCompleteTextView text= (AutoCompleteTextView) findViewById(R.id.autoCom1);
        String[] languages=getResources().getStringArray(R.array.languages); //from xml
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,R.layout.layout_items);
        text.setAdapter(ad);
        text.setThreshold(1);


       /* fl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });*/
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

      /*  // Verify that there are applications registered to handle this intent
        // (resolveActivity returns null if none are registered)

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/


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


    /*   // 7 Built-in Intent to send SMS -- not working
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , "01234");
        smsIntent.putExtra("sms_body"  , "Test ");
        try {

            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }*/


        // 8
    /*    Log.i("Send email", "");
        String[] TO = {"Ali"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here...");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.d("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        */
        //showSettingsAlert();
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); // to launch camera in your app..
        startActivity(intent);
        /*
        ##### Intent type and description#####
        1 -	ACTION_IMAGE_CAPTURE_SECURE
        It returns the image captured from the camera , when the device is secured
        
        2 -	ACTION_VIDEO_CAPTURE
        It calls the existing video application in android to capture video

        3 -	EXTRA_SCREEN_ORIENTATION
        It is used to set the orientation of the screen to vertical or landscape

        4 -	EXTRA_FULL_SCREEN
        It is used to control the user interface of the ViewImage

        5 -	INTENT_ACTION_VIDEO_CAMERA
        This intent is used to launch the camera in the video mode

        6 -	EXTRA_SIZE_LIMIT
        It is used to specify the size limit of video or image capture size

*/
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    }



