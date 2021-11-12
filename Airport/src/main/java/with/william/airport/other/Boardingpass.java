package with.william.airport.other;

import with.william.airport.Flight;

public class Boardingpass {
   private Flight flight;

   private String seat;
   private String clazz;


   public Boardingpass(Flight flight, String seat, String clazz) {
       this.flight = flight;
       this.seat = seat;
       this.clazz = clazz;
   }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return String.format("%s Flight number: %s, Seat: %s, Class: %s" + flight.getFlightNumber(), seat, clazz);
    }
}
