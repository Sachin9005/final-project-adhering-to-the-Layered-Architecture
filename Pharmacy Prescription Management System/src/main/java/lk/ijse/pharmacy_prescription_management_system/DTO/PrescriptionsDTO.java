package lk.ijse.pharmacy_prescription_management_system.DTO;

import java.time.LocalDate;

public class PrescriptionsDTO {
    private int id;
    private int patientId;
    private int medicineId;
    private LocalDate prescribedDate;

    public PrescriptionsDTO() {
    }

    public PrescriptionsDTO(int patientId, int medicineId, LocalDate prescribedDate) {
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.prescribedDate = prescribedDate;
    }

    public PrescriptionsDTO(int patientId, int medicineId) {
        this.patientId = patientId;
        this.medicineId = medicineId;
    }

    public PrescriptionsDTO(int id, int patientId, int medicineId, LocalDate prescribedDate) {
        this.id = id;
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.prescribedDate = prescribedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDate getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(LocalDate prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    @Override
    public String toString() {
        return "PrescriptionsDTO{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", medicineId=" + medicineId +
                ", prescribedDate=" + prescribedDate +
                '}';
    }
}
