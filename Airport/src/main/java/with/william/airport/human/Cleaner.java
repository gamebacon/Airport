package with.william.airport.human;

import with.william.airport.other.Passport;

public class Cleaner extends Staff {

    protected Cleaner(Passport passport, float salary) {
        super(passport, 50);
    }

    @Override
    public void Work() {
        System.out.println("Cleaning...");
    }

}
