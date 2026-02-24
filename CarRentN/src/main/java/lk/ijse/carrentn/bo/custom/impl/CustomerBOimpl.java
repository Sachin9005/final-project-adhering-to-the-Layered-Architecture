package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.CustomerBO;
import lk.ijse.carrentn.dao.custom.CustomerDAO;
import lk.ijse.carrentn.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.carrentn.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOimpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean saveCustomer(CustomerDTO cusDTO) throws SQLException {
        return customerDAO.save(cusDTO);
    }

    @Override
    public boolean updateCustomer(CustomerDTO cusDTO) throws SQLException {
        return customerDAO.update(cusDTO);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {
        return customerDAO.getAll();
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException {
        return customerDAO.search(id);
    }

    @Override
    public String searchCustomerId(String name) {
        return customerDAO.searchId(name);
    }

    @Override
    public List<String> getCustomersNotPaidLast() {
        return customerDAO.getCustomersNotPaidLast();
    }
}
