package lk.ijse.controller;

import lk.ijse.DTO.TrainersDTO;
import lk.ijse.model.TrainerModel;

public class TrainerController {
    private final TrainerModel trainerModel = new TrainerModel();
    public void saveTrainer(TrainersDTO trainersDTO)  {
        try {
            boolean isSaved = trainerModel.save(trainersDTO);
            if (isSaved) {
                System.out.println("Trainer Saved Successfully");
            }else  {
                System.out.println("Trainer Save Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateTrainer(TrainersDTO trainersDTO)  {
        try {
            boolean isUpdate = trainerModel.update(trainersDTO);
            if (isUpdate) {
                System.out.println("Trainer Update Successfully");
            }else  {
                System.out.println("Trainer Update Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteTrainer(int id)  {
        try {
            boolean isDelete = trainerModel.delete(id);
            if (isDelete) {
                System.out.println("Trainer Delete Successfully");
            }else  {
                System.out.println("Trainer Delete Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
