package lk.ijse.util;

import lk.ijse.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object ...obj) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++){
            stmt.setObject(i+1, obj[i]);
        }
        if(sql.startsWith("SELECT")|| sql.startsWith("select")){
            ResultSet rs = stmt.executeQuery();
            return (T) rs;
        }else {
            boolean result = stmt.executeUpdate()>0;
            return (T) (Boolean) result;
        }
    }
}
