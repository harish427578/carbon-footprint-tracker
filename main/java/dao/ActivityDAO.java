package dao;

import model.Activity;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    public void addActivity(Activity a) throws Exception {
        String sql = "INSERT INTO activities(user_id, activity_type, quantity, date) VALUES (?, ?, ?, CURDATE())";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getUserId());
            ps.setString(2, a.getType());
            ps.setDouble(3, a.getQuantity());
            ps.executeUpdate();
        }
    }

    public List<Activity> getActivitiesByUser(int userId) throws Exception {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activities WHERE user_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Activity a = new Activity();
                    a.setId(rs.getInt("id"));
                    a.setUserId(rs.getInt("user_id"));
                    a.setType(rs.getString("activity_type"));
                    a.setQuantity(rs.getDouble("quantity"));
                    a.setDate(rs.getDate("date"));
                    list.add(a);
                }
            }
        }
        return list;
    }
}
