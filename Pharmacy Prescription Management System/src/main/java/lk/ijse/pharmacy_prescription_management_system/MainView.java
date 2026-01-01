package lk.ijse.pharmacy_prescription_management_system;

import lk.ijse.pharmacy_prescription_management_system.controller.MedicinesController;
import lk.ijse.pharmacy_prescription_management_system.controller.PatientController;
import lk.ijse.pharmacy_prescription_management_system.controller.PrescriptionsController;

import java.util.Scanner;


public class MainView {
    public static void view() {
        Scanner sc = new Scanner(System.in);
        PatientController patientController = new PatientController();
        MedicinesController medicinesController = new MedicinesController();
        PrescriptionsController prescriptionsController = new PrescriptionsController();

        System.out.println("Pharmacy Prescription Management System");
        System.out.println("-------------------------------------------");
        System.out.println("1.Patient Management");
        System.out.println("2.Medicines Management");
        System.out.println("3.Prescription");
        System.out.println("4.get Inventory Reports");
        System.out.println("5.Cancel Prescription");
        System.out.println("6.Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 ->patientController.addPatient();
            case 2 -> medicinesController.addMedicine();
            case 3 -> prescriptionsController.addPrescription();
            case 4 -> medicinesController.getInventoryReport();
            case 5 -> prescriptionsController.cancelPrescription();
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }
    public static void main(String[] args) {
        view();
    }
}
