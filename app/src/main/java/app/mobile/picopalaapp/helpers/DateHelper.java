package app.mobile.picopalaapp.helpers;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {


    public static String getDateTodayShow() {
        java.util.Date date = new java.util.Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        String strMonth = String.valueOf(month);
        if (month < 10) {
            strMonth = "0" + month;
        }

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String strDay = String.valueOf(day);
        if (day < 10) {
            strDay = "0" + day;
        }

        String fecha = strDay + "/" + strMonth + "/" + year;
        return fecha;
    }


    public static boolean isDateOk(String fechaFormat1, String fechaFormta2) {
        String[] fecha1 = fechaFormat1.split("\\/");
        String[] fecha2 = fechaFormta2.split("\\/");

        int año = Integer.parseInt(fecha1[2]);
        int año2 = Integer.parseInt(fecha2[2]);
        int mes = Integer.parseInt(fecha1[1]);
        int mes2 = Integer.parseInt(fecha2[1]);
        int dia = Integer.parseInt(fecha1[0]);
        int dia2 = Integer.parseInt(fecha2[0]);

        if (año >= año2 && mes >= mes2 && dia > dia2) {
            return false;
        } else {
            return true;
        }
    }


    public static String formatDate(boolean isShow, int selectedYear,
                                    int selectedMonth, int selectedDay, String strselectDay) {

        if (selectedDay < 10) {
            strselectDay = "0" + selectedDay;
        }

        selectedMonth = selectedMonth + 1;
        String strSelectedMonth = String.valueOf(selectedMonth);
        if (selectedMonth < 10) {
            strSelectedMonth = "0" + selectedMonth;
        }

        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String strHour = String.valueOf(hour);
        if (hour < 10) {
            strHour = "0" + hour;
        }

        int minute = calendar.get(Calendar.MINUTE);
        String strMin = String.valueOf(minute);
        if (minute < 10) {
            strMin = "0" + minute;
        }

        int seconds = calendar.get(Calendar.SECOND);
        String strSecond = String.valueOf(seconds);
        if (seconds < 10) {
            strSecond = "0" + seconds;
        }

        String finalDate = "";
        if (isShow) {
            finalDate = strselectDay + "/" + strSelectedMonth + "/" + selectedYear;
        } else {
            finalDate = selectedYear + "-" + strSelectedMonth + "-" + strselectDay + "T" + strHour + ":" + strMin + ":" + strSecond;
        }

        return finalDate;
    }

    public static boolean isHourInFirstRage(String hourSelected) {
        try {
            String hour1 = "7:00";
            Date time1 = new SimpleDateFormat("HH:mm").parse(hour1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);


            String hour2 = "9:30";
            Date time2 = new SimpleDateFormat("HH:mm").parse(hour2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date d = new SimpleDateFormat("HH:mm").parse(hourSelected);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                Log.i("RANGO", "HORA EN RANGO HORARIO!!");
                return true;
            }
            Log.i("RANGO", "HORA FUERA DEL RANGO HORARIO!!");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean isHourInSecondtRage(String hourSelected) {
        try {
            String string1 = "16:00";
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);


            String string2 = "19:30";
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date d = new SimpleDateFormat("HH:mm").parse(hourSelected);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                Log.i("RANGO", "HORA EN RANGO HORARIO!!");
                return true;
            }
            Log.i("RANGO", "HORA FUERA DEL RANGO HORARIO!!");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }
}
