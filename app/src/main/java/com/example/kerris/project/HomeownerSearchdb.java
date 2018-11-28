package com.example.kerris.project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class HomeownerSearchdb extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "homeownersearchDB.db";
    public static final String TABLE_HOMEOWNERSEARCH = "homeownersearchs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_PROVIDERNAME = "providername";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";
    public static final String COLUMN_AVAILABLE = "available";

    public HomeownerSearchdb(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_HOMEOWNERSEARCH + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT, "+COLUMN_SERVICENAME+" TEXT,"+COLUMN_PROVIDERNAME+" TEXT,"+COLUMN_RATE+" TEXT,"+COLUMN_DAY+" TEXT, "+COLUMN_STARTTIME+" TEXT, "+COLUMN_ENDTIME+" TEXT,"+COLUMN_AVAILABLE+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOMEOWNERSEARCH);
        onCreate(db);
    }

    public void addHoServiceSearch(HomeownerSearch homeownersearch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,homeownersearch.getUserName());
        values.put(COLUMN_SERVICENAME,homeownersearch.getServiceName());
        values.put(COLUMN_PROVIDERNAME,homeownersearch.getProviderName());
        values.put(COLUMN_RATE,homeownersearch.getRate());
        values.put(COLUMN_DAY,homeownersearch.getDAY());
        values.put(COLUMN_STARTTIME,homeownersearch.getStartTime());
        values.put(COLUMN_ENDTIME,homeownersearch.getEndTime());
        values.put(COLUMN_AVAILABLE,homeownersearch.getAvailable());
        db.insert(TABLE_HOMEOWNERSEARCH,null,values);
        db.close();
    }



    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_HOMEOWNERSEARCH);
    }

    public HomeownerSearch findService (String username, String servicename, String providername, String rate, String day, String starttime, String endtime){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_HOMEOWNERSEARCH+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+" = \""+servicename+"\""+"AND "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_RATE+" = \""+rate+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"";
        Cursor cursor = db.rawQuery(query,null);
        HomeownerSearch homeownerseearch = new HomeownerSearch();
        if(cursor.moveToFirst()){
            homeownerseearch.setID(Integer.parseInt(cursor.getString(0)));
            homeownerseearch.setUserName(cursor.getString(1));
            homeownerseearch.setServiceName(cursor.getString(2));
            homeownerseearch.setProvidereName(cursor.getString(3));
            homeownerseearch.setRate(cursor.getString(4));
            homeownerseearch.setDAY(cursor.getString(5));
            homeownerseearch.setStartTime(cursor.getString(6));
            homeownerseearch.setEndTime(cursor.getString(7));
            homeownerseearch.setAvailable(cursor.getString(8));
            cursor.close();
        }
        else{
            homeownerseearch = null;
        }
        db.close();
        return homeownerseearch;
    }

    public void changeavail (String providername, String day, String starttime, String endtime){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update " + TABLE_HOMEOWNERSEARCH + " SET "+ COLUMN_AVAILABLE+" = \""+"unavailable"+"\""+" WHERE "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.close();
    }

    public void changeAvail1 (String providername, String day, String starttime, String endtime){
        String a = "available";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update " + TABLE_HOMEOWNERSEARCH + " SET "+ COLUMN_AVAILABLE+" = \""+a+"\""+" WHERE "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.close();
    }

    public Cursor getListContents1(String day, String starttime, String endtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HOMEOWNERSEARCH+" WHERE "+COLUMN_DAY+" = \""+day+"\""+" AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+" AND "+COLUMN_ENDTIME+" = \""+endtime+"\"", null);
        return data;
    }

    public Cursor getListContents2(String servicename, String day, String starttime, String endtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HOMEOWNERSEARCH+" WHERE "+COLUMN_SERVICENAME+" = \""+servicename+"\""+" AND "+COLUMN_DAY+" = \""+day+"\""+" AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+" AND "+COLUMN_ENDTIME+" = \""+endtime+"\"", null);
        return data;
    }

    public Cursor getListContents3(String rate, String day, String starttime, String endtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HOMEOWNERSEARCH+" WHERE "+COLUMN_RATE+" = \""+rate+"\""+" AND "+COLUMN_DAY+" = \""+day+"\""+" AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+" AND "+COLUMN_ENDTIME+" = \""+endtime+"\"", null);
        return data;
    }

    public Cursor getListContents4(String servicename, String rate, String day, String starttime, String endtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HOMEOWNERSEARCH+" WHERE "+COLUMN_SERVICENAME+" = \""+servicename+"\""+" AND "+COLUMN_RATE+" = \""+rate+"\""+" AND "+COLUMN_DAY+" = \""+day+"\""+" AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+" AND "+COLUMN_ENDTIME+" = \""+endtime+"\"", null);
        return data;
    }

}
