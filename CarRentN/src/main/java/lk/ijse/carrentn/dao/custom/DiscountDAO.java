package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDAO extends CrudDAO<DiscountDTO> {
    DiscountDTO searchId(String description);
}
