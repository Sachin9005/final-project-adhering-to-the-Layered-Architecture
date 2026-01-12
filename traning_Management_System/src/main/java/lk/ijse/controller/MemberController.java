package lk.ijse.controller;

import lk.ijse.DTO.MemberDTO;
import lk.ijse.model.MemeberModel;

public class MemberController {
    private final MemeberModel memeberModel =  new MemeberModel();
    public void saveMember(MemberDTO memberDTO) {
        try {
            boolean isSaved = memeberModel.save(memberDTO);
            if (isSaved) {
                System.out.println("Member saved successfully");
            }else  {
                System.out.println("Member not saved ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateMember(MemberDTO memberDTO) {
        try {
            boolean isUpdate = memeberModel.update(memberDTO);
            if (isUpdate) {
                System.out.println("Member Update successfully");
            }else  {
                System.out.println("Member not update ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteMember(int id) {
        try {
            boolean isDelete = memeberModel.delete(id);
            if (isDelete) {
                System.out.println("Member Delete successfully");
            }else  {
                System.out.println("Member not Delete ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
