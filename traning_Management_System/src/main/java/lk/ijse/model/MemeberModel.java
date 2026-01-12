package lk.ijse.model;

import lk.ijse.DTO.MemberDTO;
import lk.ijse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemeberModel {

    public boolean save(MemberDTO memberDTO) throws SQLException {
        return(CrudUtil.execute("INSERT INTO members(name,membership_type) VALUES(?,?)",memberDTO.getName(),memberDTO.getMembershipType()));
    }
    public boolean update(MemberDTO memberDTO) throws SQLException {
       String sql = "UPDATE members SET name=?,membership_type=? WHERE id=?";
       return (CrudUtil.execute(sql,memberDTO.getName(),memberDTO.getMembershipType(),memberDTO.getId()));
    }
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM members WHERE id=?";
        return (CrudUtil.execute(sql,id));
    }
    public MemberDTO get(int id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM members WHERE id=?",id);
        MemberDTO memberDTO = null;
        if (rs.next()) {
            memberDTO = new MemberDTO(
                    rs.getString("name"),
                    rs.getString("membership_type")
            );
        }
        return memberDTO;
    }
}
