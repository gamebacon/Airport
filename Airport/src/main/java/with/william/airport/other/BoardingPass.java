package with.william.airport.other;

import with.william.airport.Flight;
import with.william.airport.util.Util;

public class BoardingPass {
   private Flight flight;

   private String seat;
   private FlightClass flightClass;  //changed from String to enum


   public BoardingPass(Flight flight, String seat, FlightClass flightClass) {
       this.flight = flight;
       this.seat = seat;
       this.flightClass = flightClass;
   }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return String.format("Flight: %s, Destination: %s, Departure: %s, Seat: %s, Class: %s", flight.getFlightNumber(), flight.getDestination().toString(), flight.getDepartureTime().format(Util.formatter), seat, flightClass.toString());
    }
}
