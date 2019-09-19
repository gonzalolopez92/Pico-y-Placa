package app.mobile.picopalaapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import app.mobile.picopalaapp.helpers.DateHelper;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText etHour;
    private EditText etDate;
    private String dateFormat;
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;

        View mainView = findViewById(R.id.main_view);
        final EditText etLicensePlate = mainView.findViewById(R.id.etLicensePlate);
        etHour = mainView.findViewById(R.id.etHour);
        etDate = mainView.findViewById(R.id.etDate);
        Button btnSelectDate = mainView.findViewById(R.id.btnSelectDate);
        Button btnSelectHour = mainView.findViewById(R.id.btnSelectHour);
        Button btnConsultant = mainView.findViewById(R.id.btnConsultant);

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
                        etHour.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        btnConsultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLicensePlate(etLicensePlate.getText().toString())) {

                } else {
                    Toast.makeText(context, "Debe ingresar una placa válida", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public boolean validateLicensePlate(String licensePlate) {
        boolean isOk = false;
        if (licensePlate.length() == 8) {
            if (licensePlate.substring(0, 3).matches("[a-zA-Z ]+") && licensePlate.charAt(3) == '-' && licensePlate.substring(5, 8).matches("^[0-9]{1,10}$")) {
                isOk = true;
            }
        }
        return isOk;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            String dateToday = DateHelper.getDateTodayShow();
            String selectDay = String.valueOf(selectedDay);
            dateFormat = DateHelper.formatDate(false, selectedYear, selectedMonth, selectedDay, selectDay);
            String dateShow = DateHelper.formatDate(true, selectedYear, selectedMonth, selectedDay, selectDay);
            boolean isOk = DateHelper.isDateOk(dateShow, dateToday);
            if (isOk) {
                etDate.setText(dateShow);
            } else {
                Toast.makeText(context, "La fecha seleccionada no puede ser superior a la fecha actual", Toast.LENGTH_LONG).show();
            }

        }
    };
}
