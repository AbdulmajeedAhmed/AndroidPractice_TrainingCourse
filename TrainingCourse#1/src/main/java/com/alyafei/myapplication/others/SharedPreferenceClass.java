package com.alyafei.myapplication.others;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DELL on 3/20/2017.
 */

public class SharedPreferenceClass {
    SharedPreferences sharedPreferences;

    public SharedPreferenceClass(Context context){
        sharedPreferences=context.getSharedPreferences("myPrefe",Context.MODE_PRIVATE);
    }
    public void saveData(String username,String password){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Username",username);
        editor.putString("Password",password);
        editor.commit();
    }
    public String loadData(){
        String content=sharedPreferences.getString("Username","No one with this username");
        content+=sharedPreferences.getString("Password","No password");
        return  content;
    }
}
