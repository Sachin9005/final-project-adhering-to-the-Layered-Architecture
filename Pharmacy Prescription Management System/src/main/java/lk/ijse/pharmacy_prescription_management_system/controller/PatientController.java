package lk.ijse.pharmacy_prescription_management_system.controller;

import lk.ijse.pharmacy_prescription_management_system.DTO.PatientsDTO;
import lk.ijse.pharmacy_prescription_management_system.model.PatientsModel;

import java.util.Scanner;

public class PatientController {

    private final PatientsModel patientsModel =  new PatientsModel();

    public void addPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Patient");
        System.out.println("------------------------");
        System.out.print("Enter Patient Name:");
        String patientName = sc.nextLine();
        System.out.print("\n Enter Contact:");
        String patientContact = sc.nextLine();
        try {
            if (patientName.isEmpty() || patientContact.isEmpty()) {
                System.out.println("Patient Name or Contact is Empty");
            }else  {
                PatientsDTO patientsDTO = new PatientsDTO(patientName,patientContact);
                boolean isSaved = patientsModel.save(patientsDTO);
                if (isSaved) {
                    System.out.println("Patient added successfully");
                }else {
                    System.out.println("Something went wrong");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
