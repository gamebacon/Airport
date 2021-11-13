import with.william.airport.Airport;
import with.william.airport.Flight;
import with.william.airport.Gate;
import with.william.airport.Terminal;
import with.william.airport.airplane.PassengerPlane;
import with.william.airport.human.Traveler;
import with.william.airport.other.BoardingPass;
import with.william.airport.other.FlightClass;
import with.william.airport.util.Country;
import with.william.airport.other.Passport;

import java.time.LocalDateTime;
import java.util.*;


public class Driver {

    private Scanner scanner = new Scanner(System.in);

    private enum AirportAction {
        NONE,
        EXIT,
        VIEW_MONITORS,
        VIEW_BOARDINPASS,
        GO_TO_TERMINAL,
        GO_TO_GATE,
        BUY_TICKET
    }

    private enum GateAction {
        BOARD_PLANE,
        WAIT,
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

        //lets begin in kastrup.
        Airport currentAirport = kastrup;
        Terminal currentTerminal = currentAirport.getTerminals().get(0);
        Gate currentGate = null;

        System.out.println("Who are you?");

        Passport userPassport = new Passport("William With", Country.SWEDEN, "1998-10-16"); //CreatePassport();
        Traveler traveler = new Traveler(userPassport);
        LocalDateTime time = LocalDateTime.now();

        clear();
        System.out.println("You are now ready to explore the world!");


        AirportAction action = AirportAction.NONE;

        while(action != AirportAction.EXIT) {
            System.out.println(
                    String.format("\nCurrent location: %s%s - %s \nBalance: $%.2f\n", currentTerminal.getName(), currentGate != null ? String.format(", %s", currentGate.getName()) : "", currentAirport.toString(), traveler.getBalance()));

            //display all available actions.
            for(int i = 1; i < AirportAction.values().length; i++)
                System.out.print(String.format("%d(%s) \n", i, AirportAction.values()[i]));

            //take input
            try {
                action = AirportAction.values()[Integer.parseInt(scanner.nextLine())];
                clear();
            } catch (Exception e) {}

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
                            break;
                        } catch (Exception e) {}


                    }
                }

                case VIEW_MONITORS -> {
                    currentAirport.ViewMonitors();
                }

                case VIEW_BOARDINPASS -> {
                    if(traveler.getBoardingpass().size() == 0)
                        System.out.println("No tickets purchased.");

                    for(BoardingPass boardingpass : traveler.getBoardingpass())
                        System.out.println(boardingpass.toString());
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
                        System.out.println("   Price | No.  | Time  | Gate      | Destination ");
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
                try {
                    String countryS = GetInput("Country: ", "[A-Öa-ö]+");
                    country = Country.valueOf(countryS.toUpperCase());
                    break;
                } catch (Exception e) {
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
