package model.flight.list_model;

import model.flight.data.Flight;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class FlightListModel implements ListModel {

    private List<Flight> flights;

    public FlightListModel(List<Flight> passengers) {
        this.flights = passengers;
    }

    @Override
    public int getSize() {
        return flights.size();
    }

    @Override
    public Object getElementAt(int index) {
        return flights.get(index).toString();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
