package com.example.kerris.project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class ProviderTimedb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "providertimeDB.db";
    public static final String TABLE_PROVIDERTIME = "providertimes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";
    public static final String COLUMN_AVAILABLE = "available";

    public ProviderTimedb(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_PROVIDERTIME + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT, "+COLUMN_DAY+" TEXT, "+COLUMN_STARTTIME+" TEXT, "+COLUMN_ENDTIME+" TEXT,"+COLUMN_AVAILABLE+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDERTIME);
        onCreate(db);
    }

    public void addProviderTime(ProviderTime providertime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,providertime.getUserName());
        values.put(COLUMN_DAY,providertime.getDAY());
        values.put(COLUMN_STARTTIME,providertime.getStartTime());
        values.put(COLUMN_ENDTIME,providertime.getEndTime());
        values.put(COLUMN_AVAILABLE,providertime.getAvailable());
        db.insert(TABLE_PROVIDERTIME,null,values);
        db.close();
    }

    public ProviderTime findProviderTime (String username, String day, String starttime, String endtime, String available){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_PROVIDERTIME+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\""+"AND "+COLUMN_AVAILABLE+" = \""+available+"\"";
        Cursor cursor = db.rawQuery(query,null);
        ProviderTime providertime = new ProviderTime();
        if(cursor.moveToFirst()){
            providertime.setID(Integer.parseInt(cursor.getString(0)));
            providertime.setUserName(cursor.getString(1));
            providertime.setDAY(cursor.getString(2));
            providertime.setStartTime(cursor.getString(3));
            providertime.setEndTime(cursor.getString(4));
            providertime.setAvailable(cursor.getString(5));
            cursor.close();
        }
        else{
            providertime = null;
        }
        db.close();
        return providertime;
    }

    public void changeavail (String username, String day, String starttime, String endtime){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update " + TABLE_PROVIDERTIME + " SET "+ COLUMN_AVAILABLE+" = \""+"unavailable"+"\""+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.close();
    }

    public void changeavail2 (String username, String day, String starttime, String endtime){
        String a = "available";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update " + TABLE_PROVIDERTIME + " SET "+ COLUMN_AVAILABLE+" = \""+a+"\""+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.close();
    }

    public boolean deleteProviderTime(String username, String day, String starttime, String endtime){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+TABLE_PROVIDERTIME+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"";
        Cursor cursor = db.rawQuery(query,null);
        ProviderTime providertime = new ProviderTime();
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PROVIDERTIME,COLUMN_ID + " = " + idStr,null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean clear(){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_PROVIDERTIME);
        result = true;
        return result;
    }

    public Cursor getListContents(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PROVIDERTIME+" WHERE "+COLUMN_USERNAME+" = \""+username+"\"", null);
        return data;
    }

    public Cursor getListContents2(String day, String starttime, String endtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(" SELECT * FROM " + TABLE_PROVIDERTIME+" WHERE "+COLUMN_DAY+" = \""+day+"\""+" AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+" AND "+COLUMN_ENDTIME+" = \""+endtime+"\"" , null);
        return data;
    }

}
