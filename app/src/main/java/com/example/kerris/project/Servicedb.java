package com.example.kerris.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class Servicedb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "serviceDB.db";
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_RATE = "rate";

    public Servicedb(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_SERVICES + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_SERVICENAME+" TEXT, "+COLUMN_RATE+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SERVICES);
        onCreate(db);
    }

    public void addService(Service service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME,service.getServiceName());
        values.put(COLUMN_RATE,service.getRate());
        db.insert(TABLE_SERVICES,null,values);
        db.close();
    }

    public Service findService(String servicename){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_SERVICES+" WHERE "+COLUMN_SERVICENAME+" = \""+servicename+"\"";
        Cursor cursor = db.rawQuery(query,null);
        Service service = new Service();
        if(cursor.moveToFirst()){
            service.setID(Integer.parseInt(cursor.getString(0)));
            service.setServiceName(cursor.getString(1));
            service.setRate(cursor.getString(2));
            cursor.close();
        }
        else{
            service = null;
        }
        db.close();
        return service;
    }

    public void changeService(String servicename, String rate){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " UPDATE "+ TABLE_SERVICES+ " SET " + COLUMN_RATE + " = \""+rate+"\""+" WHERE "+COLUMN_SERVICENAME+" = \""+servicename+"\"";
        db.execSQL(query);
    }

    public boolean deleteService(String servicename){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+TABLE_SERVICES+" WHERE "+COLUMN_SERVICENAME+ " = \""+ servicename+"\"";
        Cursor cursor = db.rawQuery(query,null);
        Service service = new Service();
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES,COLUMN_ID + " = " + idStr,null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_SERVICES, null);
        return data;
    }

    public Cursor getListServices() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor s = db.rawQuery("SELECT "+ COLUMN_SERVICENAME + " FROM " + TABLE_SERVICES, null);
        return s;
    }
}
