package with.william.airport.airplane;

import with.william.airport.human.Traveler;

import java.util.HashMap;
import java.util.Map;

public class PassengerPlane extends Airplane {

    public Map<String, Traveler> passengers = new HashMap<>(); // added seat and passengers map.

    public PassengerPlane() {

        for(int i = 1; i <= 10; i++) {
            for(char c : "ABCD".toCharArray()) {
                passengers.put(i + "" +  c, null);
            }
        }

    }


    @Override
    public void Fly() {
        System.out.println("Now taking of!");
    }
}
