package app.mobile.picopalaapp.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import app.mobile.picopalaapp.R;
import app.mobile.picopalaapp.controller.Controller;
import app.mobile.picopalaapp.helpers.DateHelper;
import app.mobile.picopalaapp.helpers.Util;
import app.mobile.picopalaapp.model.Consultant;
import app.mobile.picopalaapp.view.adapters.ConsultantListAdapter;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText etHour;
    private EditText etDate;
    private String weekDaySelected;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;
        controller = new Controller(context);

        View mainView = findViewById(R.id.main_view);
        final EditText etLicensePlate = mainView.findViewById(R.id.etLicensePlate);
        etHour = mainView.findViewById(R.id.etHour);
        etDate = mainView.findViewById(R.id.etDate);
        Button btnSelectDate = mainView.findViewById(R.id.btnSelectDate);
        Button btnSelectHour = mainView.findViewById(R.id.btnSelectHour);
        Button btnConsultant = mainView.findViewById(R.id.btnConsultant);
        FloatingActionButton listConsultantButton = findViewById(R.id.listConsultants);


        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);
                int Year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, datePickerListener,
                        Year, month, day);
                dialog.show();

            }
        });

        btnSelectHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etHour.setText(DateHelper.formatHour(selectedHour, selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccionar Hora");
                mTimePicker.show();

            }
        });

        btnConsultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String licensePlate = etLicensePlate.getText().toString();
                final String date = etDate.getText().toString();
                final String hour = etHour.getText().toString();

                if (Util.validateLicensePlate(licensePlate)) {
                    if (date.isEmpty()) {
                        Toast.makeText(context, "Debe ingresar una fecha", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (hour.isEmpty()) {
                        Toast.makeText(context, "Debe ingresar un horario", Toast.LENGTH_LONG).show();
                        return;
                    }
                    char lastDigit = licensePlate.charAt(licensePlate.length() - 1);
                    boolean isCounterversion = Util.existCounterversion(lastDigit, hour, weekDaySelected);

                    String msjDialog = "";
                    if (isCounterversion) {
                        msjDialog = "Usted no puede circular por la ciudad.";
                    } else {
                        msjDialog = "Usted puede circular por la ciudad.";
                    }


                    new MakeConsultant(licensePlate, date, hour, isCounterversion, msjDialog).execute();

                } else {
                    Toast.makeText(context, "Debe ingresar una placa válida", Toast.LENGTH_LONG).show();
                }
            }
        });

        listConsultantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ConsultantsActivity.class));
            }
        });


    }


    private class MakeConsultant extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog;
        private int previusCounterversions = 0;
        private String licensePlate;
        private String date;
        private String hour;
        private boolean isCounterversion;
        private String msgDialog;


        public MakeConsultant(String licensePlate, String date, String hour, boolean isCounterversion, String msgDialog) {
            this.licensePlate = licensePlate;
            this.date = date;
            this.hour = hour;
            this.isCounterversion = isCounterversion;
            this.msgDialog = msgDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle(getString(R.string.app_name));
            dialog.setCancelable(false);
            dialog.setIcon(R.mipmap.ic_launcher);
            dialog.setMessage("Espere un momento...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                previusCounterversions = controller.getPreviusCounterversion(licensePlate);
                final String currentDay = DateHelper.getCurrentDate();
                controller.insertConsultant(new Consultant(licensePlate, currentDay, date + " " + hour, (isCounterversion) ? 1 : 0));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            new AlertDialog.Builder(context)
                    .setTitle("Atención")
                    .setMessage(msgDialog + "\n\nContravenciones previas para " + licensePlate.toUpperCase() + ": " + previusCounterversions)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String selectDay = String.valueOf(selectedDay);
            String dateShow = DateHelper.formatDate(true, selectedYear, selectedMonth, selectedDay, selectDay);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, selectedMonth);
            cal.set(Calendar.DAY_OF_MONTH, selectedDay);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            weekDaySelected = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];
            Log.i("DAY ", weekDaySelected);
            etDate.setText(dateShow);

        }
    };
}
