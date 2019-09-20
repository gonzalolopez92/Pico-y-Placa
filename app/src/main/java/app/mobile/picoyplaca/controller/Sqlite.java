package app.mobile.picoyplaca.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class Sqlite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "picoyplaca.db";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    static final String LICENSE_PLATE = "licensePlate";
    static final String DATE_REGISTER = "dateRegister";
    static final String DATE_CONSULTANT = "dateConsultant";
    static final String IS_COUNTERVERSION = "isCounterversion";

    static final String TABLE_CONSULTANTS = "Consultants";


    private final String sqlCreateTableConsultants = "CREATE TABLE " + TABLE_CONSULTANTS + " ("
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
