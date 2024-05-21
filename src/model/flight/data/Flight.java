package model.flight.data;

import model.passenger.data.PassengerType;

import java.util.ArrayList;

public class Flight {

    private int id;

    private String departureLocation;

    private String landingLocation;

    private String departureDate;

    private ArrayList<Seat> seats;

    public Flight(String departureLocation, String landingLocation, String departureDate) throws Exception {
        inflateSeats();
        setDepartureLocation(departureLocation);
        setLandingLocation(landingLocation);
        setDepartureDate(departureDate);
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    private void inflateSeats() {
        seats = new ArrayList<>();

        seats.add(new Seat(PassengerType.Business, "Empty", 1050.0));
        seats.add(new Seat(PassengerType.Economy, "Empty", 325.0));
        seats.add(new Seat(PassengerType.Economy, "Empty", 325.0));
        seats.add(new Seat(PassengerType.Economy, "Empty", 325.0));
        seats.add(new Seat(PassengerType.Family, "Empty", 150.0));
    }

    @Override
    public String toString() {
        return "Flight " + departureLocation + " to " + landingLocation + " on date " + departureDate;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) throws Exception {

        for (Character s : departureLocation.toCharArray()) {
            if (Character.isDigit(s)) {
                throw new Exception("Departure Location is invalid");
            }
        }
        this.departureLocation = departureLocation;
    }

    public String getLandingLocation() {
        return landingLocation;
    }

    public void setLandingLocation(String landingLocation) throws Exception {
        //If landing location contains digit, it will throw Exception
        for (Character s : landingLocation.toCharArray()) {
            if (Character.isDigit(s)) {
                throw new Exception("Landing Location is invalid");
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

    public void setDepartureDate(String departureDate) throws Exception {
        if (isDateValid(departureDate)) {
            this.departureDate = departureDate;
        } else {
            throw new Exception("Invalid Date. Enter date in format of dd/mm/yyyy");
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
