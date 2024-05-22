package model.flight.data;

import model.local.DatabaseConnection;
import model.passenger.data.PassengerType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public static void addFlight(Flight flight) {
        String sql = "INSERT INTO Flight (departureLocation, landingLocation, departureDate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, flight.getDepartureLocation());
            pstmt.setString(2, flight.getLandingLocation());
            pstmt.setString(3, flight.getDepartureDate());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    flight.setId(generatedKeys.getInt(1));
                }
            }

            addSeats(flight.getSeats(), flight.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addSeats(List<Seat> seats, int flightId) {
        String sql = "INSERT INTO Seat (passengerType_id, owner_id, price, flight_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Seat seat : seats) {
                pstmt.setInt(1, seat.getPassengerType().getId());
                pstmt.setNull(2, Types.INTEGER);
                pstmt.setDouble(3, seat.getPrice());
                pstmt.setInt(4, flightId);
                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to remove a flight from the database
    public static void removeFlight(int id) {
        String deleteSeatsSql = "DELETE FROM Seat WHERE flight_id = ?";
        String deleteFlightSql = "DELETE FROM Flight WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement pstmtDeleteSeats = conn.prepareStatement(deleteSeatsSql);
                 PreparedStatement pstmtDeleteFlight = conn.prepareStatement(deleteFlightSql)) {

                // Delete associated seats
                pstmtDeleteSeats.setInt(1, id);
                pstmtDeleteSeats.executeUpdate();

                // Delete flight
                pstmtDeleteFlight.setInt(1, id);
                pstmtDeleteFlight.executeUpdate();

                conn.commit(); // Commit transaction

            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on error
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Method to get all flights from the database
    public static List<Flight> getAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM Flight";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String departureLocation = rs.getString("departureLocation");
                String landingLocation = rs.getString("landingLocation");
                String departureDate = rs.getString("departureDate");

                Flight flight = new Flight(departureLocation, landingLocation, departureDate);
                flight.setId(id);
//                flight.setSeats(getSeatsByFlightId(id)); // Method to retrieve seats for a flight

                flights.add(flight);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flights;
    }

    public static List<Seat> getSeatsByFlightId(int flightId) {
        ArrayList<Seat> seats = new ArrayList<>();
        String sql = "SELECT s.id, s.passengerType_id, s.owner_id, s.price, p.name AS owner_name " +
                "FROM Seat s LEFT JOIN Passenger p ON s.owner_id = p.id " +
                "WHERE s.flight_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, flightId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int passengerTypeId = rs.getInt("passengerType_id");
                    double price = rs.getDouble("price");
                    String ownerName = rs.getString("owner_name");
                    if (ownerName == null) {
                        ownerName = "Empty";
                    }

                    PassengerType passengerType = PassengerType.fromId(passengerTypeId);
                    Seat seat = new Seat(passengerType, ownerName, price);
                    seat.setId(id);
                    seats.add(seat);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seats;
    }

}