package model.passenger.list_model;

import model.passenger.data.Passenger;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class PassengerListModel implements ListModel {

    private List<Passenger> passengers;

    public PassengerListModel(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public int getSize() {
        return passengers.size();
    }

    @Override
    public Object getElementAt(int index) {
        return passengers.get(index).toString(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
