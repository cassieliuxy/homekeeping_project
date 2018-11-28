package com.example.kerris.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;


public class Userdb extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PSW = "psw";
    public static final String COLUMN_ROLE = "role";

    public Userdb(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_USERS + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT, "+COLUMN_PSW+" TEXT,"+COLUMN_ROLE+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,user.getUserName());
        values.put(COLUMN_PSW,user.getPassword());
        values.put(COLUMN_ROLE,user.getRole());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public User findUser(String username, String role){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+" = \""+username+"\"" +" AND "+  COLUMN_ROLE+" = \""+role+"\"";
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();
        if(cursor.moveToFirst()){
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
            cursor.close();
        }
        else{
            user = null;
        }
        db.close();
        return user;
    }


    public User findUser2(String username, String passsword, String role){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+" = \""+username+"\"" +" AND "+  COLUMN_PSW+" = \""+passsword+"\""+ " AND "+  COLUMN_ROLE+" = \""+role+"\"";
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();
        if(cursor.moveToFirst()){
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
            cursor.close();
        }
        else{
            user = null;
        }
        db.close();
        return user;
    }

    public User findUser3(String role){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_USERS+" WHERE "+COLUMN_ROLE+" = \""+role+"\"";
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();
        if(cursor.moveToFirst()){
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
            cursor.close();
        }
        else{
            user = null;
        }
        db.close();
        return user;
    }
}
