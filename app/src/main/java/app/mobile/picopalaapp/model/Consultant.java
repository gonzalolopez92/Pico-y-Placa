package app.mobile.picopalaapp.model;

public class Consultant {

    private String dateRegister;
    private String licensePlate;
    private String dateConsultant;
    private int isCounterversion;


    public Consultant(String licensePlate, String dateRegister, String dateConsultant, int isCountversion) {
        this.licensePlate = licensePlate;
        this.dateRegister = dateRegister;
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

    public int isCounterversion() {
        return isCounterversion;
    }

    public void setCounterversion(int counterversion) {
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