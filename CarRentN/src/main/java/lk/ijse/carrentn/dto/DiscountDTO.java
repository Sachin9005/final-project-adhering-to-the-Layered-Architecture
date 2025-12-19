package lk.ijse.carrentn.dto;

public class DiscountDTO {
    private int discount_id;
    private String description;
    private double percentage;

    public DiscountDTO(String description, double percentage) {
        this.description = description;
        this.percentage = percentage;
    }

    public DiscountDTO(int discount_id, String description, double percentage) {
        this.discount_id = discount_id;
        this.description = description;
        this.percentage = percentage;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public String getDescription() {
        return description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" + "discount_id=" + discount_id + ", description=" + description + ", percentage=" + percentage + '}';
    }
    
    
    
}
