package with.william.airport.human;

import with.william.airport.other.Passport;

public class Pilot extends Staff {

    protected Pilot(Passport passport, float salary) {
        super(passport, salary);
    }

    @Override
    public void Work() {
        System.out.println("Flying...");
    }

}
