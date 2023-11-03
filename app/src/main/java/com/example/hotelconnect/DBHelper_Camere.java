package com.example.hotelconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper_Camere extends SQLiteOpenHelper {

    public DBHelper_Camere(@Nullable Context context) {
        super(context, "Camere.db" , null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table Camere(camera TEXT primary key , status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldV, int newV) {
        myDB.execSQL("drop Table if exists Camere");
    }
    public Boolean insertData(String camera , String status)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("camera" , camera);
        contentValues.put("status", status);
        long result = myDB.insert("Camere" , null , contentValues);
        if(result == -1)
        {
            return false;
        } else {return true;}

    }
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from Camere",null);
        return cursor;
    }

    public Boolean checkStatus(String camera, String stats)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor c = myDB.rawQuery("SELECT *FROM Camere where camera = ? and status = ?" ,new String[]{camera,stats});
        if(c.getCount()>0){
            c.close();
            return true;
        }
        else{
            c.close();
            return false;
        }

    }
}
