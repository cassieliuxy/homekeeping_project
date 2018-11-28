package com.example.kerris.project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class Providerdb extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "providerDB.db";
    public static final String TABLE_PROVIDERS = "providers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phonenumber";
    public static final String COLUMN_COMPANY = "companyname";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LICENSED = "licensed";

    public Providerdb(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_PROVIDERS + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT,"+COLUMN_ADDRESS+" TEXT,"+COLUMN_PHONE+" TEXT,"+COLUMN_COMPANY+" TEXT,"+COLUMN_DESCRIPTION+" TEXT,"+COLUMN_LICENSED+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PROVIDERS);
        onCreate(db);
    }

    public void addProvider(Provider provider){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,provider.getUserName());
        values.put(COLUMN_ADDRESS,provider.getAddress());
        values.put(COLUMN_PHONE,provider.getPhoneNumber());
        values.put(COLUMN_COMPANY,provider.getCompanyName());
        values.put(COLUMN_DESCRIPTION,provider.getDescription());
        values.put(COLUMN_LICENSED,provider.getLicensed());
        db.insert(TABLE_PROVIDERS,null,values);
        db.close();
    }

    public Provider findProvider(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_PROVIDERS+" WHERE "+COLUMN_USERNAME+" = \""+username+"\"";
        Cursor cursor = db.rawQuery(query,null);
        Provider provider = new Provider();
        if(cursor.moveToFirst()){
            provider.setID(Integer.parseInt(cursor.getString(0)));
            provider.setUserName(cursor.getString(1));
            provider.setAddress(cursor.getString(2));
            provider.setPhoneNumber(cursor.getString(3));
            provider.setCompanyName(cursor.getString(4));
            provider.setDescription(cursor.getString(5));
            provider.setLicensed(cursor.getString(6));
            cursor.close();
        }
        else{
            provider = null;
        }
        db.close();
        return provider;
    }

    public void changeProvider(String username, String address, String phonenumber, String companyname, String description,String licensed){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " UPDATE "+ TABLE_PROVIDERS+ " SET " + COLUMN_ADDRESS +" = \""+address+"\""+" AND "+ COLUMN_PHONE +" = \""+phonenumber+"\""+" AND "+ COLUMN_COMPANY +" = \""+companyname+"\""+" AND "+ COLUMN_DESCRIPTION +" = \""+description+"\""+" AND "+ COLUMN_LICENSED +" = \""+licensed+"\""+" WHERE "+ COLUMN_USERNAME +" = \""+username+"\"";
        db.execSQL(query);
    }

}
