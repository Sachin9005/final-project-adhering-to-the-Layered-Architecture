package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.User;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    User getUserDetails()throws SQLException;
    boolean updatePassword(String password) throws SQLException;
    boolean updateEmail(String email) throws SQLException;
    boolean updatePhoneNo(String phoneNo) throws SQLException;
    boolean updateName(String name) throws SQLException;
    boolean updateaddress(String address) throws SQLException;
}
