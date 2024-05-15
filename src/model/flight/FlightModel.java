package model.flight;

import kotlin.Pair;
import model.flight.data.Flight;

import java.util.ArrayList;

public class FlightModel {

    private FlightManagementSystem flightData = new FlightManagementSystem();

    private Pair<Flight, Flight> checkFields(
            String departure,
            String landing,
            String departureDate,
            String returnDate
    ) throws Exception {
        if (departure.isEmpty() || landing.isEmpty() || departureDate.isEmpty()) {
            throw new Exception("Fill all fields");
        } else {
            if (!Flight.isDateValid(departureDate)) {
                throw new Exception("Enter valid departure date (dd/mm/yyyy)");
            } else if (!Flight.isDateValid(returnDate)) {
                throw new Exception("Enter valid return date (dd/mm/yyyy)");
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
    ) throws Exception {
        Flight newFlight1 = checkFields(departure, landing, departureDate, returnDate).getFirst();
        Flight newFlight2 = checkFields(departure, landing, departureDate, returnDate).getSecond();
        flightData.addFlight(newFlight1);
        flightData.addFlight(newFlight2);
    }

    public ArrayList<Flight> getAllFlights() {
        return flightData.getAllFlights();
    }

    public Flight getFlight(int index) {
        return flightData.getAllFlights().get(index);
    }

    public void removeFlight(int index) {
        flightData.removeFlight(index);
    }

}
