package model.flight;

import model.flight.data.Flight;
import model.flight.data.FlightDAO;
import model.flight.data.Seat;
import model.flight.data.SeatDAO;

import java.util.ArrayList;
import java.util.List;

public class FlightManagementSystem {



    public List<Flight> getAllFlights() {
        return FlightDAO.getAllFlights();
    }

    public void addFlight(Flight flight) {
        FlightDAO.addFlight(flight);
    }

    public void removeFlight(int id) {
        FlightDAO.removeFlight(id);
    }

    public void buyTicket(int seatId, Integer passengerId) {
        SeatDAO.updateSeatOwner(seatId, passengerId);
    }

    public List<Seat> getSeatsByFlightId(int flightId) {
        return FlightDAO.getSeatsByFlightId(flightId);
    }

}
