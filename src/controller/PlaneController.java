package controller;

import model.plane.PlaneModel;
import view.form.ErrorDialog;

public class PlaneController {

    private PlaneModel planeModel;

    public PlaneController(
            PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public void removePlane(int id) {
        try {
            planeModel.removePlane(id);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    public void addPlane(
            String name,
            int capacity
    ) {
        try {
            planeModel.addPlane(name, capacity);
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }
}
