package with.william.airport.other;

//added flightclass
public enum FlightClass {
    BUSINESS(0),
    FIRST_CLASS(50);


    private FlightClass(float price) {
        this.price = price;
    }

    private float price;

    public float GetPrice() {
        return price;
    }
}
