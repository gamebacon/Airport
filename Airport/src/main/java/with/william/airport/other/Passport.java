package with.william.airport.other;

import with.william.airport.util.Country;

import java.time.LocalDate;

public class Passport {

    private String fullName; //encapsulated
    private Country country; //encapsulated
    private LocalDate dateOfBirth; //added

    public Passport(String fullName, Country country, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public Country getCountry() {
        return country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return LocalDate.now().compareTo(dateOfBirth);
    }

    @Override
    public String toString() {
        return String.format("*********\nName: %s\nCountry: %s\nDate of birth: %s (%d y/o)\n*********",
                fullName, country.toString(), dateOfBirth.toString(), getAge());
    }

}
