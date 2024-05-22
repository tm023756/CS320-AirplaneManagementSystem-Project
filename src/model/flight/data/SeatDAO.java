package model.flight.data;

import model.local.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatDAO {

    public static void updateSeatOwner(int seatId, Integer newOwnerId) {
        String sql = "UPDATE Seat SET owner_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (newOwnerId != null) {
                pstmt.setInt(1, newOwnerId);
            } else {
                pstmt.setNull(1, java.sql.Types.INTEGER); // Set null for SQL type INTEGER
            }
            pstmt.setInt(2, seatId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
