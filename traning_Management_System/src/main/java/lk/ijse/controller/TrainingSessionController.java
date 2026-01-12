package lk.ijse.controller;

import lk.ijse.DTO.TrainingSessionsDTO;
import lk.ijse.model.MemeberModel;
import lk.ijse.model.TrainerModel;
import lk.ijse.model.TrainingSessionModel;

import java.util.List;

public class TrainingSessionController {
    private final TrainingSessionModel trainingSessionModel = new TrainingSessionModel();
    private final TrainerModel trainerModel = new TrainerModel();
    private final MemeberModel memeberModel = new MemeberModel();

    public void assignTrainer(TrainingSessionsDTO trainingSessionsDTO){
        try {
            boolean isAssigned = trainingSessionModel.assign(trainingSessionsDTO);
            if(isAssigned){
                System.out.println("Trainer Assigned Successfully");
            }else {
                System.out.println("Trainer Assigned Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeTrainer(TrainingSessionsDTO trainingSessionsDTO){
        try {
            boolean isRemoved = trainingSessionModel.removeAssign(trainingSessionsDTO);
            if(isRemoved){
                System.out.println("Trainer Removed Successfully");
            }else {
                System.out.println("Trainer Removed Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lordTrainingSession(){
        try {
            List<TrainingSessionsDTO> trainingSessionsDTOS = trainingSessionModel.getAllTrainingSessions();
            for(TrainingSessionsDTO trainingSessionsDTO : trainingSessionsDTOS){
                String memberName = memeberModel.get(trainingSessionsDTO.getMemberId()).getName();
                String trainerName = trainerModel.getTrainerById(trainingSessionsDTO.getTrainerId()).getName();
                System.out.printf("%-20s %-20s%n",
                        trainerName,
                        memberName
                );
                System.out.println("---------------------------------");}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
