package app.mobile.picoyplaca.helpers;

public class Util {

    public static boolean existCounterversion(char lastDigit, String hour, int weekDaySelected) {
        switch (weekDaySelected) {
            case 2:
                if (lastDigit == '1' || lastDigit == '2') {
                    if (DateHelper.isHourInRage(hour, "7:00", "9:30") || DateHelper.isHourInRage(hour, "16:00", "19:30")) {
                        return true;
                    }
                }
                break;
            case 3:
                if (lastDigit == '3' || lastDigit == '4') {
                    if (DateHelper.isHourInRage(hour, "7:00", "9:30") || DateHelper.isHourInRage(hour, "16:00", "19:30")) {
                        return true;
                    }
                }
                break;
            case 4:
                if (lastDigit == '5' || lastDigit == '6') {
                    if (DateHelper.isHourInRage(hour, "7:00", "9:30") || DateHelper.isHourInRage(hour, "16:00", "19:30")) {
                        return true;
                    }
                }
                break;
            case 5:
                if (lastDigit == '7' || lastDigit == '8') {
                    if (DateHelper.isHourInRage(hour, "7:00", "9:30") || DateHelper.isHourInRage(hour, "16:00", "19:30")) {
                        return true;
                    }

                }
                break;
            case 6:
                if (lastDigit == '9' || lastDigit == '0') {
                    if (DateHelper.isHourInRage(hour, "7:00", "9:30") || DateHelper.isHourInRage(hour, "16:00", "19:30")) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public static boolean validateLicensePlate(String licensePlate) {
        if (licensePlate.length() == 8) {
            return licensePlate.substring(0, 3).matches("[a-zA-Z ]+") && licensePlate.charAt(3) == '-' && licensePlate.substring(5, 8).matches("^[0-9]{1,10}$");
        }
        return false;
    }
}
