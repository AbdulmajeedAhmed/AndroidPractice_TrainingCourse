package com.alyafei.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alyafei.myapplication.others.User;
import com.alyafei.myapplication.others.DBManager;

import java.util.ArrayList;

public class SQLite extends AppCompatActivity {
    EditText username;
    EditText password;
    DBManager DBManager;
    String RecordID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        DBManager = new DBManager(this);
    }
    public void buRegister(View view) {
        ContentValues contentValues= new ContentValues();
        contentValues.put(DBManager.ColUserName,username.getText().toString());
        contentValues.put(DBManager.ColPassWord,password.getText().toString());
        long id=DBManager.Insert(contentValues);
        if(id>0)
            Toast.makeText(this,"User is added and id is: "+id,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Error.. user is not added: "+id,Toast.LENGTH_SHORT).show();

        username.setText("");
        password.setText("");
    }
    ArrayList<User> listnewsData = new ArrayList<User>();
    MyCustomAdapter myadapter;

    public void load(){

        listnewsData.clear();// to clear it after inserting to it every time.

        //String[] projections={"Username","Password"};
        // if you want all columns, write null.

       // String selectionArgs[]={"%"+username.getText().toString()+"%"};
        Cursor cursor=DBManager.query(null,null,null,DBManager.ColUserName); // you can do this only..
       // Cursor cursor=DBManager.query(null,"Username like ?",selectionArgs,DBManager.ColUserName);// every ? will be replaced with the selectionArgs[] element one after onther.

        String data="";
        if(cursor.moveToFirst()){
            do{
               /* data+=cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))+" "+
                        cursor.getString(cursor.getColumnIndex(DBManager.ColPassWord))+"::";*/
                // adding to list
                listnewsData.add(new User(cursor.getString(cursor.getColumnIndex(DBManager.ColID)),cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))
                        , cursor.getString(cursor.getColumnIndex(DBManager.ColPassWord))));

            }while (cursor.moveToNext());
        }


        myadapter=new MyCustomAdapter(listnewsData); //create the adapter and passing to it our list.
        ListView lsNews=(ListView)findViewById(R.id.LVNews);
        lsNews.setAdapter(myadapter);//intisal with data
    }
    public void buLoad(View view) {
        load();
    }

    public void buUpdate(View view) {
        ContentValues contentValues= new ContentValues();
        contentValues.put(DBManager.ColUserName,username.getText().toString());
        contentValues.put(DBManager.ColPassWord,password.getText().toString());
        String[] SelectionArgs={RecordID};
        int count=DBManager.Update(contentValues,"ID=?",SelectionArgs);
        if(count>0){
            Toast.makeText(this,"You've updated the recoed successfully. Please load again.: ",Toast.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
        }

    }


    //display new list
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<User> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<User> listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
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
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.listview_sqlite_design, null);

            final User s = listnewsDataAdpater.get(position);

            TextView txvID=( TextView)myView.findViewById(R.id.txvID);
            txvID.setText(s.ID);

            TextView txvUsername=( TextView)myView.findViewById(R.id.txvUsername);
            txvUsername.setText(s.Username);

            TextView txvPassword=( TextView)myView.findViewById(R.id.txvPassword);
            txvPassword.setText(s.Password);

            Button deleteElement = (Button) myView.findViewById(R.id.deleteElement);
            deleteElement.setOnClickListener(new View.OnClickListener() {
                String[] SelectionArgs={s.ID} ;
                @Override
                public void onClick(View v) {
                   int counter= DBManager.Delete("ID=?",SelectionArgs);
                    myadapter.notifyDataSetChanged();
                    if(counter>0)
                    load();
                }
            });

            Button updateElement = (Button) myView.findViewById(R.id.updateElement);
            updateElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username.setText(s.Username);
                    password.setText(s.Password);
                    RecordID=s.ID;

                }
            });

            return myView;
        }

    }
}
