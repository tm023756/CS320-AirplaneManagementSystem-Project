package model.flight;

import model.flight.data.Flight;

import java.util.ArrayList;

public class FlightManagementSystem {

    private ArrayList<Flight> flights;

    public FlightManagementSystem() {
        flights = new ArrayList<>();
    }

    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void removeFlight(int index) {
        flights.remove(index);
    }

}
