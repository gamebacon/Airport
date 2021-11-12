package with.william.airport.human;

import with.william.airport.util.Country;
import with.william.airport.other.Passport;

import java.time.LocalDate;

public abstract class Human {

    private LocalDate dateOfBirth; //added date of birth.
    private Country country; //added country
    private float balance = 3000; // added money

    private String name;

    protected Human(Passport id) {
        this.name = id.getFullName();
        this.country = id.getCountry();
        this.dateOfBirth = id.getDateOfBirth();

    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Country getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
