package modelflight;

import java.util.Calendar;
import java.util.Date;

public class Traveller {

    private String name;
    private Calendar birthday;
    private String nationality;
    private String passportNumber;
    private String email;
    private String phone;

    public Traveller() {

    }
    public Traveller(String name, Calendar birthday, String nationality, String passportNumber, String email, String phone) {
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
