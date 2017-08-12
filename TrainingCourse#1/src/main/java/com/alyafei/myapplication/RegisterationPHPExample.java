package com.alyafei.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alyafei.myapplication.others.Item;
import com.alyafei.myapplication.others.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RegisterationPHPExample extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;
    boolean register;
    boolean login;
    boolean displayUsers;
    ListView listViewUsers;
    private ArrayList<User> users;
    private Adapter adapter;
    MyHandler  myHandler;
    private ProgressDialog progressDialog;;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_phpexample);
        myHandler= new MyHandler();
        etUserName= (EditText)findViewById(R.id.txvUsernamePHP);
        etPassword=(EditText)findViewById(R.id.txvPasswordPHP);
        // get the list view
        listViewUsers=(ListView)findViewById(R.id.listViewUsers);
        register=false;
        login=false;
        displayUsers=false;
        progressBar= (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
    }

    public void register(View view) {
        //192.168.150.2  IPv4
        String url="http:/192.168.150.2/phpFiles/AddUser.php?Username="+ etUserName.getText().toString()+"&Password="+ etPassword.getText().toString();
        Log.d("msg",url);
        register=true;
        new MyAsyncTask().execute(url);
    }

    public void login(View view) {
        //be careful that 192.168.48.2 is dynamic.. so do ipconfig and look at it every time you have timeout error.
        String url="http:/192.168.150.2/phpFiles/login.php?Username="+ etUserName.getText().toString()+"&Password="+ etPassword.getText().toString();
        Log.d("msg",url);
        login=true;
        new MyAsyncTask().execute(url);
    }

    public void displayUsers(View view) {
        // send request to the server
        String url="http://192.168.150.2/phpFiles/list.php";
        displayUsers=true;//must be before next line..
        new MyAsyncTask().execute(url);

    }



    int jumpTime = 0;
  // another way to deal with the thead..
    public void download(View view) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Downloading Music");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(true);
        progressDialog.setSecondaryProgress(44);
        progressDialog.show();
        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                while(jumpTime <totalProgressTime) {

                    try {
                        //handleThread(); //#2 way using Handler class.

                        sleep(400);
                        jumpTime += 5;
                        Log.d("jumpTime",""+jumpTime);
                        runOnUiThread(new Runnable() { // run in parallel with the one who have it ..
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } runOnUiThread(new Runnable() { // after reaching 100, disable the bar.
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }


        };
        t.start();
    }

    private void handleThread() {
        // the difference is with the follwoing 5 lines..
        Message msg= myHandler.obtainMessage();
        Bundle bundle=new Bundle();
        bundle.putInt("counter",jumpTime);
        msg.setData(bundle);

        myHandler.sendMessage(msg);
        jumpTime=jumpTime+5;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MyHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        Log.d("handler",""+msg.getData().getInt("counter"));
        int counter=msg.getData().getInt("counter");
        progressDialog.setProgress(counter);
    }
}



    public class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                Log.d("msg1","test");
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(3000);//set timeout to 5 seconds

                try {
                    //getting the response data
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    NewsData = ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData);
                    Log.d("msg","publishProgress");
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("msg","Exception");
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {

            try {
                if(register){ // the user clicks register
                    Log.d("msg","register");
                    JSONObject json = new JSONObject(progress[0]);
                    //display response data
                    Toast.makeText(getApplicationContext(), json.getString("msg"), Toast.LENGTH_LONG).show();
                    register=false;

                }else if(login){ // user clicks register

                    if(progress[0].toString().equalsIgnoreCase("No entries."))
                    {
                        Toast.makeText(getApplicationContext(),"Username is not existed. Please register first", Toast.LENGTH_LONG).show();
                    }else{
                        JSONArray jsonArray= new JSONArray(progress[0]); //progress[0] it is array...
                        JSONObject user= jsonArray.getJSONObject(0); // because it is only one user .. or loop through it..
                        Toast.makeText(getApplicationContext(),"Welcome "+user.getString("Username"), Toast.LENGTH_LONG).show();
                    }
                    login=false;

                }else if(displayUsers){ // user clicks display Users
                    Log.d("msg","displayUsers");
                    // convert get the info from json
                    JSONArray jsonArray= new JSONArray(progress[0]); //progress[0] it is array...
                    JSONObject user; //
                    int ID;
                    String username;
                    String password;
                    users =new ArrayList<User>(); // create the list
                    for(int i=0;i<jsonArray.length();i++){ // array of users..
                        user= jsonArray.getJSONObject(i);
                        // get the data
                        ID=user.getInt("ID");
                        username=user.getString("Username");
                        password =user.getString("Password");
                        users.add(new User(String.valueOf(ID),username,password));
                    }
                    adapter=  new Adapter(users); // create the adapter and pass to it our arrayList.
                    listViewUsers.setAdapter(adapter);
                    displayUsers=false; //flag.
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

        protected void onPostExecute(String result2) {


        }


    }
    // This class needs to be here not in seprate class because it need the inheritance AppCompatActivity

    private class Adapter extends BaseAdapter {

        private ArrayList<User> users;

        public Adapter(ArrayList<User> users){
            this.users=users;
        }
        @Override
        public int getCount() {
            return users.size();
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
            View view=inflater.inflate(R.layout.users_items_layout,null);

            // use each item
            final User user=users.get(position);

            //get the views of the layout you designed...
            TextView txvID=(TextView)view.findViewById(R.id.txvID);
            TextView txvUsername=(TextView)view.findViewById(R.id.txvUsername);
            TextView txvPassword=(TextView)view.findViewById(R.id.txvPassword);

            // this is for the primary layout that has the list view
            TextView usersTitle=(TextView)findViewById(R.id.usersTitle);
            usersTitle.setText("Users");

            // set the data
            txvID.setText("ID: "+user.ID);
            txvUsername.setText("User: "+user.Username);
            txvPassword.setText("Password: "+user.Password);
            adapter.notifyDataSetChanged();

            return view;
        }
    }

        // this method convert any stream to string
    public static String ConvertInputToStringNoChange(InputStream inputStream) {
        Log.d("msg2","ConvertInputToStringNoChange");
        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String linereultcal="";
        try{
            while((line=bureader.readLine())!=null) {
                linereultcal+=line;
            }
            inputStream.close();
        }catch (Exception ex){}
        return linereultcal;
    }
}
