package with.william.airport;

import with.william.airport.airplane.Airplane;
import with.william.airport.util.Country;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    private Country country; //add
    private String name;

    private List<Terminal> terminals = new ArrayList<>();
    private List<Airplane> airPlanes= new ArrayList<>();


    public Airport(String name, Country country) {
        this.name = name;
        this.country = country;

        for(int i = 1; i <= 3; i++) {
            terminals.add(new Terminal("Terminal " + i));
        }



    }

    //added
    public void scheduleFlight(int terminalIndex, int gateIndex, Flight flight) {
        Terminal terminal = terminals.get(terminalIndex);
        Gate gate = terminals.get(terminalIndex).getGates().get(gateIndex);

        gate.getFlights().add(flight);
        flight.setGate(gate);
    }

    //Added monitors in order to get intel on all flights
    public void ViewMonitors() {
        for(Terminal terminal : getTerminals()) {
            String terminalInfo = terminal.toString();

            if(terminalInfo.length() > 0) {
                System.out.println(terminal.getName());
                System.out.println("_______________________________________");
                System.out.println("No.  | Time  | Gate | Destination");
                System.out.println(terminalInfo);
            }
        }

    }


    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", name, country.toString());
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }
}
