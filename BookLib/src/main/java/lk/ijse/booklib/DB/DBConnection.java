package lk.ijse.booklib.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DB_URL ="jdbc:mysql://localhost:3306/book_store";
    private final String DB_USER ="root";
    private final String DB_PASS ="mysql";

    private static DBConnection dbConnection;
    private Connection conn;

    private DBConnection() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
    }
    public static DBConnection getInstance() throws SQLException {
       return dbConnection==null?new DBConnection():dbConnection;
    }
    public Connection getConnection(){
        return conn;
    }
}
