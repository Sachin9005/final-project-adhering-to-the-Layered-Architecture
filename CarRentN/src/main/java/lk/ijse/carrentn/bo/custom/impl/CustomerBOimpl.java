package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.CustomerBO;
import lk.ijse.carrentn.dao.custom.CustomerDAO;
import lk.ijse.carrentn.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.carrentn.dto.CustomerDTO;
import lk.ijse.carrentn.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOimpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public boolean saveCustomer(CustomerDTO cusDTO) throws SQLException {
        Customer customer = new Customer(cusDTO.getName(),cusDTO.getEmail(),cusDTO.getPhone_number(),cusDTO.getNic_or_passport_number(),cusDTO.getAddress());
        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO cusDTO) throws SQLException {
        Customer customer = new Customer(cusDTO.getCustomer_id(),cusDTO.getName(),cusDTO.getEmail(),cusDTO.getPhone_number(),cusDTO.getNic_or_passport_number(),cusDTO.getAddress());
        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {
        List<Customer> customers =  customerDAO.getAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getCustomer_id(), customer.getName(),customer.getEmail(),customer.getPhone_number(),customer.getNic_or_passport_number(),customer.getAddress()));
        }
        return customerDTOs;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomer_id(), customer.getName(),customer.getEmail(),customer.getPhone_number(),customer.getNic_or_passport_number(),customer.getAddress());
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
