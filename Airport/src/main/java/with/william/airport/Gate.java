package with.william.airport;

import java.util.ArrayList;
import java.util.List;

public class Gate {
    String name; //added name for identification.

    private List<Flight> flights = new ArrayList<Flight>();

    public Gate(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public String toString() {
        return name;
    }
}
