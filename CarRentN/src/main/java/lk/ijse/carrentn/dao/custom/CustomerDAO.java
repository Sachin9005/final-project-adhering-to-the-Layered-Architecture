package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer>, SuperDAO {
    String searchId(String name);
    List<String> getCustomersNotPaidLast();
}
