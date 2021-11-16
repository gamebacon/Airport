package with.william.airport.human;

import with.william.airport.util.Country;

import java.time.LocalDate;

public abstract class Human {

    private LocalDate dateOfBirth; //added date of birth.
    private Country country; //added country
    private float balance = 3000; // added money

    private String name;

    protected Human(String fullName, Country country, LocalDate dateOfBirth) {
        this.name = fullName;
        this.country = country;
        this.dateOfBirth = dateOfBirth;

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
