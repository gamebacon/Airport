package with.william.airport.human;

import with.william.airport.util.Country;

import java.time.LocalDate;

public abstract class Staff extends Human {

    private float salary;

    protected Staff(String fullName, Country country, LocalDate dateOfBirth, float salary) {
        super(fullName, country, dateOfBirth);
        this.salary = salary;
    }

    public abstract void Work();

    public float getSalary() {
        return salary;
    }
}
