package lk.ijse.carrentn.dto;

public class RentalDiscountDTO {
    private int rental_discount_id;
    private int rental_id;
    private int discount_id;
    private double discount_amount_applied;

    public RentalDiscountDTO() {
    }

    public RentalDiscountDTO(int rental_id, int discount_id, double discount_amount_applied) {
        this.rental_id = rental_id;
        this.discount_id = discount_id;
        this.discount_amount_applied = discount_amount_applied;
    }

    public RentalDiscountDTO(int rental_discount_id, int rental_id, int discount_id, double discount_amount_applied) {
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

    public double getDiscount_amount_applied() {
        return discount_amount_applied;
    }

    public void setDiscount_amount_applied(double discount_amount_applied) {
        this.discount_amount_applied = discount_amount_applied;
    }

    @Override
    public String toString() {
        return "RentalDiscountDTO{" +
                "rental_discount_id=" + rental_discount_id +
                ", rental_id=" + rental_id +
                ", discount_id=" + discount_id +
                ", discount_amount_applied=" + discount_amount_applied +
                '}';
    }
}
