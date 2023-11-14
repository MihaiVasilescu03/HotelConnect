package com.example.hotelconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class DBHelper_Camere extends SQLiteOpenHelper {

    public DBHelper_Camere(@Nullable Context context) {
        super(context, "Camere.db" , null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table Camere(camera TEXT primary key , status TEXT , obs TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldV, int newV) {
        myDB.execSQL("drop Table if exists Camere");
    }
    public Boolean insertData(String camera , String status, String obs)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("camera" , camera);
        contentValues.put("status", status);
        contentValues.put("obs", obs);
        long result = myDB.insert("Camere" , null , contentValues);
        if(result == -1)
        {
            return false;
        } else {return true;}

    }
    public List<CamereList> getData() {
        List<CamereList> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Camere", null);

        if (cursor.moveToFirst()) {
            do {
                String column1Value = cursor.getString(cursor.getColumnIndexOrThrow("camera"));
                String column2Value = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                String column3Value = cursor.getString(cursor.getColumnIndexOrThrow("obs"));
                CamereList data = new CamereList(column1Value, column2Value, column3Value);
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dataList;
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
