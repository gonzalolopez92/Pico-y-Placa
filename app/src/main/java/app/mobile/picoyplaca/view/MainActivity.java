package app.mobile.picoyplaca.view;

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

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import app.mobile.picoyplaca.R;
import app.mobile.picoyplaca.controller.Controller;
import app.mobile.picoyplaca.helpers.DateHelper;
import app.mobile.picoyplaca.helpers.Util;
import app.mobile.picoyplaca.model.Consultant;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText etHour;
    private EditText etDate;
    private int dayOfWeek;

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
                closeKeyBoard();
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
                closeKeyBoard();
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
                mTimePicker.setTitle(getString(R.string.select_hour));
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
                        Toast.makeText(context, getString(R.string.must_enter_date), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (hour.isEmpty()) {
                        Toast.makeText(context, getString(R.string.must_enter_hour), Toast.LENGTH_LONG).show();
                        return;
                    }
                    char lastDigit = licensePlate.charAt(licensePlate.length() - 1);
                    boolean isCounterversion = Util.existCounterversion(lastDigit, hour, dayOfWeek);

                    String msjDialog;
                    if (isCounterversion) {
                        msjDialog = getString(R.string.can_not_circulate);
                    } else {
                        msjDialog = getString(R.string.can_circulate);
                    }


                    new MakeConsultant(licensePlate, date, hour, isCounterversion, msjDialog).execute();

                } else {
                    Toast.makeText(context, getString(R.string.must_enter_valid_licensePlate), Toast.LENGTH_LONG).show();
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


    public void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
            dialog.setMessage(getString(R.string.wait_a_moment));
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
                    .setTitle(getString(R.string.attention))
                    .setMessage(msgDialog + "\n\nContravenciones previas para " + licensePlate.toUpperCase() + ": " + previusCounterversions)
                    .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
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
            String dateShow = DateHelper.formatDate(selectedYear, selectedMonth, selectedDay, selectDay);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, selectedMonth);
            cal.set(Calendar.DAY_OF_MONTH, selectedDay);
            dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            etDate.setText(dateShow);

        }
    };
}
