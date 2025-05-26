package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;


public class WubsayMenu {

     WubsayProductDataManager wubsayDataManager = new WubsayProductDataManager();

   // public static ArrayList<SandwichProduct> sandwichProducts = wubsayDataManager.loadSandwichProducts();

    public static void displayMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("What would you like to add to your order? (Please choose a number 1-5): ");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("5. Cancel Order");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("---Add Sandwich---");
                    System.out.println("What bread would you like? (White, Wheat, Rye, Wrap): ");
                    String sandwich = scanner.nextLine();
                    System.out.println("Bread added: " + wubsayDataManager.getItem());
                    break;

                case "2":
                    System.out.println("---Add Drink---");
                    System.out.println("Enter size of drink: (Small, Medium, Large): ");
                    String drink = scanner.nextLine();
                    break;

                case "3":
                    System.out.println("---Add Chips---");
                    System.out.println("Chips added to order!");
                    System.out.println("PRESS ENTER TO CONTINUE");
                    String chips = scanner.nextLine();
                    break;

                case "4":
                    System.out.println("---Checking Out---");
                    break;

                case "5":
                    System.out.println("---Cancel Order---");
                    System.out.println("---Have a good day!");
                    System.exit(0);
                    break;

            }
        }

    }

}

