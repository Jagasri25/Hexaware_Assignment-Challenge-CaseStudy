package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PropertyUtil {
    public static Connection getConnection() {
    	String dbUrl = DBConnection.getPropertyString("C:/Users/jagi2/eclipse-workspace/FinanceManagementSystem/src/resources/db.properties");

        try {
            return DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB: " + e.getMessage());
        }
    }
}
