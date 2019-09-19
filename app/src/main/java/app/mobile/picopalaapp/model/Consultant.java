package app.mobile.picopalaapp.model;

public class Consultant {

    private String dateRegister;
    private String licensePlate;
    private String dateConsultant;
    private boolean isCounterversion;

    public Consultant() {
    }

    public Consultant(String dateRegister, String licensePlate, String dateConsultant, boolean isCountversion) {
        this.dateRegister = dateRegister;
        this.licensePlate = licensePlate;
        this.dateConsultant = dateConsultant;
        this.isCounterversion = isCountversion;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDateConsultant() {
        return dateConsultant;
    }

    public void setDateConsultant(String dateConsultant) {
        this.dateConsultant = dateConsultant;
    }

    public boolean isCounterversion() {
        return isCounterversion;
    }

    public void setCounterversion(boolean counterversion) {
        isCounterversion = counterversion;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "dateRegister='" + dateRegister + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", dateConsultant='" + dateConsultant + '\'' +
                ", isCounterversion=" + isCounterversion +
                '}';
    }
}
