package lk.ijse.booklib.util;

import lk.ijse.booklib.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object... params) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        if(sql.startsWith("select")||sql.startsWith("SELECT")){
            ResultSet rs = stmt.executeQuery();
            return (T) rs;
        }else {
            boolean result = stmt.executeUpdate(sql) > 0;
            return (T) (Boolean) result;
        }
    }
}
