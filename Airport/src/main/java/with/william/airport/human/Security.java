package with.william.airport.human;

import with.william.airport.other.Passport;

public class Security extends Staff {

    public Security(Passport passport, float salary) {
        super(passport, salary);
    }

    @Override
    public void Work() {
        System.out.println("Doing security work..");
    }

}


