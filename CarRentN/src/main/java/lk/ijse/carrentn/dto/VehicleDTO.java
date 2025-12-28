package lk.ijse.carrentn.dto;

public class VehicleDTO {
    private int vehicle_id;
    private String vehicleNo;
    private int owner_id;
    private String model;
    private String manufacturer;
    private String type;
    private double rate_per_day;
    private double ownership_percentage;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicleNo, int owner_id, String model, String manufacturer, String type, double rate_per_day, double ownership_percentage) {
        this.vehicleNo = vehicleNo;
        this.owner_id = owner_id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.rate_per_day = rate_per_day;
        this.ownership_percentage = ownership_percentage;
    }

    public VehicleDTO(int vehicle_id, String vehicleNo, int owner_id, String model, String manufacturer, String type, double rate_per_day, double ownership_percentage) {
        this.vehicle_id = vehicle_id;
        this.vehicleNo = vehicleNo;
        this.owner_id = owner_id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.rate_per_day = rate_per_day;
        this.ownership_percentage = ownership_percentage;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate_per_day() {
        return rate_per_day;
    }

    public void setRate_per_day(double rate_per_day) {
        this.rate_per_day = rate_per_day;
    }

    public double getOwnership_percentage() {
        return ownership_percentage;
    }

    public void setOwnership_percentage(double ownership_percentage) {
        this.ownership_percentage = ownership_percentage;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicle_id=" + vehicle_id +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", owner_id=" + owner_id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", rate_per_day=" + rate_per_day +
                ", ownership_percentage=" + ownership_percentage +
                '}';
    }
}
