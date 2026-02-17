package lk.ijse.carrentn.dao;

import java.sql.*;

import lk.ijse.carrentn.db.DBConnection;

public class CrudUtil {

    public static <T> T execute(String sql, Object... obj) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        PreparedStatement ptsm = conn.prepareStatement(sql);

        for(int i=0; i<obj.length; i++) {
            ptsm.setObject(i + 1, obj[i]);
        }

        if(sql.startsWith("select") || sql.startsWith("SELECT")) {

            ResultSet results = ptsm.executeQuery();


            return (T)results; // ResultSet

        } else {

            int result = ptsm.executeUpdate();

            boolean results = result>0;

            return (T)(Boolean)results; // boolean

        }

    }
    public static int executeInsertAndGetId(String sql, Object... obj)
            throws SQLException {

        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < obj.length; i++) {
            pst.setObject(i + 1, obj[i]);
        }

        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new SQLException("Failed to retrieve generated ID");
    }
}





