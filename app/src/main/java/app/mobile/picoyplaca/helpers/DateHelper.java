package app.mobile.picoyplaca.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {


    public static String formatDate(int selectedYear, int selectedMonth, int selectedDay, String strselectDay) {
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

    public static String formatHour(int selectedHour, int selectedMinute) {
        String formatedHour;
        if (selectedMinute < 9) {
            formatedHour = "0" + selectedMinute;
        } else {
            formatedHour = String.valueOf(selectedMinute);
        }
        return selectedHour + ":" + formatedHour;

    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static boolean isHourInRage(String hourSelected, String hour1, String hour2) {
        try {
            Date time1 = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(hour1);
            Date time2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(hour2);
            if (time1 != null && time2 != null) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(time1);
                calendar1.add(Calendar.DATE, 1);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(time2);
                calendar2.add(Calendar.DATE, 1);

                Date d = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(hourSelected);
                if (d != null) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(d);
                    calendar3.add(Calendar.DATE, 1);

                    Date x = calendar3.getTime();
                    if ((x.equals(calendar1.getTime()) || x.after(calendar1.getTime())) && (x.equals(calendar2.getTime()) || x.before(calendar2.getTime()))) {
                        return true;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }
}
