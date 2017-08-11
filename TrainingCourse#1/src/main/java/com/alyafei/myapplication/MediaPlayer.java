package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alyafei.myapplication.others.SongInfo;

import java.util.ArrayList;

public class MediaPlayer extends AppCompatActivity {
    SeekBar seekBar1;
    MyCustomAdapter Adapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
    }

    private class MyCustomAdapter extends BaseAdapter {
        ArrayList<SongInfo> fullsongpath;

        public MyCustomAdapter(ArrayList<SongInfo> fullsongpath) {
            this.fullsongpath = fullsongpath;
        }

        @Override
        public int getCount() {
            return fullsongpath.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();
            View    myView=  mInflater.inflate(R.layout.song_item, null);
            SongInfo s=fullsongpath.get(position);
            TextView song_name = (TextView)myView.findViewById(R.id.song_name);
            song_name.setText(s.song_name);

            TextView artist_name = (TextView)myView.findViewById(R.id.artist_name);
            artist_name.setText(s.artist_name);
            return myView;
        }
    }



}
