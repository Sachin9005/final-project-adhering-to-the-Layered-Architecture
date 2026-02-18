package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.UserDTO;

import java.sql.SQLException;

public interface UserDAO {
    UserDTO getUserDetails()throws SQLException;
    boolean updatePassword(String password) throws SQLException;
    boolean updateEmail(String email) throws SQLException;
    boolean updatePhoneNo(String phoneNo) throws SQLException;
    boolean updateName(String name) throws SQLException;
    boolean updateaddress(String address) throws SQLException;
}
