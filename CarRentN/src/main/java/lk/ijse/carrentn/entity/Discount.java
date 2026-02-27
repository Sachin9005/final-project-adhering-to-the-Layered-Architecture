package lk.ijse.carrentn.entity;

import java.math.BigDecimal;

public class Discount {
    int discount_id;
    String description;
    BigDecimal percentage;

    public Discount() {
    }

    public Discount(String description, BigDecimal percentage) {
        this.description = description;
        this.percentage = percentage;
    }

    public Discount(int discount_id, String description, BigDecimal percentage) {
        this.discount_id = discount_id;
        this.description = description;
        this.percentage = percentage;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discount_id='" + discount_id + '\'' +
                ", description='" + description + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
