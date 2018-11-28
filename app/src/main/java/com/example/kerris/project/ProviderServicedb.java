package com.example.kerris.project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class ProviderServicedb extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "providerserviceDB.db";
    public static final String TABLE_PROVIDERSERVICE = "providerservices";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_RATE = "rate";


    public ProviderServicedb(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_PROVIDERSERVICE + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT, "+COLUMN_SERVICENAME+" TEXT,"+COLUMN_RATE+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDERSERVICE);
        onCreate(db);
    }

    public void addProviderService(ProviderService providerservice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,providerservice.getUserName());
        values.put(COLUMN_SERVICENAME,providerservice.getServiceName());
        values.put(COLUMN_RATE,providerservice.getRate());
        db.insert(TABLE_PROVIDERSERVICE,null,values);
        db.close();
    }

    public ProviderService findProviderService (String username, String servicename){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_PROVIDERSERVICE+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+" = \""+servicename+"\"";
        Cursor cursor = db.rawQuery(query,null);
        ProviderService providerservice = new ProviderService();
        if(cursor.moveToFirst()){
            providerservice.setID(Integer.parseInt(cursor.getString(0)));
            providerservice.setUserName(cursor.getString(1));
            providerservice.setServiceName(cursor.getString(2));
            providerservice.setRate(cursor.getString(3));
            cursor.close();
        }
        else{
            providerservice = null;
        }
        db.close();
        return providerservice;
    }

    public boolean deleteProviderService(String username, String servicename){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+TABLE_PROVIDERSERVICE+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+ " = \""+ servicename+"\"";
        Cursor cursor = db.rawQuery(query,null);
        ProviderService providerservice = new ProviderService();
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PROVIDERSERVICE,COLUMN_ID + " = " + idStr,null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean clear(){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_PROVIDERSERVICE);
        result = true;
        return result;
    }

    public Cursor getListContents(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_PROVIDERSERVICE+" WHERE "+COLUMN_USERNAME+" = \""+username+"\"", null);
        return data;
    }

}
