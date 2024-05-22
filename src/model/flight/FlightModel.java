package model.flight;

import kotlin.Pair;
import model.flight.data.Flight;
import model.flight.data.Seat;

import java.util.ArrayList;
import java.util.List;

public class FlightModel {

    private FlightManagementSystem flightData = new FlightManagementSystem();

    private Pair<Flight, Flight> checkFields(
            String departure,
            String landing,
            String departureDate,
            String returnDate
    ) {
        if (departure.isEmpty() || landing.isEmpty() || departureDate.isEmpty()) {
            throw new IllegalArgumentException("Fill all fields");
        } else {
            if (!Flight.isDateValid(departureDate)) {
                throw new IllegalArgumentException("Enter valid departure date (dd/mm/yyyy)");
            } else if (!Flight.isDateValid(returnDate)) {
                throw new IllegalArgumentException("Enter valid return date (dd/mm/yyyy)");
            } else {
                return new Pair<>(
                        new Flight(departure, landing, departureDate),
                        new Flight(landing, departure, returnDate)
                );
            }
        }
    }

    public void addFlight(
            String departure,
            String landing,
            String departureDate,
            String returnDate
    ) {
        Flight newFlight1 = checkFields(departure, landing, departureDate, returnDate).getFirst();
        Flight newFlight2 = checkFields(departure, landing, departureDate, returnDate).getSecond();
        flightData.addFlight(newFlight1);
        flightData.addFlight(newFlight2);
    }

    public List<Flight> getAllFlights() {
        return flightData.getAllFlights();
    }

    public Flight getFlight(int index) {
        return flightData.getAllFlights().get(index);
    }

    public void removeFlight(int id) {
        flightData.removeFlight(id);
    }

    public void buyTicket(int seatId, Integer passengerId) {
        flightData.buyTicket(seatId, passengerId);
    }

    public List<Seat> getSeatsByFlightId(int flightId) {
        return flightData.getSeatsByFlightId(flightId);
    }

}
