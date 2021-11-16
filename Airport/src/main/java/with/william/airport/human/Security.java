package with.william.airport.human;

import with.william.airport.other.Passport;
import with.william.airport.util.Country;

import java.time.LocalDate;

public class Security extends Staff {

    public Security(String name, Country country, LocalDate dateOfBirth) {
        super(name, country, dateOfBirth, 3000);
    }

    @Override
    public void Work() {
        System.out.println("Doing security work..");
    }

}


