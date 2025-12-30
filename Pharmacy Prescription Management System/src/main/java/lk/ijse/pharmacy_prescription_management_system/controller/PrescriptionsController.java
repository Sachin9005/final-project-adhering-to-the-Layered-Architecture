package lk.ijse.pharmacy_prescription_management_system.controller;

import lk.ijse.pharmacy_prescription_management_system.DTO.PrescriptionsDTO;
import lk.ijse.pharmacy_prescription_management_system.model.PrescriptionsModel;


import java.util.Scanner;

public class PrescriptionsController {
    private final PrescriptionsModel prescriptionsModel =  new PrescriptionsModel();

    public void addPrescription(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Prescription");
        System.out.println("-------------------");

        System.out.print("Enter Patient id:");
        int patientId = sc.nextInt();
        System.out.print("\nEnter Medicine id:");
        int medicineId = sc.nextInt();

        try {
            boolean isAdd = prescriptionsModel.savePrescription(new PrescriptionsDTO(patientId,medicineId));
            if(isAdd){
                System.out.println("Prescription added successfully");
            }else {
                System.out.println("Prescription not added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
