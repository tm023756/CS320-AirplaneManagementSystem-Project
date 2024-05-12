package model.flight.data;

import model.passenger.data.PassengerType;

public class Seat {
    private PassengerType passengerType;
    private String owner;
    private double price;

    public Seat(PassengerType passengerType, String owner, double price) {
        this.passengerType = passengerType;
        this.owner = owner;
        this.price = price;
    }

    public PassengerType getType() {
        return passengerType;
    }

    public double getPrice() {
        return price;
    }

    public String toString(int index) {
        String seatType = switch (passengerType) {
            case Business -> "VIP";
            case Economy -> "Econ";
            case Family -> "Fam";
        };
        return seatType + " Seat:" + index + ", " + owner;
    }

    @Override
    public String toString() {
        return "SEAT: " + owner + passengerType + price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
