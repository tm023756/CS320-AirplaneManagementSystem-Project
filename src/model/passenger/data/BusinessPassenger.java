package model.passenger.data;

public class BusinessPassenger extends Passenger{

    public BusinessPassenger(String name, String surname, int luggageCount, int yearOfBirth) throws Exception {
        setName(name);
        setSurname(surname);
        setLuggageCount(luggageCount);
        setYearOfBirth(yearOfBirth);
        setPassengerType(PassengerType.Business);
    }
}
