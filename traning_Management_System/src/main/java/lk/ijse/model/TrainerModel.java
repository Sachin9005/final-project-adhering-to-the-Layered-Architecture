package lk.ijse.model;

import lk.ijse.DTO.TrainersDTO;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerModel {
    public boolean save(TrainersDTO trainersDTO) throws SQLException {
        String sql = "INSERT INTO trainers(name,max_trainees) VALUES (?,?)";
        return (CrudUtil.execute(sql,trainersDTO.getName(),trainersDTO.getMax_trainees()));
    }
    public boolean update(TrainersDTO trainersDTO) throws SQLException {
        String sql = "UPDATE trainers SET name=?,max_trainees=?WHERE id=?";
        return (CrudUtil.execute(sql,trainersDTO.getName(),trainersDTO.getMax_trainees(),trainersDTO.getId()));
    }
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM trainers WHERE id=?";
        return (CrudUtil.execute(sql,id));
    }
    public TrainersDTO getTrainerById(int id) throws SQLException {
        String sql = "SELECT * FROM trainers WHERE id=?";
        TrainersDTO trainersDTO = null;
        ResultSet rs = CrudUtil.execute(sql,id);
        if (rs.next()) {
            trainersDTO = new TrainersDTO(
                    rs.getString("name"),
                    rs.getInt("max_trainees"),
                    rs.getInt("current_trainee_count")
            );
        }
        return trainersDTO;
    }
}
