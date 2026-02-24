package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.dto.CustomerDTO;

import java.util.List;

public interface CustomerDAO extends CrudDAO<CustomerDTO>, SuperDAO {
    public String searchId(String name);
    public List<String> getCustomersNotPaidLast();
}
