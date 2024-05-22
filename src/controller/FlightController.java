package controller;

import model.flight.FlightModel;
import model.flight.data.Seat;
import model.passenger.PassengerModel;
import model.passenger.data.Passenger;
import model.passenger.data.PassengerType;
import view.form.ErrorDialog;

import java.util.ArrayList;
import java.util.List;

public class FlightController {

    private FlightModel flightModel;

    private PassengerModel passengerModel;

    public FlightController(
            PassengerModel passengerModel,
            FlightModel flightModel) {
        this.flightModel = flightModel;
        this.passengerModel = passengerModel;
    }

    public void removeFlight(int id) {
        try {
            flightModel.removeFlight(id);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    public void addFlight(String departure, String landing, String departureDate, String returnDate) {
        try {
            flightModel.addFlight(departure, landing, departureDate, returnDate);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    public void buyTicket(Seat selectedSeat, String passengerIndex) {
        try {
            int id = Integer.parseInt(passengerIndex);

            Passenger passenger = passengerModel.getPassenger(id);
            PassengerType passengerType = passenger.getPassengerType();

            if (selectedSeat.getType() == passengerType) {
                flightModel.buyTicket(selectedSeat.getId(), passenger.getId());
            } else {
                new ErrorDialog("Passenger Type does not match with Seat Type");
            }
        } catch (NumberFormatException e) {
            new ErrorDialog("Enter valid passenger Id");
        } catch (IndexOutOfBoundsException e) {
            new ErrorDialog("There is no passenger with specified id");
        }
    }

    public void cancelTicket(int seatId) {
        flightModel.buyTicket(seatId, null);
    }

    public List<Seat> getSeatsByFlightId(int flightId) {
        return flightModel.getSeatsByFlightId(flightId);
    }

}
