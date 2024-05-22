package model.passenger.data;

import java.time.LocalDate;

public abstract class Passenger {

    private int id;

    private String name;

    private String surname;

    private int yearOfBirth;

    private int luggageCount;

    private PassengerType passengerType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        if (yearOfBirth > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Invalid year of birth");
        } else {
            this.yearOfBirth = yearOfBirth;
        }
    }

    public int getLuggageCount() {
        return luggageCount;
    }

    public void setLuggageCount(int luggageCount) {
        this.luggageCount = luggageCount;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public String toString(int index) {
        return index + ". " + passengerType.name() + " Passenger" + " : " + name + " " + surname + " " + yearOfBirth;
    }
}