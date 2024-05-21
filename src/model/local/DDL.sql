-- Create PassengerType table
CREATE TABLE PassengerType (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               type VARCHAR(50) NOT NULL UNIQUE
);

-- Create Passenger table
CREATE TABLE Passenger (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           surname VARCHAR(255) NOT NULL,
                           yearOfBirth INT NOT NULL,
                           luggageCount INT NOT NULL,
                           passengerType_id INT,
                           FOREIGN KEY (passengerType_id) REFERENCES PassengerType(id)
);

-- Create Plane table
CREATE TABLE Plane (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       capacity INT NOT NULL
);

-- Create Flight table
CREATE TABLE Flight (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        departureLocation VARCHAR(255) NOT NULL,
                        landingLocation VARCHAR(255) NOT NULL,
                        departureDate VARCHAR(255) NOT NULL
);

-- Create Seat table
CREATE TABLE Seat (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      passengerType_id INT,
                      owner_id INT,
                      price DOUBLE NOT NULL,
                      flight_id INT,
                      FOREIGN KEY (passengerType_id) REFERENCES PassengerType(id),
                      FOREIGN KEY (owner_id) REFERENCES Passenger(id),
                      FOREIGN KEY (flight_id) REFERENCES Flight(id)
);