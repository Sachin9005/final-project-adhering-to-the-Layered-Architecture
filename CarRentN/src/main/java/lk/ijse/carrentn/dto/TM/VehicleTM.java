package lk.ijse.carrentn.dto.TM;

public class VehicleTM {
        private int id;
        private String model;
        private String type;
        private double dailyRate;

    public VehicleTM() {
    }

    public VehicleTM(String model, String type, double dailyRate) {
        this.model = model;
        this.type = type;
        this.dailyRate = dailyRate;
    }

    public VehicleTM(int id, String model, String type, double dailyRate) {
        this.id = id;
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
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
