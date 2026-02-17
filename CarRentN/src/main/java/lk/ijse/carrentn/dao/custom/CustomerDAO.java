package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    boolean save(CustomerDTO cusDTO) throws SQLException;
    boolean update(CustomerDTO cusDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public List<CustomerDTO> getAllCustomer() throws SQLException;
    public CustomerDTO search(String id) throws SQLException;
    public List<String> getAllOCustomerNames() throws SQLException;
    public String searchId(String name);
    public List<String> getCustomersNotPaidLast();
}
