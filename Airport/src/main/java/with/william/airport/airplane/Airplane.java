package with.william.airport.airplane;

import with.william.airport.human.Human;
import with.william.airport.human.Pilot;
import with.william.airport.human.Traveler;
import with.william.airport.other.NoPilotException;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane {
    private int fuelLevel;

    public List<Pilot> pilots = new ArrayList<>();

    public void Fly() throws NoPilotException {

        if(pilots.size() <= 0)
            throw new NoPilotException("Can't fly without a pilot!");

        System.out.println("Taking of!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        System.out.println("Landed!");
    }

    public int getFuelLevel() {
        return fuelLevel;
    }
}
