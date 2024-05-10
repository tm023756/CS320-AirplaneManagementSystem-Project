package model.plane.data;

public class Plane {
    private String name;
    private int capacity;

    public Plane(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Plane " + name + " : Is available : true, Capacity : " + capacity;
    }
}
