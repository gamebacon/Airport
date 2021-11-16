package with.william.airport.human;

import with.william.airport.other.BoardingPass;
import with.william.airport.other.Luggage;
import with.william.airport.other.Passport;

import java.util.ArrayList;
import java.util.List;

public class Traveler extends Human {

    private Passport passport;
    private List<Luggage> luggage = new ArrayList<>();
    private List<BoardingPass> boardingpass = new ArrayList<>();

    public Traveler(Passport passport) {
        super(passport);
        this.passport = passport;
    }


    //fick göra en rename från wait -> waiting då den var final i super class.
    public void waiting() {
        System.out.println("Taking a nap...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {};
    }

    public Passport getPassport() {
        return passport;
    }

    public List<Luggage> getLuggage() {
        return luggage;
    }

    public List<BoardingPass> getBoardingpass() {
        return boardingpass;
    }
}
