package lk.ijse.carrentn.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    boolean save(T cusDTO) throws SQLException;
    boolean update(T cusDTO) throws SQLException;
    boolean delete(String id) throws SQLException;
    List<T> getAll() throws SQLException;
    T search(String id) throws SQLException;

}
