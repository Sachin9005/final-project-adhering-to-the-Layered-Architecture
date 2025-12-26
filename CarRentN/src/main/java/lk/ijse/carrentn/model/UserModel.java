package lk.ijse.carrentn.model;


import lk.ijse.carrentn.dto.UserDTO;
import lk.ijse.carrentn.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

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
        boolean result = CrudUtil.execute("UPDATE user SET password = ? WHERE user_id = 1 ",password );
        return result;
    }

    public boolean updateEmail(String email) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET email = ? WHERE user_id = 1 ",email );
        return result;
    }

    public boolean updatePhoneNo(String phoneNo) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET phone_number = ? WHERE user_id = 1 ",phoneNo );
        return result;
    }

    public boolean updateName(String name) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET name = ? WHERE user_id = 1 ",name );
        return result;
    }
    public boolean updateaddress(String address) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET address = ? WHERE user_id = 1 ",address );
        return result;
    }

    public boolean updateUserName(String useName) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET user_name = ? WHERE user_id = 1 ",useName );
        return result;
    }
    
}
