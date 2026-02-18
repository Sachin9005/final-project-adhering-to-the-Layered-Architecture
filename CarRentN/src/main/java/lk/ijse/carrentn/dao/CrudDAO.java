package lk.ijse.carrentn.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    boolean save(T cusDTO) throws SQLException,Exception;
    boolean update(T cusDTO) throws SQLException,Exception;
    boolean delete(String id) throws SQLException,Exception;
    List<T> getAll() throws SQLException,Exception;
    T search(String id) throws SQLException,Exception;

}
