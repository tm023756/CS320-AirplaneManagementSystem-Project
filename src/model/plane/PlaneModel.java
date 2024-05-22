package model.plane;

import model.plane.data.Plane;

import java.util.ArrayList;
import java.util.List;

public class PlaneModel {

    private PlaneManagementSystem planeData = new PlaneManagementSystem();

    private Plane checkFields(
            String planeName,
            int capacity
    ) {
        if (planeName.isEmpty()) {
            throw new IllegalArgumentException("Plane Name is Empty");
        } else {
            if (capacity == 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero!");
            } else {
                return new Plane(planeName, capacity);
            }
        }
    }

    public void addPlane(
            String planeName,
            int capacity
    ) {
        Plane newPlane = checkFields(planeName, capacity);
        planeData.addPlane(newPlane);
    }

    public List<Plane> getAllPlanes() {
        return planeData.getAllPlanes();
    }

    public Plane getPlane(int index) {
        return planeData.getAllPlanes().get(index);
    }

    public void removePlane(int id) {
        planeData.removePlane(id);
    }
}
