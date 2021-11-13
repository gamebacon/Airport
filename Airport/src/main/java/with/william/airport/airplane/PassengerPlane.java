package with.william.airport.airplane;

import with.william.airport.human.Traveler;

import java.util.HashMap;
import java.util.Map;

public class PassengerPlane extends Airplane {

    public Map<String, Traveler> passengersList = new HashMap<>(); // added seat and passengers map.

    public PassengerPlane() {

        for(int i = 1; i <= 9; i++) {
            for(char c : "ABCD".toCharArray()) {
                passengersList.put(i + "" +  c, null);
            }
        }

    }


    @Override
    public void Fly() {
        System.out.println("Now taking of!");
    }
}
