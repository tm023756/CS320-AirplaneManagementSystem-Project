package model.flight.list_model;

import model.flight.data.Seat;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class SeatListModel implements ListModel {

    private ArrayList<Seat> seats;

    public SeatListModel(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public int getSize() {
        return seats.size();
    }

    @Override
    public Object getElementAt(int index) {
        return seats.get(index).toString(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
