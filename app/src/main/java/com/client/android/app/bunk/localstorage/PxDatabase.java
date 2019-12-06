//
// Copyright 2012 Paytronix Systems, Inc.
// All Rights Reserved
// PAYTRONIX CONFIDENTIAL
//

package com.client.android.app.bunk.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.client.android.app.bunk.data.SaveCustomerItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PxDatabase {
    protected static final String IDENTITY_TABLE_NAME = "identity";
    public static final String VEHICLE_ID = "vehicle_id";
    public static final String USERNAME = "username";
    public static final String MOBILENUMBER = "mobile_number";
    public static final String OIL_CHANGED = "oil_changed";
    public static final String ODOMETER_READING = "odometer_reading";
    public static final String DATEOFOIL = "date_of_oil";
    public static final String CUSTOMEREMAIL = "customer_email";
    public static final String TENTATIVEKM = "tentative_km";
    public static final String TENTATIVE_DATE = "tentative_date";

    private final PxOpenHelper databaseOpenHelper;

    /**
     * Constructor
     *
     * @param context The Context within which to work, used to create the DB
     */
    public PxDatabase(Context context) {
        databaseOpenHelper = new PxOpenHelper(context);
    }

    public void close() {
        databaseOpenHelper.close();
    }

    public SaveCustomerItem getFirstIdentity() {
        SQLiteDatabase db = databaseOpenHelper.getReadableDatabase();
        Cursor c = db.query(IDENTITY_TABLE_NAME,
                new String[]{VEHICLE_ID, USERNAME, MOBILENUMBER, OIL_CHANGED, ODOMETER_READING, DATEOFOIL, CUSTOMEREMAIL,TENTATIVEKM,TENTATIVE_DATE}, null, null,null, null, null);
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }

        SaveCustomerItem id = new SaveCustomerItem(c.getString(c.getColumnIndex(VEHICLE_ID)), c.getString(c.getColumnIndex(USERNAME)),
                c.getString(c.getColumnIndex(MOBILENUMBER)), c.getString(c.getColumnIndex(OIL_CHANGED)),
                c.getString(c.getColumnIndex(ODOMETER_READING)), c.getString(c.getColumnIndex(DATEOFOIL)),
                c.getString(c.getColumnIndex(CUSTOMEREMAIL)),c.getString(c.getColumnIndex(TENTATIVEKM)),
                c.getString(c.getColumnIndex(TENTATIVE_DATE)));
        c.close();
        return id;
    }



    public void insertIdentity(String vehicleNo,String username, String mobilenum,String email, String oilChanged, String odometerReading,
                               String dateOfOil,String tentativeKm,String tentativeDate) {
        SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put(VEHICLE_ID, vehicleNo);
        cols.put(USERNAME, username);
        cols.put(MOBILENUMBER, mobilenum);
        cols.put(OIL_CHANGED, oilChanged);
        cols.put(ODOMETER_READING, odometerReading);
        cols.put(DATEOFOIL, dateOfOil);
        cols.put(CUSTOMEREMAIL, email);
        cols.put(TENTATIVEKM, tentativeKm);
        cols.put(TENTATIVE_DATE, tentativeDate);
        db.insert(IDENTITY_TABLE_NAME, VEHICLE_ID, cols);
        db.close();
    }


    public void deleteAllIdentities() {
        SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
        db.delete(IDENTITY_TABLE_NAME, VEHICLE_ID, new String[]{});
        db.close();
    }


    private class PxOpenHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1; // 2.3
        private static final String IDENTITY_TABLE_CREATE = "CREATE TABLE " + IDENTITY_TABLE_NAME + " (" + VEHICLE_ID
                + " TEXT PRIMARY KEY" + ", " + USERNAME + " TEXT" + ", " + MOBILENUMBER + " TEXT" + ", "
                + OIL_CHANGED + " TEXT" + ", " + ODOMETER_READING + " TEXT" + ", " + DATEOFOIL + " TEXT"
                + ", " + CUSTOMEREMAIL + " TEXT" + ", " + TENTATIVEKM + " TEXT" + " , " + TENTATIVE_DATE + " TEXT " + ");";


        PxOpenHelper(Context context) {
            super(context, "bunk.db", null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IDENTITY_TABLE_CREATE);
        }

        // Modified For PBM Guest [Starts]
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + IDENTITY_TABLE_CREATE);
            db.execSQL(IDENTITY_TABLE_CREATE);
        }
    }

}
