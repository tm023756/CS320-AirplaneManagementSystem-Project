package model.plane;

import model.plane.data.Plane;
import model.plane.data.PlaneDAO;

import java.util.ArrayList;

public class PlaneManagementSystem {

    public ArrayList<Plane> getAllPlanes() {
        return PlaneDAO.getAllPlanes();
    }

    public void addPlane(Plane plane) {
        PlaneDAO.insertPlane(plane);
    }

    public void removePlane(int id) {
        PlaneDAO.removePlane(id);
    }
}
