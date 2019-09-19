package app.mobile.picopalaapp.helpers;

public class Util {

    public static boolean existCounterversion(char lastDigit, String date, String hour, String weekDaySelected) {
        switch (weekDaySelected) {
            case "lun.":
                if (lastDigit == '1' || lastDigit == '2') {
                    if (DateHelper.isHourInFirstRage(hour) || DateHelper.isHourInSecondtRage(hour)) {
                        return true;
                    }
                }
                break;
            case "mar.":
                if (lastDigit == '3' || lastDigit == '4') {
                    if (DateHelper.isHourInFirstRage(hour) || DateHelper.isHourInSecondtRage(hour)) {
                        return true;
                    }
                }
                break;
            case "mié.":
                if (lastDigit == '5' || lastDigit == '6') {
                    if (DateHelper.isHourInFirstRage(hour) || DateHelper.isHourInSecondtRage(hour)) {
                        return true;
                    }
                }
                break;
            case "jue.":
                if (lastDigit == '7' || lastDigit == '8') {
                    if (DateHelper.isHourInFirstRage(hour) || DateHelper.isHourInSecondtRage(hour)) {
                        return true;
                    }

                }
                break;
            case "vie.":
                if (lastDigit == '9' || lastDigit == '0') {
                    if (DateHelper.isHourInFirstRage(hour) || DateHelper.isHourInSecondtRage(hour)) {
                        return true;
                    }
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean validateLicensePlate(String licensePlate) {
        boolean isOk = false;
        if (licensePlate.length() == 8) {
            if (licensePlate.substring(0, 3).matches("[a-zA-Z ]+") && licensePlate.charAt(3) == '-' && licensePlate.substring(5, 8).matches("^[0-9]{1,10}$")) {
                isOk = true;
            }
        }
        return isOk;
    }
}
