package com.example.mdmuktadir.sqlitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key,password text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table IF Exists user");

    }

    //inserting value in database
    public boolean insertData(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long insertValue= sqLiteDatabase.insert("user",null,contentValues);
        if (insertValue==-1){
            return false;
        }else
            return true;

    }

    //checking if email exists
    public boolean checkEmail(String email){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from user where email=?",new String[] {email} );
        if (cursor.getCount()>0){
            return false;
        }else
            return true;
    }

    //checking the email and password
    public boolean checkEmailPassword(String email,String password){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }else
            return false;
    }

}
