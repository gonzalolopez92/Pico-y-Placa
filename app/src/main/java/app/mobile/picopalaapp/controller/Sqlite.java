package app.mobile.picopalaapp.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class Sqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "picopala.db";
    public static final String DATABASE_NAME_FULLPATH = DATABASE_NAME;
    private static int DATABASE_VERSION = 1;

    public static final String DATE_REGISTER = "dateRegister";
    public static final String LICENSE_PLATE = "licensePlate";
    public static final String DATE_CONSULTANT = "licensePlate";
    public static final String IS_COUNTERVERSION = "licensePlate";

    private String sqlCreateTableConsultants = "CREATE TABLE Consultants("
            + LICENSE_PLATE + " TEXT PRIMARY KEY, "
            + DATE_REGISTER + " TEXT, "
            + DATE_CONSULTANT + " TEXT, "
            + IS_COUNTERVERSION + " TEXT)";


    public Sqlite(Context context) {
        super(context, DATABASE_NAME_FULLPATH, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(sqlCreateTableConsultants);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(sqlCreateTableConsultants);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
