package temaPOO;
import java.util.ArrayList;
import java.util.HashMap;

public class Information {
    private String nume, prenume, email, telefon, birthdate, sex;
    private ArrayList<String> languages;
    private ArrayList<String> levelOfLanguage;

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public String getSex() {
        return sex;
    }
    public ArrayList<String> getLanguages() {
        return languages;
    }
    public ArrayList<String> getLevelOfLanguages() {
        return levelOfLanguage;
    }
    public void setNume(String newNume) {
        nume = newNume;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    public void setTelefon(String newTelefon) {
        telefon = newTelefon;
    }
    public void setBirthdate(String newBirthdate) {
        birthdate = newBirthdate;
    }
    public void setSex(String newSex) {
        sex = newSex;
    }
    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
    public void setLevelOfLanguages(ArrayList<String> levelOfLanguage) {
        this.levelOfLanguage = levelOfLanguage;
    }

    @Override
    public String toString() {
        return
                "nume='" + nume + "\n" +
                "email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", sex='" + sex + '\'' +
                ", languages=" + languages +
                ", levelOfLanguage=" + levelOfLanguage +
                '}';
    }
}
