package com.example.assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    public static final String LOC_TABLE ="LOC_TABLE";
    public static final String COL_ID ="ID";
    public static final String COL_ADDRESS ="ADDRESS";
    public static final String COL_LATITUDE ="LATITUDE";
    public static final String COL_LONGITUDE ="LONGITUDE";
    public database(@Nullable Context context) {
        super(context, "locations.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table ="CREATE TABLE "+ LOC_TABLE+" ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_ADDRESS+" TEXT, "+COL_LATITUDE+" TEXT, "+COL_LONGITUDE+" TEXT)";
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  boolean addOne(model d){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ADDRESS, d.getAddress());
        cv.put(COL_LATITUDE, d.getLatitude());
        cv.put(COL_LONGITUDE, d.getLongitude());

        long insert = db.insert(LOC_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public List<model> getdata(){
        List<model> returnList = new ArrayList<>();
        String queryString= "SELECT * FROM "+LOC_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){

            do{
                int id = cursor.getInt(0);
                String address = cursor.getString(1);
                String latitude = cursor.getString(2);
                String longitude = cursor.getString(3);

                model newmodel = new model(id,address,latitude,longitude);
                returnList.add(newmodel);

            } while(cursor.moveToNext());

        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteOne (String add)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+LOC_TABLE+" WHERE "+COL_ADDRESS+" = '"+ add+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        else{
            return false;
        }

    }
    public String searchOne (String add)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+LOC_TABLE+" WHERE "+COL_ADDRESS+" = '"+ add+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String address = cursor.getString(1);
            String latitude = cursor.getString(2);
            String longitude = cursor.getString(3);
            //put the results to a new model and return the String
            model newmodel = new model(id,address,latitude,longitude);
            return newmodel.toString();

        }
        else{
            return null;
        }

    }
    public boolean updateOne (String add, String newadd,String lon,String lat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+LOC_TABLE+" WHERE "+COL_ADDRESS+" = '"+ add+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            query = "UPDATE "+LOC_TABLE+" SET "+COL_ADDRESS+" = '"+newadd+"', "+COL_LONGITUDE+" = "+lon+", "+COL_LATITUDE+" = "+lat+" WHERE "+COL_ID+" = "+id;
            Cursor cursor2 = db.rawQuery(query, null);
            if (cursor2.moveToFirst())
            {
                return true;
            }
            return true;

        }else {return false;}


    }



}
