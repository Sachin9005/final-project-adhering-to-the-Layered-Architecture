package lk.ijse.DTO;

public class MemberDTO {
    private int id;
    private String name;
    private String membershipType;

    public MemberDTO() {
    }

    public MemberDTO(String name, String membershipType) {
        this.name = name;
        this.membershipType = membershipType;
    }

    public MemberDTO(int id, String name, String membershipType) {
        this.id = id;
        this.name = name;
        this.membershipType = membershipType;
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

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}
