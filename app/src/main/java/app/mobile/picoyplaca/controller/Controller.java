package app.mobile.picoyplaca.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mobile.picoyplaca.model.Consultant;

public class Controller {


    private Sqlite instBD;

    public Controller(Context context) {
        this.instBD = new Sqlite(context);
    }

    public void insertConsultant(Consultant consultant) {
        ContentValues cv = new ContentValues();

        cv.put(Sqlite.LICENSE_PLATE, consultant.getLicensePlate().toUpperCase());
        cv.put(Sqlite.DATE_REGISTER, consultant.getDateRegister());
        cv.put(Sqlite.DATE_CONSULTANT, consultant.getDateConsultant());
        cv.put(Sqlite.IS_COUNTERVERSION, consultant.isCounterversion());

        try {
            SQLiteDatabase bd = instBD.getWritableDatabase();
            bd.insert(Sqlite.TABLE_CONSULTANTS, null, cv);
            bd.close();
            Log.d("INSERT OK", "insertConsultant: " + consultant.getLicensePlate());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("INSERT ERROR", "insertConsultant: " + e.toString());

        }
    }

    public List<Consultant> getConsultants() {
        ArrayList<Consultant> listConsultants = new ArrayList<Consultant>();
        Consultant consultant;

        SQLiteDatabase bd = instBD.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Sqlite.TABLE_CONSULTANTS;

        Cursor cursor = bd.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                consultant = new Consultant(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                listConsultants.add(consultant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return listConsultants;
    }

    public int getPreviusCounterversion(String licensePlate) {
        int previusCounterversion = 0;

        SQLiteDatabase bd = instBD.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Sqlite.TABLE_CONSULTANTS + " WHERE "
                + Sqlite.IS_COUNTERVERSION + " = '" + 1 + "' AND " +
                Sqlite.LICENSE_PLATE + "  = \"" + licensePlate.toUpperCase() + "\"";

        Cursor cursor = bd.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                previusCounterversion++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return previusCounterversion;
    }


}
