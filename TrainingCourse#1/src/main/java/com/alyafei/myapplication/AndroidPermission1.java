package com.alyafei.myapplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidPermission1 extends AppCompatActivity {
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_permission);
    }


    public void getLocation(View view) {
        CheckUserPermsions();

    }

    private void GETLocation() {
        display = (TextView) findViewById(R.id.display);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        display.setText("location lang: "+String.valueOf(location.getLongitude())+", and location lat: "+String.valueOf(location.getLatitude()));
    }

    void CheckUserPermsions(){
            if ( Build.VERSION.SDK_INT >= 23){
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED  ){
                    requestPermissions(new String[]{
                                    android.Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    return ;
                }
            }

        GETLocation();//

        }
        //get acces to location permsion
        final private int REQUEST_CODE_ASK_PERMISSIONS = 123;// you can put any thing ..



        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case REQUEST_CODE_ASK_PERMISSIONS:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        GETLocation(); //gps call
                    } else {
                        // Permission Denied
                        Toast.makeText( this,"ERROR! Permession is denied" , Toast.LENGTH_SHORT)
                                .show();
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

