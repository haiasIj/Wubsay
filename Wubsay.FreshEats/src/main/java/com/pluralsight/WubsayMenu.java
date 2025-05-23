package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class WubsayMenu {


    public static void displayMenu() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Welcome to Wubsay!-----");
        System.out.println("\n---\n");


        while (running) {
            System.out.println("What type of bread would you like?");
            System.out.println("1. White");
            System.out.println("2. Wheat");
            System.out.println("3. Rye");
            System.out.println("4. Wrap");
            System.out.println("5. Exit Wubsay... (You don't want to do this.)");
            System.out.print("\nPlease select and option (1-5): ");

            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    System.out.println("What size would you like your white bread?");
                    System.out.println("1. 4 inch");
                    System.out.println("2. 8 inch");
                    System.out.println("3. 12 inch");
                    System.out.println("4. Back to Bread Choice");
                    System.out.print("\nPlease Select a Size (1-3): ");
                    break;

                case "2":
                    System.out.println("What size would you like your wheat bread?");
                    System.out.println("1. 4 inch");
                    System.out.println("2. 8 inch");
                    System.out.println("3. 12 inch");
                    System.out.println("4. Back to Bread Choice");
                    System.out.print("\nPlease Select a Size (1-3): ");
                    break;

                case "3":

                    System.out.println("What size would you like your rye bread?");
                    System.out.println("1. 4 inch");
                    System.out.println("2. 8 inch");
                    System.out.println("3. 12 inch");
                    System.out.println("4. Back to Bread Choice");
                    System.out.print("\nPlease Select a Size (1-3): ");
                    break;

                case "4":
                    System.out.println("What size would you like your wrap?");
                    System.out.println("1. 4 inch");
                    System.out.println("2. 8 inch");
                    System.out.println("3. 12 inch");
                    System.out.println("4. Back to Bread Choice");
                    System.out.print("\nPlease Select a Size (1-3): ");
                    break;

                case "5":
                    System.exit(0);
            }


            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                System.out.println("\n");
            }


        }

    }

}

