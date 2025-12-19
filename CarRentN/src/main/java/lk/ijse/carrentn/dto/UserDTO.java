package lk.ijse.carrentn.dto;

public class UserDTO {
    private int userID ;
    private String username;
    private String password;
    private String role;
    private String phone_number;
    private String name;

    public UserDTO(String username, String password, String role, String phone_number, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone_number = phone_number;
        this.name = name;
    }
    
    

    public UserDTO(int userID, String username, String password, String role, String phone_number, String name) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone_number = phone_number;
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getName() {
        return name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", role=" + role + ", phone_number=" + phone_number + ", name=" + name + '}';
    } 
}
