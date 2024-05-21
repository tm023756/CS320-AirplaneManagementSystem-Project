package model.passenger.data;

public enum PassengerType {
    Business(1),
    Economy(2),
    Family(3);

    private final int id;

    PassengerType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PassengerType fromId(int id) {
        for (PassengerType type : PassengerType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id for PassengerType: " + id);
    }
}
