package lk.ijse.booklib.model;

import lk.ijse.booklib.DB.DBConnection;
import lk.ijse.booklib.DTO.BooksDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel {
    public boolean save(BooksDTO booksDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO books(title,available_quantity) VALUES(?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, booksDTO.getTitle());
        preparedStatement.setInt(2, booksDTO.getAvailableQuantity());
        return preparedStatement.executeUpdate()>0;
    }
    public boolean update(BooksDTO booksDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE books SET title=?,available_quantity=? WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, booksDTO.getTitle());
        preparedStatement.setInt(2, booksDTO.getAvailableQuantity());
        preparedStatement.setInt(3, booksDTO.getId());
        return preparedStatement.executeUpdate()>0;
    }
    public boolean delete(BooksDTO booksDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM books WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, booksDTO.getId());
        return preparedStatement.executeUpdate()>0;
    }

    public BooksDTO findById(int id)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        BooksDTO booksDTO = null;
        String sql = "SELECT * FROM books WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            booksDTO = new BooksDTO(
                    resultSet.getString("title"),
                    resultSet.getInt("available_quantity")
            );
        }
        return booksDTO;
    }
    public List<BooksDTO> findAll()throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM books";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BooksDTO> booksDTOList = new ArrayList<>();
        if (resultSet.next()) {
            BooksDTO booksDTO = new BooksDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("available_quantity")
            );
        booksDTOList.add(booksDTO);
        }
    return booksDTOList;
    }
}
