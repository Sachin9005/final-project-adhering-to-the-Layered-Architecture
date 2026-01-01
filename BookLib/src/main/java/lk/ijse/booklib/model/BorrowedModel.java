package lk.ijse.booklib.model;

import lk.ijse.booklib.DB.DBConnection;
import lk.ijse.booklib.DTO.BorrowedBooksDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowedModel {

    public boolean save(BorrowedBooksDTO borrowedBooksDTO) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            String stockDis = "UPDATE books SET available_quantity=available_quantity-1 WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(stockDis);
            preparedStatement.setInt(1, borrowedBooksDTO.getId());
            preparedStatement.executeUpdate();

            String sql = "INSERT INTO borrowed_books (student_id,book_id,borrow_date) VALUES (?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, borrowedBooksDTO.getStudentId());
            pstm.setInt(2, borrowedBooksDTO.getBookId());
            pstm.setDate(3, Date.valueOf(LocalDate.now()));
            pstm.executeUpdate();
            conn.commit();
            return  true;
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean delete(BorrowedBooksDTO borrowedBooksDTO) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            String stockInc = "UPDATE books SET available_quantity=available_quantity+1 WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(stockInc);
            preparedStatement.setInt(1, borrowedBooksDTO.getBookId());
            preparedStatement.executeUpdate();

            String sql = "DELETE FROM borrowed_books WHERE student_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, borrowedBooksDTO.getStudentId());
            pstm.executeUpdate();
            conn.commit();
            return  true;
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public List<BorrowedBooksDTO> getAllBorrowedBooks() throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        List<BorrowedBooksDTO> borrowedBooksDTOList = new ArrayList<>();
        String sql = "SELECT * FROM borrowed_books";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            borrowedBooksDTOList.add(new BorrowedBooksDTO(
                    resultSet.getInt("student_id"),
                    resultSet.getInt("book_id")
                   ));
        }
        return borrowedBooksDTOList;
    }
}
