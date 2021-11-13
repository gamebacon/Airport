package with.william.airport;

import with.william.airport.airplane.PassengerPlane;
import with.william.airport.util.Util;

import java.time.LocalDateTime;

public class Flight implements Comparable<Flight> {

    private Airport destination; //changed from String to Airport
    private LocalDateTime departureTime; //changed from String to LocalDate

    private float price; //added price

    private String flightNumber; //added flight number for some uniqueness.

    private Gate gate;
    private PassengerPlane airplane;

    public Flight(Airport destination, LocalDateTime departureTime, float price, PassengerPlane airplane) {
        this.destination = destination;
        this.departureTime =  departureTime;
        this.price = price;
        this.airplane = airplane;

        System.out.println(departureTime.toString());
        this.flightNumber = String.format("%s%s", destination.getName().substring(0, 2).toUpperCase(), departureTime.toString().substring(11, 13));
    }

    @Override
    public String toString() {
        return String.format("%s | %s |  %s   | %s", flightNumber, departureTime.format(Util.formatter), gate.toString(), destination.toString());
    }

    @Override
    public int compareTo(Flight o) {
        return this.departureTime.isBefore(o.departureTime) ? 1 : -1;
    }


    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Gate getGate() {
        return gate;
    }

    public float getPrice() {
        return price;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public PassengerPlane getAirplane() {
        return airplane;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}
