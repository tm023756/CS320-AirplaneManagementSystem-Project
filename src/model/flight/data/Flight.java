package model.flight.data;

import model.passenger.data.PassengerType;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    private int id;

    private String departureLocation;

    private String landingLocation;

    private String departureDate;

    private List<Seat> seats;

    public Flight(String departureLocation, String landingLocation, String departureDate) {
        inflateSeats();
        setDepartureLocation(departureLocation);
        setLandingLocation(landingLocation);
        setDepartureDate(departureDate);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    private void inflateSeats() {
        seats = new ArrayList<>();
        String emptyOwner = "Empty";
        seats.add(new Seat(PassengerType.Business, emptyOwner, 1050.0));
        seats.add(new Seat(PassengerType.Economy, emptyOwner, 325.0));
        seats.add(new Seat(PassengerType.Economy, emptyOwner, 325.0));
        seats.add(new Seat(PassengerType.Economy, emptyOwner, 325.0));
        seats.add(new Seat(PassengerType.Family, emptyOwner, 150.0));
    }

    @Override
    public String toString() {
        return "Flight " + departureLocation + " to " + landingLocation + " on date " + departureDate;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        for (Character s : departureLocation.toCharArray()) {
            if (Character.isDigit(s)) {
                throw new IllegalArgumentException("Departure Location is invalid");
            }
        }
        this.departureLocation = departureLocation;
    }

    public String getLandingLocation() {
        return landingLocation;
    }

    public void setLandingLocation(String landingLocation) {
        //If landing location contains digit, it will throw Exception
        for (Character s : landingLocation.toCharArray()) {
            if (Character.isDigit(s)) {
                throw new IllegalArgumentException("Landing Location is invalid");
            }
        }
        this.landingLocation = landingLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        if (isDateValid(departureDate)) {
            this.departureDate = departureDate;
        } else {
            throw new IllegalArgumentException("Invalid Date. Enter date in format of dd/mm/yyyy");
        }
    }

    public static boolean isDateValid(String date) {
        return date.length() == 10
                && date.charAt(2) == '/' && date.charAt(5) == '/'
                && (Integer.parseInt(date.substring(0, 2))) <= 31
                && (Integer.parseInt(date.substring(3, 5))) <= 12
                && (Integer.parseInt(date.substring(6))) >= 2023;
    }


}
