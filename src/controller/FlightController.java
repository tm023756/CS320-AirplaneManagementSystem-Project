package controller;

import model.flight.FlightModel;
import model.flight.data.Flight;
import model.flight.data.Seat;
import model.passenger.PassengerModel;
import model.passenger.data.Passenger;
import model.passenger.data.PassengerType;
import view.form.ErrorDialog;

public class FlightController {

    private FlightModel flightModel;

    private PassengerModel passengerModel;

    public FlightController(
            PassengerModel passengerModel,
            FlightModel flightModel) {
        this.flightModel = flightModel;
        this.passengerModel = passengerModel;
    }

    public void removeFlight(int index) {
        try {
            flightModel.removeFlight(index);
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

    public void buyTicket(Flight selectedFlight, int selectedSeatIndex, String passengerId) {
        try {
            int id = Integer.parseInt(passengerId);

            Passenger passenger = passengerModel.getPassenger(id);
            PassengerType passengerType = passenger.getPassengerType();

            Seat selectedSeat = selectedFlight.getSeats().get(selectedSeatIndex);

            if (selectedSeat.getType() == passengerType) {
                selectedSeat.setOwner(passenger.getName());
            } else {
                new ErrorDialog("Passenger Type does not match with Seat Type");
            }
        } catch (NumberFormatException e) {
            new ErrorDialog("Enter valid passenger Id");
        } catch (IndexOutOfBoundsException e) {
            new ErrorDialog("There is no passenger with specified id");
        }
    }

    public void cancelTicket(Flight selectedFlight, int selectedSeatIndex) {
        selectedFlight.getSeats().get(selectedSeatIndex).setOwner("Empty");
    }
}
