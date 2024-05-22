package model.passenger.data;

import model.local.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {

    public static List<Passenger> getAllPassengers() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        String sql = "SELECT * FROM Passenger";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int yearOfBirth = rs.getInt("yearOfBirth");
                int luggageCount = rs.getInt("luggageCount");
                PassengerType passengerType = PassengerType.fromId((rs.getInt("passengerType_id")));

                Passenger passenger = null;
                switch (passengerType) {
                    case Business -> {
                        try {
                            passenger = new BusinessPassenger(name, surname, luggageCount, yearOfBirth);
                        } catch (Exception ignored) {}
                    }
                    case Economy -> {
                        try {
                            passenger = new EconomyPassenger(name, surname, luggageCount, yearOfBirth);
                        } catch (Exception ignored) {}
                    }
                    case Family -> {
                        try {
                            passenger = new FamilyPassenger(name, surname, luggageCount, yearOfBirth);
                        } catch (Exception ignored) {}
                    }
                }
                if (passenger != null) {
                    passenger.setId(id);
                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passengers;
    }

    public static void addPassenger(Passenger passenger) {
        String sql = "INSERT INTO Passenger (name, surname, yearOfBirth, luggageCount, passengerType_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, passenger.getName());
            pstmt.setString(2, passenger.getSurname());
            pstmt.setInt(3, passenger.getYearOfBirth());
            pstmt.setInt(4, passenger.getLuggageCount());
            pstmt.setInt(5, passenger.getPassengerType().getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePassenger(Passenger passenger) {
        String sql = "UPDATE Passenger SET name = ?, surname = ?, yearOfBirth = ?, luggageCount = ?, passengerType_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, passenger.getName());
            pstmt.setString(2, passenger.getSurname());
            pstmt.setInt(3, passenger.getYearOfBirth());
            pstmt.setInt(4, passenger.getLuggageCount());
            pstmt.setInt(5, passenger.getPassengerType().getId());
            pstmt.setInt(6, passenger.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePassenger(int id) {
        String sql = "DELETE FROM Passenger WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
