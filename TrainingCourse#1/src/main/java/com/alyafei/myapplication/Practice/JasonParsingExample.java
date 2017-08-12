package com.alyafei.myapplication.Practice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alyafei.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class JasonParsingExample extends AppCompatActivity {
     static LinearLayout myRootLinearLayout;
     ArrayList<HashMap<String, String>> contactList; // needs to public, so we can access it from inner classes.
     RecyclerView myRecyclerView;
     CustomAdapter customAdapter;
     ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason_parsing_example);
        // ge the root layout..
        myRootLinearLayout = (LinearLayout) findViewById(R.id.myRootLinear);

        contactList= new ArrayList<HashMap<String, String>>(); // create first in order to access it later..

        // prepare
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        //myRecyclerView.setLayoutManager(new GridLayoutManager(this,2)) // // other way of presentation.
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));


      /*  // Dummy, save contact info in hash
        HashMap<String, String> contact = new HashMap<>();
        contact.put("email","sss@");
        contact.put("phone","4324243");
        contactList.add(contact);*/
        progressBar= (ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);

        //Toast.makeText(this,getResources().getString(R.string.jasonEx),Toast.LENGTH_LONG).show();
       // Log.d("jsonTest",getResources().getString(R.string.jasonEx));
        // start background process
        new GetContacts(this).execute();
    }

    private class GetContacts extends AsyncTask<Void,Void,Void>{
        Context context; // form the main activity..
        String jsonResponse;;
        ProgressDialog progressDialog;
        public GetContacts(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // Snackbar.make(JasonParsingExample.myRootLinearLayout,"Jason data is downloaded...",Snackbar.LENGTH_SHORT).show();
            Toast.makeText(context, "Jason data is downloaded..", Toast.LENGTH_SHORT).show();
            progressDialog = ProgressDialog.show(context, "", "Downloading Image ....");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Bellow is the way to connect to server and get json reponse, but instead now use it as string name ready to use..
           /* MyHttpHandler myHttpHandler = new MyHttpHandler();
            String url= "http://api.androidhive.info/contacts/"; // imagine it is a page on server.. You need to see thr json before handeling it.

             jsonResponse = myHttpHandler.makeServiceCall(url);*/

            jsonResponse="{\n" +
                    "    \"contacts\": [\n" +
                    "        {\n" +
                    "                \"id\": \"c200\",\n" +
                    "                \"name\": \"Ravi Tamada\",\n" +
                    "                \"email\": \"ravi@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c201\",\n" +
                    "                \"name\": \"Johnny Depp\",\n" +
                    "                \"email\": \"johnny_depp@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c202\",\n" +
                    "                \"name\": \"Leonardo Dicaprio\",\n" +
                    "                \"email\": \"leonardo_dicaprio@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c203\",\n" +
                    "                \"name\": \"John Wayne\",\n" +
                    "                \"email\": \"john_wayne@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c204\",\n" +
                    "                \"name\": \"Angelina Jolie\",\n" +
                    "                \"email\": \"angelina_jolie@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c205\",\n" +
                    "                \"name\": \"Dido\",\n" +
                    "                \"email\": \"dido@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c206\",\n" +
                    "                \"name\": \"Adele\",\n" +
                    "                \"email\": \"adele@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c207\",\n" +
                    "                \"name\": \"Hugh Jackman\",\n" +
                    "                \"email\": \"hugh_jackman@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c208\",\n" +
                    "                \"name\": \"Will Smith\",\n" +
                    "                \"email\": \"will_smith@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c209\",\n" +
                    "                \"name\": \"Clint Eastwood\",\n" +
                    "                \"email\": \"clint_eastwood@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c2010\",\n" +
                    "                \"name\": \"Barack Obama\",\n" +
                    "                \"email\": \"barack_obama@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c2011\",\n" +
                    "                \"name\": \"Kate Winslet\",\n" +
                    "                \"email\": \"kate_winslet@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c2012\",\n" +
                    "                \"name\": \"Eminem\",\n" +
                    "                \"email\": \"eminem@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

            if(jsonResponse!=null){
                // handle the json string now .. inside try and catch....
                try{
                    JSONObject jsonObj= new JSONObject(jsonResponse);

                    // Getting JSON Array node
                    JSONArray contacts=jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i=0; i<contacts.length();i++){
                        JSONObject ob= contacts.getJSONObject(i); //ob is first contact..
                        String id = ob.getString("id");
                        String name = ob.getString("name");
                        String email = ob.getString("email");
                        String address = ob.getString("address");
                        String gender = ob.getString("gender");

                        // Phone node is JSON Object, check it ...
                        JSONObject phone=ob.getJSONObject("phone");
                        String phoneNumber = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>(); // remember that we create recycler view to have contacts, and the arraylist was of type ArrayList<HashMap<String, String>>

                        // adding each child node to HashMap key => value , i just want to values only..
                        contact.put("email",email);
                        contact.put("phone",phoneNumber);

                        // adding contact to contact list
                        contactList.add(contact);

                        // end of the background process..
                    }


                }catch (final JSONException e){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           /* Snackbar.make(JasonParsingExample.myRootLinearLayout,
                                    "Json parsing error: " + e.getMessage(),
                                    Snackbar.LENGTH_LONG).show();*/
                           Log.e("JSONException",e.getMessage());
                            Toast.makeText(context,  "Json parsing error--", Toast.LENGTH_LONG).show();
                            Log.e("jsonObj",jsonResponse);
                        }
                    });

                }



            }else{
                runOnUiThread(new Runnable() { // to access the ui thread and talk to the user. otherwise you can not access from this function.
                    @Override
                    public void run() {
                    /*    Snackbar.make(JasonParsingExample.myRootLinearLayout,"Couldn't get json from server. Check LogCat for possible errors!" +
                                "",Snackbar.LENGTH_SHORT).show();*/
                        Toast.makeText(context, "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // as ArrayList<String>
            customAdapter= new CustomAdapter(context,contactList); // needs to pass the context..
            myRecyclerView.setAdapter(customAdapter);
            progressBar.setVisibility(View.GONE);
           // progressDialog.dismiss();
        }



        private class  MyHttpHandler{
            private  final String TAG = MyHttpHandler.class.getSimpleName();

            public String makeServiceCall(String myUrl){ // the returned string should be a jsonStr response..
                String response = null;
                try{
                    URL url = new URL(myUrl);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");

                    // read the response from the connection.
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    // convert the stream to string
                    response= convertStreamToString(in);

                }  catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                }



                return response;
            }

            private String convertStreamToString(InputStream in) {
              /*  BufferedReader bf= new BufferedReader(new InputStreamReader(in));
                String line ;
                String newString="";
                try{
                    while((line=bf.readLine())!=null) {
                        newString+=line;
                    }
                    in.close();
                }catch (Exception ex){}
                return newString;*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return sb.toString();
            }

        }
    } //class GetContacts

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList arrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, String> hashMap=(HashMap) arrayList.get(position); // the element inside the arraylist.

        holder.email.setText(hashMap.get("email"));
        holder.phone.setText(hashMap.get("phone"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView email;
        TextView phone;

        public ViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.contactEmail);
            phone = (TextView) itemView.findViewById(R.id.contactPhone);
        }


    }

}



}



