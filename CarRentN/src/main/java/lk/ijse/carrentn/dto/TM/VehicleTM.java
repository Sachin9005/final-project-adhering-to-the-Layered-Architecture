package lk.ijse.carrentn.dto.TM;

public class VehicleTM {
        private int id;
        private String vehicleNo;
        private String model;
        private String type;
        private double dailyRate;

    public VehicleTM() {
    }

    public VehicleTM(String vehicleNo, String model, String type, double dailyRate) {
        this.vehicleNo = vehicleNo;
        this.model = model;
        this.type = type;
        this.dailyRate = dailyRate;
    }

    public VehicleTM(int id, String vehicleNo, String model, String type, double dailyRate) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.model = model;
        this.type = type;
        this.dailyRate = dailyRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public String toString() {
        return "VehicleTM{" +
                "id=" + id +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
