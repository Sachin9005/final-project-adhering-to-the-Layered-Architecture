package lk.ijse.carrentn.dto;

public class CarOwnerDTO {
    private int owner_id;
    private String name;
    private String phone;
    private String bank_account;

    public CarOwnerDTO(int owner_id) {
        this.owner_id = owner_id;
    }

    public CarOwnerDTO(String name, String phone, String bank_account) {
        this.name = name;
        this.phone = phone;
        this.bank_account = bank_account;
    }

    public CarOwnerDTO(int owner_id, String name, String phone, String bank_account) {
        this.owner_id = owner_id;
        this.name = name;
        this.phone = phone;
        this.bank_account = bank_account;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    @Override
    public String toString() {
        return "CarOwnerDTO{" + "owner_id=" + owner_id + ", name=" + name + ", phone=" + phone + ", bank_account=" + bank_account + '}';
    }
}
