package model.passenger;

import model.passenger.data.Passenger;

import java.util.ArrayList;

public class PassengerManagementSystem {

    private ArrayList<Passenger> passengers;

    public PassengerManagementSystem() {
        passengers = new ArrayList<>();
    }

    public ArrayList<Passenger> getAllPassengers() {
        return passengers;
    }

    public void addUser(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removeUser(int index) {
        passengers.remove(index);
    }

    public void updateUser(int index, Passenger updatedPassenger) {
        passengers.set(index, updatedPassenger);
    }

}
