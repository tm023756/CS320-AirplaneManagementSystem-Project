package model.plane;

import model.plane.data.Plane;
import model.plane.data.PlaneDAO;

import java.util.ArrayList;
import java.util.List;

public class PlaneManagementSystem {

    public List<Plane> getAllPlanes() {
        return PlaneDAO.getAllPlanes();
    }

    public void addPlane(Plane plane) {
        PlaneDAO.insertPlane(plane);
    }

    public void removePlane(int id) {
        PlaneDAO.removePlane(id);
    }
}
