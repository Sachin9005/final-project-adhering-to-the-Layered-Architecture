package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.UserDTO;

import java.sql.SQLException;

public interface UserDAO {
    public UserDTO getUserDetails()throws SQLException;
    public boolean updatePassword(String password) throws SQLException;
    public boolean updateEmail(String email) throws SQLException;
    public boolean updatePhoneNo(String phoneNo) throws SQLException;
    public boolean updateName(String name) throws SQLException;
    public boolean updateaddress(String address) throws SQLException;
}
