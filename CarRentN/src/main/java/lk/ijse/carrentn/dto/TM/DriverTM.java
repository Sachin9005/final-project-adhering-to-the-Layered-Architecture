package lk.ijse.carrentn.dto.TM;

public class DriverTM {
    private int id;
    private String name;
    private String phoneNO;
    private double dailyRate;


    public DriverTM(int id, String name, String phoneNO, double dailyRate) {
        this.id = id;
        this.name = name;
        this.phoneNO = phoneNO;
        this.dailyRate = dailyRate;
    }


    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public String toString() {
        return "DriverTM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNO='" + phoneNO + '\'' +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
