package lk.ijse.booklib.model;

import lk.ijse.booklib.DB.DBConnection;
import lk.ijse.booklib.DTO.StudentsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {

    public boolean save(StudentsDTO studentsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO students (name) VALUES (?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, studentsDTO.getName());
        return preparedStatement.executeUpdate()>0;
    }
    public boolean update(StudentsDTO studentsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE students SET name=? WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, studentsDTO.getName());
        preparedStatement.setInt(2, studentsDTO.getId());
        return preparedStatement.executeUpdate()>0;
    }
    public boolean delete(StudentsDTO studentsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, studentsDTO.getId());
        return preparedStatement.executeUpdate()>0;
    }
    public StudentsDTO search(int id)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        StudentsDTO studentsDTO = null;
        String sql = "SELECT * FROM students WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            studentsDTO = new StudentsDTO(resultSet.getInt("id"),resultSet.getString("name"));
        }
        return studentsDTO;
    }
    public List<StudentsDTO> getAll()throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM students";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<StudentsDTO> studentsDTOList = new ArrayList<>();
        if (resultSet.next()) {
            studentsDTOList.add(new StudentsDTO(resultSet.getInt("id"),resultSet.getString("name")));
        }
    return studentsDTOList;
    }
}
