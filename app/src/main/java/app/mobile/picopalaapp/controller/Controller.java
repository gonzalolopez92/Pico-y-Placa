package app.mobile.picopalaapp.controller;

import android.content.Context;

public class Controller {


    private Sqlite instBD;

    public Controller(Context context) {
        this.instBD = new Sqlite(context);
    }



}
