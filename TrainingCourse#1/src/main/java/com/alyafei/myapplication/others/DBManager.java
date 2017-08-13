package com.alyafei.myapplication.others;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

/**
 * Created by DELL on 3/20/2017.
 */

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName="Students";
    static final String TableName="Logins";
    public static final String ColID="ID";
    public static final String ColUserName="UserName";
    public static final String ColPassWord="Password";
    static final int DBVersion=1;

    public DBManager(Context context){
        DatabaseHelperUser db=new DatabaseHelperUser(context) ;
        sqlDB=db.getWritableDatabase();
        //sqlDB.close();

    }

    // create table Logins(ID integer primary key autoincrment, UserName text, Password text)
    static final String CreateTable=" CREATE TABLE IF NOT EXISTS " +TableName+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ ColUserName+
            " TEXT,"+ ColPassWord + " TEXT);";

    private static class DatabaseHelperUser extends SQLiteOpenHelper{
        Context context;
        public DatabaseHelperUser(Context context) {
            super(context, DBName, null, DBVersion);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table is created successfully", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF  EXISTS "+ TableName);
            onCreate(db);
            Toast.makeText(context,"Table is updated successfully ", Toast.LENGTH_LONG).show();
        }

    }



    public long Insert(ContentValues contentValues){
        long ID= sqlDB.insert(TableName,"",contentValues);
        //sqlDB.close();
        return ID;
    }

    //select username,Password from Logins where ID=1

    /**
     * To get records, cursor is a table with rows.
     * @param Projection
     * @param Selection
     * @param SelectionArgs
     * @param SortOrder
     * @return
     */
    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(TableName);
        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

    public int Delete(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableName,Selection,SelectionArgs);
        return count;
    }

    public  int Update(ContentValues values,String Selection,String[] SelectionArgs)
    {
        int count=sqlDB.update(TableName,values,Selection,SelectionArgs);
        return count;
    }

}
