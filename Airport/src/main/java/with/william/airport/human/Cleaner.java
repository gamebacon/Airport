package with.william.airport.human;

import with.william.airport.other.Passport;
import with.william.airport.util.Country;

import java.time.LocalDate;

public class Cleaner extends Staff {


    public Cleaner(String name, Country country, LocalDate dateOfBirth) {
        super(name, country, dateOfBirth, 1500);
    }

    @Override
    public void Work() {
        System.out.println("Cleaning...");
    }

}
