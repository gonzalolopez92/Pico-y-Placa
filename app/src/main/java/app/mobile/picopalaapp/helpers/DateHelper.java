package app.mobile.picopalaapp.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

    public static String getDateLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        cal.setTime(d);


        int year = cal.get(Calendar.YEAR);

        int month = cal.get(Calendar.MONTH);

        month = month + 1;

        String strMonth = String.valueOf(month);
        if (month < 10) {
            strMonth = "0" + month;
        }

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String strDay = String.valueOf(day);
        if (day < 10) {
            strDay = "0" + day;
        }


        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String strHour = String.valueOf(hour);
        if (hour < 10) {
            strHour = "0" + hour;
        }

        int minute = cal.get(Calendar.MINUTE);
        String strMin = String.valueOf(minute);
        if (minute < 10) {
            strMin = "0" + minute;
        }

        int seconds = cal.get(Calendar.SECOND);
        String strSecond = String.valueOf(seconds);
        if (seconds < 10) {
            strSecond = "0" + seconds;
        }

        String fecha = year + "-" + strMonth + "-" + strDay + "T" + strHour + ":" + strMin + ":" + strSecond;
        return fecha;
    }

    public static String getDateLastMonthShow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date d = cal.getTime();
        cal.setTime(d);


        int year = cal.get(Calendar.YEAR);

        int month = cal.get(Calendar.MONTH);

        month = month + 1;

        String strMonth = String.valueOf(month);
        if (month < 10) {
            strMonth = "0" + month;
        }

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String strDay = String.valueOf(day);
        if (day < 10) {
            strDay = "0" + day;
        }


        String fecha = strDay + "/" + strMonth + "/" + year;
        return fecha;
    }

    public static String getDateToday() {
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

        String fecha = year + "-" + strMonth + "-" + strDay + "T" + strHour + ":" + strMin + ":" + strSecond;
        return fecha;
    }

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

    //2017-12-21T11:09:18.978978
    public static String getDateShow(String fecha) {
        String date = fecha;
        String hora = fecha.substring(11, fecha.length());
        String horaAux[] = hora.split(":");
        hora = horaAux[0] + ":" + horaAux[1];
        date = date.replaceAll("-", "/");
        date = date.substring(0, 10);
        String[] aux = date.split("/");
        String anio = aux[0];
        String mes = aux[1];
        String dia = aux[2];


        return anio + "/" + mes + "/" + dia + " " + hora;
    }

    public static int getDifferentDates(String fechaDesde, String fechaHasta) {
        Calendar calendar = GregorianCalendar.getInstance();
        String[] aux = fechaDesde.split("-");
        String dia = aux[2].substring(0, 2);
        String mes = aux[1];
        String anio = aux[0];

        calendar.set(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
        long lFechaDesde = calendar.getTimeInMillis();

        aux = fechaHasta.split("-");
        dia = aux[2].substring(0, 2);
        mes = aux[1];
        anio = aux[0];

        calendar.set(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
        long lFechaHasta = calendar.getTimeInMillis();

        if (lFechaHasta > lFechaDesde) {
            if ((lFechaHasta - lFechaDesde) >= 2764800000l) {
                return 2;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    //dia seleccionado > dia hoy
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

    public static boolean isDateOkServer(String fechaFormat1, String fechaFormta2) {
        String[] fecha1 = fechaFormat1.split("T");
        String[] fecha2 = fechaFormta2.split("T");

        fecha1 = fecha1[0].split("-");
        fecha2 = fecha2[0].split("-");

        int año = Integer.parseInt(fecha1[0]);
        int año2 = Integer.parseInt(fecha2[0]);
        int mes = Integer.parseInt(fecha1[1]);
        int mes2 = Integer.parseInt(fecha2[1]);
        int dia = Integer.parseInt(fecha1[2]);
        int dia2 = Integer.parseInt(fecha2[2]);

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
}
