package com.example.hotelconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db" , null , 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table Angajati(username TEXT primary key ,nume TEXT , prenume TEXT , email TEXT  , password TEXT , status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldV, int newV) {
        myDB.execSQL("drop Table if exists Angajati");
    }
    public String generateUsername(String nume , String prenume)
    {
        return prenume.toLowerCase() + "." + nume.toLowerCase();
    }
    public Boolean insertData(String nume , String prenume , String email ,String password , String status)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume" , nume);
        contentValues.put("prenume" , prenume);
        contentValues.put("email" , email);
        contentValues.put("username" , generateUsername(nume,prenume));
        contentValues.put("password" , password);
        contentValues.put("status", status);
        long result = myDB.insert("Angajati" , null , contentValues);
        if(result == -1)
        {
            return false;
        } else {return true;}

    }

    public void deleteData(String nume, String prenume){
        SQLiteDatabase myDB = this.getWritableDatabase();
        myDB.delete("Angajati","nume = ? and prenume = ?", new String[]{nume,prenume} );
        myDB.close();
    }
    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from angajati",null);
        return cursor;
    }


    public Boolean checkUsername(String username)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT *FROM Angajati where username = ?" , new String[]{username});
        if(cursor.getCount() > 0 )
        {
            cursor.close();
            return true;
        }
        else
            return false;
    }

    public Boolean checkStatus(String username, String stats)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor c = myDB.rawQuery("SELECT *FROM Angajati where username = ? and status = ?" ,new String[]{username,stats});
        if(c.getCount()>0){
            c.close();
            return true;
        }
        else{
            c.close();
            return false;
        }

    }

    public Boolean checkNumePrenume(String nume,String prenume)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT *FROM Angajati where nume = ? and prenume = ?" , new String[]{nume , prenume});
        if(cursor.getCount() > 0 )
        {
            cursor.close();
            return true;
        }
        else
            return false;
    }
    public Boolean checkUsernamePassword(String username , String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT *FROM Angajati where username = ? and password = ?" , new String[]{username , password});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        else
            return false;
    }
}
