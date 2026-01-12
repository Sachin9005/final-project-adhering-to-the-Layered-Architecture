package lk.ijse.DTO;

import java.time.LocalDateTime;

public class TrainingSessionsDTO {
    private int id;
    private int memberId;
    private int trainerId;
    private LocalDateTime assignedDate;

    public TrainingSessionsDTO() {
    }

    public TrainingSessionsDTO(int memberId, int trainerId, LocalDateTime assignedDate) {
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.assignedDate = assignedDate;
    }

    public TrainingSessionsDTO(int memberId, int trainerId) {
        this.memberId = memberId;
        this.trainerId = trainerId;
    }

    public TrainingSessionsDTO(int id, int memberId, int trainerId, LocalDateTime assignedDate) {
        this.id = id;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.assignedDate = assignedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "TrainingSessionsDTO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", trainerId=" + trainerId +
                ", assignedDate=" + assignedDate +
                '}';
    }
}
