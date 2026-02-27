package lk.ijse.carrentn.entity;

import java.math.BigDecimal;

public class RentalDiscount {
    int rental_discount_id;
    int rental_id;
    int discount_id;
    BigDecimal discount_amount_applied;

    public RentalDiscount() {
    }

    public RentalDiscount(int rental_id, int discount_id, BigDecimal discount_amount_applied) {
        this.rental_id = rental_id;
        this.discount_id = discount_id;
        this.discount_amount_applied = discount_amount_applied;
    }

    public RentalDiscount(int rental_discount_id, int rental_id, int discount_id, BigDecimal discount_amount_applied) {
        this.rental_discount_id = rental_discount_id;
        this.rental_id = rental_id;
        this.discount_id = discount_id;
        this.discount_amount_applied = discount_amount_applied;
    }

    public int getRental_discount_id() {
        return rental_discount_id;
    }

    public void setRental_discount_id(int rental_discount_id) {
        this.rental_discount_id = rental_discount_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public BigDecimal getDiscount_amount_applied() {
        return discount_amount_applied;
    }

    public void setDiscount_amount_applied(BigDecimal discount_amount_applied) {
        this.discount_amount_applied = discount_amount_applied;
    }

    @Override
    public String toString() {
        return "RentalDiscount{" +
                "rental_discount_id=" + rental_discount_id +
                ", rental_id=" + rental_id +
                ", discount_id=" + discount_id +
                ", discount_amount_applied=" + discount_amount_applied +
                '}';
    }
}
