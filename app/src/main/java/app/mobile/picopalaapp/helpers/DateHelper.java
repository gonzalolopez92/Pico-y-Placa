package app.mobile.picopalaapp.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {


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
        return selectedYear + "/" + strSelectedMonth + "/" + strselectDay;

    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
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
            if ((x.equals(calendar1.getTime()) || x.after(calendar1.getTime())) && (x.equals(calendar2.getTime()) || x.before(calendar2.getTime()))) {
                return true;
            }
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
            if ((x.equals(calendar1.getTime()) || x.after(calendar1.getTime())) && (x.equals(calendar2.getTime()) || x.before(calendar2.getTime()))) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }
}
