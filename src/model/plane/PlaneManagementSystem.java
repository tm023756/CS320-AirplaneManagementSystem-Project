package model.plane;

import model.plane.data.Plane;

import java.util.ArrayList;

public class PlaneManagementSystem {

    private ArrayList<Plane> planes;

    public PlaneManagementSystem() {
        planes = new ArrayList<>();
    }

    public ArrayList<Plane> getAllPlanes() {
        return planes;
    }

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

    public void removePlane(int index) {
        planes.remove(index);
    }
}
