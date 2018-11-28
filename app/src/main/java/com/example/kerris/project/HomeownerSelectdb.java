package com.example.kerris.project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class HomeownerSelectdb extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "homeownerselectDB.db";
    public static final String TABLE_HOMEOWNERSELECT = "homeownerselects";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_PROVIDERNAME = "providername";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";
    public static final String COLUMN_R = "r";
    public static final String COLUMN_COMMENT = "comment";

    public HomeownerSelectdb(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "+ TABLE_HOMEOWNERSELECT + "( "+COLUMN_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USERNAME+" TEXT, "+COLUMN_SERVICENAME+" TEXT,"+COLUMN_PROVIDERNAME+" TEXT,"+COLUMN_RATE+" TEXT,"+COLUMN_DAY+" TEXT, "+COLUMN_STARTTIME+" TEXT, "+COLUMN_ENDTIME+" TEXT,"+COLUMN_R+" TEXT,"+COLUMN_COMMENT+" TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_HOMEOWNERSELECT);
        onCreate(db);
    }

    public void addHoServiceSearch(HomeownerSelect homeownerselect){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,homeownerselect.getUserName());
        values.put(COLUMN_SERVICENAME,homeownerselect.getServiceName());
        values.put(COLUMN_PROVIDERNAME,homeownerselect.getProviderName());
        values.put(COLUMN_RATE,homeownerselect.getRate());
        values.put(COLUMN_DAY,homeownerselect.getDAY());
        values.put(COLUMN_STARTTIME,homeownerselect.getStartTime());
        values.put(COLUMN_ENDTIME,homeownerselect.getEndTime());
        values.put(COLUMN_R,homeownerselect.getR());
        values.put(COLUMN_COMMENT,homeownerselect.getComment());
        db.insert(TABLE_HOMEOWNERSELECT,null,values);
        db.close();
    }

    public HomeownerSelect findHOService (String username, String servicename, String providername, String day, String starttime, String endtime){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_HOMEOWNERSELECT+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+" = \""+servicename+"\""+"AND "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"";
        Cursor cursor = db.rawQuery(query,null);
        HomeownerSelect homeownerselect = new HomeownerSelect();
        if(cursor.moveToFirst()){
            homeownerselect.setID(Integer.parseInt(cursor.getString(0)));
            homeownerselect.setUserName(cursor.getString(1));
            homeownerselect.setServiceName(cursor.getString(2));
            homeownerselect.setProvidereName(cursor.getString(3));
            homeownerselect.setRate(cursor.getString(4));
            homeownerselect.setDAY(cursor.getString(5));
            homeownerselect.setStartTime(cursor.getString(6));
            homeownerselect.setEndTime(cursor.getString(7));
            homeownerselect.setR(cursor.getString(8));
            homeownerselect.setComment(cursor.getString(9));
            cursor.close();
        }
        else{
            homeownerselect = null;
        }
        db.close();
        return homeownerselect;
    }

    public void addratecom (String rate, String comment, String username, String servicename, String providername, String day, String starttime, String endtime){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update " + TABLE_HOMEOWNERSELECT + " SET "+ COLUMN_R+" = \""+rate+"\""+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+" = \""+servicename+"\""+"AND "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.execSQL("Update " + TABLE_HOMEOWNERSELECT + " SET "+ COLUMN_COMMENT+" = \""+comment+"\""+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+" = \""+servicename+"\""+"AND "+COLUMN_PROVIDERNAME+" = \""+providername+"\""+"AND "+COLUMN_DAY+" = \""+day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+endtime+"\"");
        db.close();
    }

    public boolean deleteHOService(String username, String servicename, String providername, String rate, String day, String starttime, String endtime){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+TABLE_HOMEOWNERSELECT+" WHERE "+COLUMN_USERNAME+" = \""+username+"\""+"AND "+COLUMN_SERVICENAME+ " = \""+ servicename+"\""+"AND "+COLUMN_PROVIDERNAME+" = \""+ providername+"\""+"AND "+COLUMN_RATE+" = \""+ rate+"\""+"AND "+COLUMN_DAY+" = \""+ day+"\""+"AND "+COLUMN_STARTTIME+" = \""+starttime+"\""+"AND "+COLUMN_ENDTIME+" = \""+ endtime+"\"";
        Cursor cursor = db.rawQuery(query,null);
        HomeownerSelect homeownerselect = new HomeownerSelect();
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_HOMEOWNERSELECT,COLUMN_ID + " = " + idStr,null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_HOMEOWNERSELECT, null);
        return data;
    }
}
