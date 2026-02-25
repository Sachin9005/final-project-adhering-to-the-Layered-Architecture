package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO cusDTO) throws SQLException;
    boolean updateCustomer(CustomerDTO cusDTO) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;
    List<CustomerDTO> getAllCustomers() throws SQLException;
    CustomerDTO searchCustomer(String id) throws SQLException;
    String searchCustomerId(String name);
    List<String> getCustomersNotPaidLast();
}
