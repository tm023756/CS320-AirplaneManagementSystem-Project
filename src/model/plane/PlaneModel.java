package model.plane;

import model.plane.data.Plane;

import java.util.ArrayList;

public class PlaneModel {

    private PlaneManagementSystem planeData = new PlaneManagementSystem();

    private Plane checkFields(
            String planeName,
            int capacity
    ) throws Exception {
        if (planeName.isEmpty()) {
            throw new Exception("Plane Name is Empty");
        } else {
            if (capacity == 0) {
                throw new Exception("Capacity must be greater than zero!");
            } else {
                return new Plane(planeName, capacity);
            }
        }
    }

    public void addPlane(
            String planeName,
            int capacity
    ) throws Exception {
        Plane newPlane = checkFields(planeName, capacity);
        planeData.addPlane(newPlane);
    }

    public ArrayList<Plane> getAllPlanes() {
        return planeData.getAllPlanes();
    }

    public Plane getPlane(int index) {
        return planeData.getAllPlanes().get(index);
    }

    public void removePlane(int id) {
        planeData.removePlane(id);
    }
}
