package with.william.airport.airplane;

import with.william.airport.human.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane {
    private int fuelLevel;

    private List<Pilot> pilots = new ArrayList<>();

    public void Fly() throws Exception {

        if(pilots.size() <= 0)
            throw new Exception("Can't fly without a pilot!");

        System.out.println("Taking off..");

    }

    public int getFuelLevel() {
        return fuelLevel;
    }
}
