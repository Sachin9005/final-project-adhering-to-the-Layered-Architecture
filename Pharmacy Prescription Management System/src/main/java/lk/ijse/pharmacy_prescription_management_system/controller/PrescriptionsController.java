package lk.ijse.pharmacy_prescription_management_system.controller;

import lk.ijse.pharmacy_prescription_management_system.DTO.PrescriptionsDTO;
import lk.ijse.pharmacy_prescription_management_system.MainView;
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
                System.out.println("\n1.back");
                System.out.println("2.exit");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1->MainView.view();
                    case 2->System.exit(0);
                }
            }else {
                System.out.println("Prescription not added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelPrescription(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Cancel Prescription");
        System.out.println("-------------------");
        System.out.print("Enter Prescription id:");
        int prescriptionID = sc.nextInt();
        try {
            boolean isDelete = prescriptionsModel.deletePrescription(prescriptionID,prescriptionsModel.getPrescription(prescriptionID).getMedicineId());
            if(isDelete){
                System.out.println("Prescription deleted successfully");
                System.out.println("\n1.back");
                System.out.println("2.exit");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1-> MainView.view();
                    case 2->System.exit(0);
                }
            }else {
                System.out.println("Prescription not deleted or Prescription Not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
