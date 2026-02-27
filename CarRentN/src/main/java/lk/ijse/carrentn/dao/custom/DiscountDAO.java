package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.Discount;


public interface DiscountDAO extends CrudDAO<Discount>, SuperDAO {
    Discount searchId(String description);
}
