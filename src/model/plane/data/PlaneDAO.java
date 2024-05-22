package model.plane.data;

import model.local.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO {
    public static void insertPlane(Plane plane) {
        String sql = "INSERT INTO Plane (name, capacity) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, plane.getName());
            pstmt.setInt(2, plane.getCapacity());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePlane(int id) {
        String sql = "DELETE FROM Plane WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Plane> getAllPlanes() {
        ArrayList<Plane> planes = new ArrayList<>();
        String sql = "SELECT * FROM Plane";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");

                Plane plane = new Plane(name, capacity);
                plane.setId(id);
                planes.add(plane);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return planes;
    }


}