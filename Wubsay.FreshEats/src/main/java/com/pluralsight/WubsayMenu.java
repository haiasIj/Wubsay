package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;


public class WubsayMenu {


    public static void displayMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        WubsayProductDataManager wubsayDataManager = new WubsayProductDataManager();
        ArrayList<SandwichProduct> sandwichProducts = wubsayDataManager.loadSandwichProducts();

        while (running) {
            System.out.println("What would you like to add to your order? (Please choose a number 1-5): ");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("5. Cancel Order");
            System.out.println("Enter your choice ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("---Add Sandwich---");
                    int index = 1;
                    ArrayList<SandwichProduct> breadOptions = new ArrayList<>();

                    //Display bread options and store
                    for(SandwichProduct product : sandwichProducts) {
                        if(product.getCategory().equalsIgnoreCase("Sandwich Base") &&
                        product.getItem().startsWith("Bread")) {
                            System.out.printf("%d. %s (%s) – %s%n", index, product.getName(), product.getSize(),
                                    product.isIncluded() ? "Included" : "$" + product.getPrice());
                            breadOptions.add(product);
                        index++;
                        }
                    }
                    System.out.print("Please enter the name of the bread you want: ");
                    String breadChoice = scanner.nextLine().trim();

                    SandwichProduct selectedBread = null;
                    for(SandwichProduct bread : breadOptions) {
                        if(bread.getName().equalsIgnoreCase(breadChoice)) {
                            selectedBread = bread;
                            break;
                        }
                        else if(bread.getName().toLowerCase().contains(breadChoice.toLowerCase())) {
                            selectedBread = bread;
                            break;
                        }
                        else if (bread.getName().toLowerCase().endsWith(breadChoice.toLowerCase())) {
                            selectedBread = bread;
                            break;
                        }
                    }

                    if(selectedBread != null) {
                        System.out.println("You selected: " + selectedBread.getName());
                    } else {
                        System.out.println("Sorry, " + breadChoice + " is not an option ");
                    }
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
                    running = false;
                    break;

                case "5":
                    System.out.println("---Cancel Order---");
                    System.out.println("---Have a good day!");
                    running = false;
                    System.exit(0);
                    break;

            }
        }

    }

}

