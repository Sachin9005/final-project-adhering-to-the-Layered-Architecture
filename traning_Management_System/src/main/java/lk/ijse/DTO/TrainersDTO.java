package lk.ijse.DTO;

public class TrainersDTO {
    private int id;
    private String name;
    private int max_trainees;
    private int current_trainees_count;

    public TrainersDTO() {
    }

    public TrainersDTO(String name, int max_trainees, int current_trainees_count) {
        this.name = name;
        this.max_trainees = max_trainees;
        this.current_trainees_count = current_trainees_count;
    }

    public TrainersDTO(String name, int max_trainees) {
        this.name = name;
        this.max_trainees = max_trainees;
    }

    public TrainersDTO(int id, String name, int max_trainees) {
        this.id = id;
        this.name = name;
        this.max_trainees = max_trainees;
    }

    public TrainersDTO(int id, String name, int max_trainees, int current_trainees_count) {
        this.id = id;
        this.name = name;
        this.max_trainees = max_trainees;
        this.current_trainees_count = current_trainees_count;
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

    public int getMax_trainees() {
        return max_trainees;
    }

    public void setMax_trainees(int max_trainees) {
        this.max_trainees = max_trainees;
    }

    public int getCurrent_trainees_count() {
        return current_trainees_count;
    }

    public void setCurrent_trainees_count(int current_trainees_count) {
        this.current_trainees_count = current_trainees_count;
    }

    @Override
    public String toString() {
        return "TrainersDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", max_trainees=" + max_trainees +
                ", current_trainees_count=" + current_trainees_count +
                '}';
    }
}
