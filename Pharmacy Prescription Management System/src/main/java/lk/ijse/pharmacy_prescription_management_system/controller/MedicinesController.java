package lk.ijse.pharmacy_prescription_management_system.controller;

import lk.ijse.pharmacy_prescription_management_system.DTO.MedicinesDTO;
import lk.ijse.pharmacy_prescription_management_system.model.MedicinesModel;

import java.util.Scanner;

public class MedicinesController {
    private final MedicinesModel medicinesModel = new MedicinesModel();

    public void addMedicine(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" Add Medicine");
        try {
            System.out.println("Enter Medicine Name:");
            String medicineName = sc.nextLine();
            System.out.println("Enter Medicine Unit Price:");
            double medicinePrice = sc.nextDouble();
            System.out.println("Enter Medicine Quantity:");
            int medicineQuantity = sc.nextInt();

            if (medicineName.isEmpty() || medicinePrice <= 0 || medicineQuantity <= 0 ){
                System.out.println("Invalid Medicine Name/ Medicine Quantity Or Medicine Price ");
            }else {
                boolean isSaved = medicinesModel.saveMedicine(new MedicinesDTO(medicineName,medicinePrice,medicineQuantity));
                if (isSaved){
                    System.out.println("Medicine Saved");
                }else  {
                    System.out.println("Medicine Not Saved");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}