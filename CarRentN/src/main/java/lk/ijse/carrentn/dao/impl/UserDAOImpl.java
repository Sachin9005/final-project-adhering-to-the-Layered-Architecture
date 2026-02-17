package lk.ijse.carrentn.dao.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.UserDAO;
import lk.ijse.carrentn.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    public UserDTO getUserDetails()throws SQLException {
        UserDTO userDTO = null;
        ResultSet rs  = CrudUtil.execute("SELECT * FROM user WHERE user_id = 1");
        if (rs.next()){
            userDTO = new UserDTO(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getLong("phone_number"),
                    rs.getString("role"),
                    rs.getString("name"),
                    rs.getString("address")
            );
        }
        return userDTO;
    }

    public boolean updatePassword(String password) throws SQLException {
        return CrudUtil.execute("UPDATE user SET password = ? WHERE user_id = 1 ",password );
    }

    public boolean updateEmail(String email) throws SQLException {
        return CrudUtil.execute("UPDATE user SET email = ? WHERE user_id = 1 ",email );
    }

    public boolean updatePhoneNo(String phoneNo) throws SQLException {
        return CrudUtil.execute("UPDATE user SET phone_number = ? WHERE user_id = 1 ",phoneNo );
    }

    public boolean updateName(String name) throws SQLException {
        return CrudUtil.execute("UPDATE user SET name = ? WHERE user_id = 1 ",name );
    }

    public boolean updateaddress(String address) throws SQLException {
        return CrudUtil.execute("UPDATE user SET address = ? WHERE user_id = 1 ",address );
    }
}
