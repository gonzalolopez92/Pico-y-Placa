package app.mobile.picopalaapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.mobile.picopalaapp.model.Consultant;

public class Controller {


    private Sqlite instBD;

    public Controller(Context context) {
        this.instBD = new Sqlite(context);
    }

    public void insertConsultant(Consultant consultant) {
        ContentValues cv = new ContentValues();

        cv.put(Sqlite.LICENSE_PLATE, consultant.getLicensePlate());
        cv.put(Sqlite.DATE_REGISTER, consultant.getDateRegister());
        cv.put(Sqlite.DATE_CONSULTANT, consultant.getDateConsultant());
        cv.put(Sqlite.IS_COUNTERVERSION, consultant.isCounterversion());

        try {
            SQLiteDatabase bd = instBD.getWritableDatabase();
            bd.insert(Sqlite.TABLE_CONSULTANTS, null, cv);
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
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
                consultant = new Consultant(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                listConsultants.add(consultant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return listConsultants;
    }

}
