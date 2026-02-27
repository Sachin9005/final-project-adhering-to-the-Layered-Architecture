package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.UserBO;
import lk.ijse.carrentn.dao.custom.UserDAO;
import lk.ijse.carrentn.dao.custom.impl.UserDAOImpl;
import lk.ijse.carrentn.dto.UserDTO;
import lk.ijse.carrentn.entity.User;

import java.sql.SQLException;

public class UserBOimpl implements UserBO {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public UserDTO getUserDetails() throws SQLException {
        User user = userDAO.getUserDetails();
        return new UserDTO(user.getUser_id(),user.getUser_name(),user.getPassword(), user.getEmail(), Long.parseLong(user.getPhone_number()), user.getRole(), user.getName(),user.getAddress());
    }

    @Override
    public boolean updatePassword(String password) throws SQLException {
        return userDAO.updatePassword(password);
    }

    @Override
    public boolean updateEmail(String email) throws SQLException {
        return userDAO.updateEmail(email);
    }

    @Override
    public boolean updatePhoneNo(String phoneNo) throws SQLException {
        return userDAO.updatePhoneNo(phoneNo);
    }

    @Override
    public boolean updateName(String name) throws SQLException {
        return userDAO.updateName(name);
    }

    @Override
    public boolean updateaddress(String address) throws SQLException {
        return userDAO.updateaddress(address);
    }
}
