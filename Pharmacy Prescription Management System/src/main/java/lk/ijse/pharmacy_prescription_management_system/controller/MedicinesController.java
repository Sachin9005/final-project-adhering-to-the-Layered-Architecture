package lk.ijse.pharmacy_prescription_management_system.controller;

import lk.ijse.pharmacy_prescription_management_system.DTO.MedicinesDTO;
import lk.ijse.pharmacy_prescription_management_system.MainView;
import lk.ijse.pharmacy_prescription_management_system.model.MedicinesModel;

import java.util.List;
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
                    System.out.println("1.back");
                    System.out.println("2.exit");
                    System.out.print("Enter Choice: ");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1->MainView.view();
                        case 2->System.exit(0);
                    }
                }else  {
                    System.out.println("Medicine Not Saved");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getInventoryReport(){
        Scanner sc = new Scanner(System.in);
        try {

            List<MedicinesDTO> list = medicinesModel.getAllMedicines();

            System.out.println("\n===== INVENTORY REPORT =====");
            System.out.printf("%-5s %-20s %-10s %-10s%n",
                    "ID", "Name", "Price", "Stock");
            System.out.println("----------------------------------------");

            for (MedicinesDTO m : list) {
                System.out.printf("%-5d %-20s %-10.2f %-10d%n",
                        m.getId(),
                        m.getName(),
                        m.getUnitPrice(),
                        m.getQuantity()
                );
            }
            System.out.println("----------------------------------------");
            System.out.println("1.back");
            System.out.println("2.exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1->MainView.view();
                case 2->System.exit(0);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}