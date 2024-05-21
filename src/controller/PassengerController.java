package controller;

import model.passenger.PassengerModel;
import model.passenger.data.PassengerType;
import view.form.ErrorDialog;

public class PassengerController {

    private PassengerModel passengerModel;

    public PassengerController(PassengerModel passengerModel) {
        this.passengerModel = passengerModel;
    }

    public void updateUser(String name, String surname, String luggageCountText, String yearOfBirthText, PassengerType passengerType, int passengerId) {
        try {
            passengerModel.updateUser(name, surname, luggageCountText, yearOfBirthText, passengerType, passengerId);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    public void removeUser(int index) {
        try {
            passengerModel.removeUser(index);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    public void addUser(String name, String surname, String luggageCountText, String yearOfBirthText, PassengerType passengerType) {
        try {
            passengerModel.addUser(name, surname, luggageCountText, yearOfBirthText, passengerType);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }
}
