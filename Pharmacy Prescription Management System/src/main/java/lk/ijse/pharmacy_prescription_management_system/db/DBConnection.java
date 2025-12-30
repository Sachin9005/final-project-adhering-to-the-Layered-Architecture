package lk.ijse.pharmacy_prescription_management_system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/Pharmacy";

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "mysql";

    private static DBConnection dbConnection;
    private Connection conn;

    private DBConnection() throws SQLException {
        try {
            // ✅ Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Create connection
            conn = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD
            );

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return conn;
    }
}
