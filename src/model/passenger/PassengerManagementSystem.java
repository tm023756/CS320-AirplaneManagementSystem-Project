package model.passenger;

import model.passenger.data.Passenger;
import model.passenger.data.PassengerDAO;

import java.util.ArrayList;
import java.util.List;

public class PassengerManagementSystem {

    public List<Passenger> getAllPassengers() {
        return PassengerDAO.getAllPassengers();
    }

    public void addUser(Passenger passenger) {
        PassengerDAO.addPassenger(passenger);
    }

    public void removeUser(int id) {
        PassengerDAO.removePassenger(id);
    }

    public void updateUser(Passenger updatedPassenger) {
        PassengerDAO.updatePassenger(updatedPassenger);
    }

}
