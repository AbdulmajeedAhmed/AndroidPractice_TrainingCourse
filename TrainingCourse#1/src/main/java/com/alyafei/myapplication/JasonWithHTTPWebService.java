package com.alyafei.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JasonWithHTTPWebService extends AppCompatActivity {
    EditText cityEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jason_with_http_web_service);
        // jasonTest();  Example of jason for practice.. We use it
        cityEditText= (EditText)findViewById(R.id.cityEditText);



    }
    private void jasonTest() {
        String JsonFromURL="{" +
                "'info':{'name':'hussein','age':27 }," +
                "'jobs':" +
                "[" +
                "{'id':1, 'title':'developer','desc':'nyc'}," +
                "{'id':2, 'title':'developer','desc':'nyc'}," +
                "{'id':3, 'title':'developer','desc':'nyc'}" +
                "]" +
                "}";
        try{
            JSONObject json= new JSONObject(JsonFromURL);
            JSONObject info=json.getJSONObject("info");
            String name=info.getString("name");
            JSONArray jobs=json.getJSONArray("jobs");

            for(int i=0; i<jobs.length();i++){
                JSONObject job= jobs.getJSONObject(i);
                String jobTitle=job.getString("title");
                String jobDesc=job.getString("desc");
                ;            }
        }catch(Exception ex){

        };
    }
    public void test(String ... a){
    }


    public void buGetTime(View view) {
        if(cityEditText.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getApplicationContext(),"City name should not be empty.",Toast.LENGTH_LONG).show();
        }
        else
        {
            String URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + cityEditText.getText().toString() + "%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
            new MyAsyncTask().execute(URL);
        }
    }
    // get news from server

    private class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }
        @Override
        protected String doInBackground(String... params) { // you can't communicate with UI from here
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]); // depends on how many args you put when you write "new MyAsyncTask().execute(URL)";
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000);//set timeout to 7 seconds

                try {
                    //getting the response data from the server.
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    NewsData = ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData); // GO TO onProgressUpdate METHOD...
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) { // you can here communicate with UI
           // Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
            try {
                // if you want to see progress[0] jason file go to C:\Users\DELL\Downloads\Abdulmajid\NotepadFiles

                //Convert to jason ..
                JSONObject json= new JSONObject(progress[0]); //progress[0] have string json
                JSONObject query=json.getJSONObject("query");
                JSONObject results=query.getJSONObject("results");
                JSONObject channel=results.getJSONObject("channel");
                JSONObject astronomy=channel.getJSONObject("astronomy");
                String sunrise=astronomy.getString("sunrise");
                String sunset=astronomy.getString("sunset");
                if(sunrise.equalsIgnoreCase(""))
                    Toast.makeText(getApplicationContext(),"No city with the entered name."+sunset,Toast.LENGTH_LONG).show();
                else
                Toast.makeText(getApplicationContext(),"sunrise: "+sunrise+",sunset: "+sunset,Toast.LENGTH_LONG).show();


            } catch (Exception ex) {
            }


        }

        @Override
        protected void onPostExecute(String  result2){


        }

    }

    // this method convert any stream to string
    public static String ConvertInputToStringNoChange(InputStream inputStream) {
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
