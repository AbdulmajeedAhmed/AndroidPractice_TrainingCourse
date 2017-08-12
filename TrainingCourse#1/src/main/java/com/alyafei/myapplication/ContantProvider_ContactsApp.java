package com.alyafei.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alyafei.myapplication.others.ContanctItem;
import com.alyafei.myapplication.others.Item;

import java.util.ArrayList;

public class ContantProvider_ContactsApp extends AppCompatActivity {
    private ArrayList<ContanctItem> itemsList;
    private Adapter adapter;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contant_provider__contacts_app);
        CheckUserPermsions();
    }

    void CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
        ReadContacts();//
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ReadContacts(); //gps call
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

    void ReadContacts(){
        itemsList= new ArrayList<ContanctItem>();

     // String selection=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"like '%h%'"; /// if you wanna make selection

        //ContactsContract.CommonDataKinds.Phone.CONTENT_URI is the shared data for contacts.

        //loading the items.
        Cursor cursor= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                ,null,null,null,null);

        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            itemsList.add(new ContanctItem(name,phoneNumber));

        }


        // getting the list view and give it the adapter.
        ListView listViewItems=(ListView)findViewById(R.id.listViewItems);
        adapter= new Adapter(itemsList);
        listViewItems.setAdapter(adapter);

    }

    private class Adapter extends BaseAdapter {

        private ArrayList<ContanctItem> listOfItems;


        public Adapter(ArrayList<ContanctItem> listOfItems){
            this.listOfItems=listOfItems;
        }
        @Override
        public int getCount() {
            return listOfItems.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //link with the layout you design
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.contact_item,null);

            // use each item
            final ContanctItem item=listOfItems.get(position);
            //get the views
            TextView name=(TextView)view.findViewById(R.id.contactEmail);
            TextView number=(TextView)view.findViewById(R.id.contactPhone);

            //put in them the values of the item
            name.setText(item.Name);
            number.setText(item.PhoneNumber);

            return view;
        }
    }
}
