package model.passenger.data;

public class FamilyPassenger extends Passenger{

    public FamilyPassenger(String name, String surname, int luggageCount, int yearOfBirth) throws Exception {
        setName(name);
        setSurname(surname);
        setLuggageCount(luggageCount);
        setYearOfBirth(yearOfBirth);
        setPassengerType(PassengerType.Family);
    }

}
