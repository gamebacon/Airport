package with.william.airport.human;

import with.william.airport.other.Passport;

public abstract class Staff extends Human {

    private float salary;

    protected Staff(Passport passport, float salary) {
        super(passport);
        this.salary = salary;
    }

    public abstract void Work();

    public float getSalary() {
        return salary;
    }
}
