package lk.ijse.booklib.controller;

import lk.ijse.booklib.DTO.StudentsDTO;
import lk.ijse.booklib.model.StudentModel;

public class StudentController {
    private StudentModel studentModel =  new StudentModel();

    public  void saveStudent(String name){
        try {
            boolean isSaved = studentModel.save(new StudentsDTO(name));
            if(isSaved){
                System.out.println("Student Saved Successfully");
            }else {
                System.out.println("Student Save Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
