package app.mobile.picoyplaca.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "picoyplaca.db";
    private static int DATABASE_VERSION = 1;

    public static final String ID = "id";
    public static final String LICENSE_PLATE = "licensePlate";
    public static final String DATE_REGISTER = "dateRegister";
    public static final String DATE_CONSULTANT = "dateConsultant";
    public static final String IS_COUNTERVERSION = "isCounterversion";

    public static final String TABLE_CONSULTANTS = "Consultants";


    private String sqlCreateTableConsultants = "CREATE TABLE " + TABLE_CONSULTANTS + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LICENSE_PLATE + " TEXT, "
            + DATE_REGISTER + " TEXT, "
            + DATE_CONSULTANT + " TEXT, "
            + IS_COUNTERVERSION + " INTEGER)";


    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
