package with.william.airport.human;

import with.william.airport.other.Passport;
import with.william.airport.util.Country;

import java.time.LocalDate;

public class Pilot extends Staff {

    public Pilot(String name, Country country, LocalDate dateOfBirth) {
        super(name, country, dateOfBirth, 4500);
    }

    @Override
    public void Work() {
        System.out.println("Flying...");
    }

}
