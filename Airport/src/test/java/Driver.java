import with.william.airport.Airport;
import with.william.airport.Flight;
import with.william.airport.Gate;
import with.william.airport.Terminal;
import with.william.airport.airplane.Airplane;
import with.william.airport.airplane.PassengerPlane;
import with.william.airport.human.Traveler;
import with.william.airport.other.BoardingPass;
import with.william.airport.other.FlightClass;
import with.william.airport.util.Country;
import with.william.airport.other.Passport;
import with.william.airport.util.Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class Driver {

    private Scanner scanner = new Scanner(System.in);

    private enum Action {
        NONE,
        EXIT,
        VIEW_MONITORS,
        VIEW_BOARDING_PASS,
        GO_TO_TERMINAL,
        GO_TO_GATE,
        WAIT,
        BOARD_PLANE,
        BUY_TICKET;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }



    }

    public static void main(String[] args) {
        new Driver().go();
    }


    public void go() {

        Airport tegel = new Airport("Tegel", Country.GERMANY);
        Airport arlanda = new Airport("Arlanda", Country.SWEDEN);
        Airport heathrow = new Airport("Heathrow", Country.ENGLAND);
        Airport kastrup = new Airport("Kastrup", Country.DENMARK);

        PassengerPlane airplane = new PassengerPlane();

        Flight flightToheathRow = new Flight(heathrow, LocalDateTime.parse("2021-11-20T10:23:30"), 1200, airplane);
        kastrup.scheduleFlight(0, 0, flightToheathRow);

        Flight flightToTegel = new Flight(tegel, LocalDateTime.parse("2021-11-20T19:04:30"), 1200, airplane);
        kastrup.scheduleFlight(0, 0, flightToTegel);

        Flight flightToArlanda = new Flight(arlanda, LocalDateTime.parse("2021-11-20T23:04:30"), 1200, airplane);
        kastrup.scheduleFlight(2, 2, flightToArlanda);

        Flight flightToHearthRow2 = new Flight(heathrow, LocalDateTime.parse("2021-11-20T23:59:30"), 1200, airplane);
        kastrup.scheduleFlight(2, 0, flightToHearthRow2);

        Flight flightToTegel2 = new Flight(tegel, LocalDateTime.parse("2021-11-20T22:04:30"), 1200, airplane);
        kastrup.scheduleFlight(2, 1, flightToTegel2);

        Airport currentAirport = kastrup;

        Terminal currentTerminal = currentAirport.getTerminals().get(0);
        Gate currentGate = null;
        LocalDateTime currentTime = LocalDateTime.parse("2021-11-20T06:32:30");

        System.out.println("Who are you?");

        Passport userPassport = new Passport("William With", Country.SWEDEN, "1998-10-16");//createPassport();
        Traveler traveler = new Traveler(userPassport);

        clear();
        System.out.println("Passport created!");
        System.out.println("You are now ready to explore the world!");


        Action action = Action.NONE;

        while(action != Action.EXIT) {

            System.out.println("###################################################");
            System.out.println(String.format("Location: %s%s - %s", currentTerminal.getName(), currentGate != null ? String.format(", %s", currentGate.getName()) : "", currentAirport.toString()));
            System.out.println(String.format("Balance $%.2f", traveler.getBalance()));
            System.out.println(String.format("Time %s", currentTime.format(Util.formatter)));
            System.out.println("###################################################");

            System.out.println(flightToArlanda.getDepartureTime().format(Util.formatter));
            System.out.println(currentTime.format(Util.formatter));

            Util.timeUntil(currentTime, flightToArlanda.getDepartureTime());

            //display all available actions.
            for(int i = 1; i < Action.values().length; i++)
                System.out.print(String.format("%d(%s) \n", i, Action.values()[i]));

            //take input
            try {
                action = Action.values()[Integer.parseInt(scanner.nextLine())];
                clear();
            } catch (Exception e) { action = Action.NONE; }

            System.out.println(action.toString());
            System.out.println("###################################################");

            switch (action) {

                case EXIT -> {
                    System.out.println("Come back soon!");
                    break;
                }

                case GO_TO_TERMINAL -> {
                    while(true) {
                        System.out.println("Please choose which terminal to transfer to.");

                        for (int i = 1; i <= currentAirport.getTerminals().size(); i++)
                            System.out.println(String.format("%d(Terminal %d)", i, i));

                        try {
                            currentTerminal = currentAirport.getTerminals().get(GetIntput() - 1);
                            System.out.println("Transferring to " + currentTerminal.getName() + "..");
                            currentGate = null;
                            break;
                        } catch (Exception e) {}


                    }
                }

                case GO_TO_GATE -> {
                    while(true) {
                        System.out.println("Please choose which gate to transfer to.");

                        for (int i = 1; i <= currentTerminal.getGates().size(); i++)
                            System.out.println(String.format("%d(Gate %d)", i, i));

                        try {
                            currentGate = currentTerminal.getGates().get(GetIntput() - 1);
                            System.out.println(String.format("Transferring to %s..", currentGate.getName()));
                            break;
                        } catch (Exception e) {}


                    }
                }

                case VIEW_MONITORS -> {
                    currentAirport.ViewMonitors();
                }

                case VIEW_BOARDING_PASS -> {
                    if(traveler.getBoardingpass().size() == 0)
                        System.out.println("No boarding pass purchased.");

                    for(BoardingPass boardingpass : traveler.getBoardingpass())
                        System.out.println(boardingpass.toString());
                }

                case WAIT -> {
                    String in = GetInput(String.format("Current time: %s\nHow long would you like to wait? (HH:mm)\n", currentTime.format(Util.formatter)), "[0-9]{1}[0-9]{1}:[0-9]{1}[0-9]{1}");

                    traveler.waiting();

                    int hours = Integer.parseInt(in.split(":")[0]);
                    int minutes = Integer.parseInt(in.split(":")[1]);
                    currentTime = currentTime.plusHours(hours);
                    currentTime = currentTime.plusMinutes(minutes);
                }

                case BOARD_PLANE -> {
                    if(currentGate != null) {
                        if(currentGate.getFlights().size() > 0) {
                            Collections.sort(currentGate.getFlights());

                            System.out.println("   | No.  | Time  |  Destination ");
                            System.out.println("---------------------------------------------------");
                            for(int i = 0; i < currentGate.getFlights().size(); i++) {
                                Flight flight = currentGate.getFlights().get(i);
                                System.out.println(String.format("%d) | %s", i + 1, flight.toString()));
                            }

                            System.out.println("0) Exit");
                            System.out.println("---------------------------------------------------");

                            try {
                                System.out.println("Which one would you like to board?");

                                Flight flight = currentGate.getFlights().get(GetIntput() - 1);



                                if(Duration.between(currentTime, flight.getDepartureTime()).toMinutes() > 10) {
                                    System.out.println(String.format("This flight is not boarding until %s.", Util.timeUntil(currentTime, flight.getDepartureTime())));
                                    break;
                                }

                                boolean canBoard = false;

                                for(BoardingPass boardingPass : traveler.getBoardingpass())
                                    if(boardingPass.getFlight().equals(flight)) {
                                        canBoard = true;
                                    }

                                if(canBoard) {
                                    System.out.println("Boarding..");
                                    Airplane airplane1 = flight.getAirplane();

                                    airplane1.Fly();

                                    currentAirport = flight.getDestination();
                                } else {
                                    System.out.println("You don't have a ticket for this flight!");
                                }


                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                        } else {
                            System.out.println("No flights departing from this gate.");
                        }
                    } else {
                        System.out.println("Go to a gate first.");
                    }
                }


                case BUY_TICKET -> {
                    List<Flight> allFlights = new ArrayList<Flight>();

                    for(Terminal terminal : currentAirport.getTerminals()) {
                        for(Gate gate : terminal.getGates()) {
                            allFlights.addAll(gate.getFlights());
                        }
                    }

                    Collections.sort(allFlights);

                    while(true) {
                        System.out.println("   Price | No.  | Time  | Destination ");
                        System.out.println("---------------------------------------------------");
                        for(int i = 0; i < allFlights.size(); i++) {
                            Flight flight = allFlights.get(i);
                            System.out.println(String.format("%d) $%.0f | %s", i + 1, flight.getPrice(), flight.toString()));
                        }
                        System.out.println("0) Exit");
                        System.out.println("---------------------------------------------------");

                        try {
                            System.out.println("Which one would you like?");
                            
                            Flight flight = allFlights.get(GetIntput() - 1);

                            if(flight.getPrice() <= traveler.getBalance()) {
                                FlightClass preferredClass;
                                float totalExpenses = flight.getPrice();
                                String seat = "";

                                for(String availableSeat : flight.getAirplane().passengersList.keySet())
                                    if(flight.getAirplane().passengersList.get(availableSeat) == null) //free
                                        seat = availableSeat;

                                    if(seat.equals("")) {
                                        System.out.println("Sorry, this flight is fully booked!");
                                        break;
                                    }


                                while(true) {
                                    String classTextPrompt = String.format("Would you like to purchase first class for an extra $%.1f? (yes/no)\n", FlightClass.FIRST_CLASS.GetPrice());
                                    preferredClass = GetInput(classTextPrompt, "(yes)|(no)").equals("yes") ? FlightClass.FIRST_CLASS : FlightClass.BUSINESS;

                                    if(preferredClass == FlightClass.FIRST_CLASS && traveler.getBalance() < (preferredClass.GetPrice() + flight.getPrice()))
                                        System.out.println("You can't afford this!");
                                    else {
                                        totalExpenses += FlightClass.FIRST_CLASS.GetPrice();
                                        break;
                                    }
                                }

                                BoardingPass boardingpass = new BoardingPass(flight, seat, preferredClass);

                                boolean confirmation = GetInput(String.format("%s\nTotal $%.2f, Please confirm purchase. (yes/no)\n", boardingpass.toString(), totalExpenses), "(yes)|(no)").equals("yes");

                                if(confirmation) {
                                    System.out.println("Ticket added to your inventory.");
                                    traveler.setBalance(traveler.getBalance() - totalExpenses);
                                    traveler.getBoardingpass().add(boardingpass);
                                    flight.getAirplane().passengersList.put(seat, traveler);
                                } else
                                    continue;

                            } else {
                                System.out.println(String.format("Not enough funds. ($%.2f)", traveler.getBalance()));
                            }

                            break;
                        } catch (Exception e) {
                            //e.printStackTrace();
                            break;
                        }

                    }
                }

            }


        }

    }

    public int GetIntput() {
        int out = -1;

        try {
            out = Integer.parseInt(scanner.nextLine());
        } catch(Exception e) {}

        return out;
    }



    public String GetInput(String prompt, String regex) {
        String in = "";

        while(!in.matches(regex)) {

            System.out.print(prompt);
            in = scanner.nextLine();
        }

        return in;
    }

    public void waitMilli(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {}
    }

    public void clear()  {
        for(int i = 0; i < 100; i++)
            System.out.println();
    }

    public Passport CreatePassport() {
        Passport passport;

        while(true) {

            String fullName = GetInput("Full name: ", "[A-Öa-ö- ]+");

            Country country;
            while (true) {
                    String countryS = "";
                try {
                    countryS = GetInput("Country: ", "[A-Öa-ö]+");
                    country = Country.valueOf(countryS.toUpperCase());
                    break;
                } catch (Exception e) {
                    System.out.println(String.format("The country \"%s\" was not found, try another.", countryS));
                }
            }

            String dateOfBirth = GetInput("Date of birth (yyyy-MM-dd): ", "(\\d){4}[-](\\d){2}[-](\\d){2}");

            System.out.println("Now press \"Enter\" with your thump.");
            scanner.nextLine();
            System.out.println("Scanning fingerprint...");
            waitMilli(1500);
            System.out.println("Done Scanning.");
            waitMilli(2000);
            clear();

            passport = new Passport(fullName, country, dateOfBirth);
            System.out.println(passport.toString());

            String in = GetInput("Please verify, is this correct? (yes/no)\n", "(yes)|(no)");
            System.out.println(in);

            if(in.equalsIgnoreCase("yes"))
                break;
        }
        return passport;
    }

}
