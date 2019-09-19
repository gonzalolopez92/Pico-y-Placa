package app.mobile.picopalaapp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import app.mobile.picopalaapp.R;
import app.mobile.picopalaapp.controller.Controller;

public class ConsultantsActivity extends AppCompatActivity {

    private Context context;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultants_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = ConsultantsActivity.this;
        controller = new Controller(context);

    }
}
