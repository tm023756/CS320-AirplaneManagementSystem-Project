package model.plane.list_model;

import model.plane.data.Plane;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class PlaneListModel implements ListModel {

    private ArrayList<Plane> planes;

    public PlaneListModel(ArrayList<Plane> planes) {
        this.planes = planes;
    }

    @Override
    public int getSize() {
        return planes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return planes.get(index).toString();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
