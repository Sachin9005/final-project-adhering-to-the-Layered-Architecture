package lk.ijse.model;

import lk.ijse.DB.DBConnection;
import lk.ijse.DTO.TrainingSessionsDTO;
import lk.ijse.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingSessionModel {
    private final TrainerModel trainerModel =  new TrainerModel();

    public boolean assign(TrainingSessionsDTO trainingSessionsDTO)throws SQLException  {
        Connection conn = DBConnection.getInstance().getConnection();
        int maxCount = trainerModel.getTrainerById(trainingSessionsDTO.getTrainerId()).getMax_trainees();
        int currentTraineesCount = trainerModel.getTrainerById(trainingSessionsDTO.getTrainerId()).getCurrent_trainees_count();

        try {
            conn.setAutoCommit(false);
            if (maxCount < currentTraineesCount+1) {
                System.out.println("Training Count is Overload");
                conn.rollback();
                return false;
            }
            CrudUtil.execute("UPDATE trainers SET current_trainee_count = current_trainee_count + 1  WHERE id=?",trainingSessionsDTO.getTrainerId());

            CrudUtil.execute("INSERT INTO training_sessions(member_id,trainer_id)VALUES(?,?)",trainingSessionsDTO.getMemberId(),trainingSessionsDTO.getTrainerId());
            conn.commit();
            return true;
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean removeAssign(TrainingSessionsDTO trainingSessionsDTO)throws SQLException  {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            CrudUtil.execute("UPDATE trainers SET current_trainee_count = current_trainee_count - 1  WHERE id=?",trainingSessionsDTO.getTrainerId());
            CrudUtil.execute("DELETE FROM training_sessions WHERE id=?",trainingSessionsDTO.getId());
            conn.commit();
            return true;
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public List<TrainingSessionsDTO> getAllTrainingSessions()throws SQLException  {
        ResultSet rs = CrudUtil.execute("SELECT * FROM training_sessions");
        List<TrainingSessionsDTO> trainingSessionsDTOList = new ArrayList<>();
        if (rs.next()) {
            trainingSessionsDTOList.add(new TrainingSessionsDTO(
                    rs.getInt("member_id"),
                    rs.getInt("trainer_id")
            ));
        }
        return  trainingSessionsDTOList;
    }

}
