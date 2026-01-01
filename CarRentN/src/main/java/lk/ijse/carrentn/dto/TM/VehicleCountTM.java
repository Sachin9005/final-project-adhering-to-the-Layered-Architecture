package lk.ijse.carrentn.dto.TM;

public class VehicleCountTM {
    private String vehicleType;
    private int total;

    public VehicleCountTM(String vehicleType, int total) {
        this.vehicleType = vehicleType;
        this.total = total;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "VehicleCountTM{" +
                "vehicleType='" + vehicleType + '\'' +
                ", total=" + total +
                '}';
    }
}
