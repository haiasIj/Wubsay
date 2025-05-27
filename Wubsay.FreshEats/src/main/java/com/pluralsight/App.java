package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) { //main screen
        Scanner scanner = new Scanner(System.in);
        WubsayProductDataManager wubsayDataManager = new WubsayProductDataManager();
        wubsayDataManager.loadSandwichProducts();
        WubsayMenu menu = new WubsayMenu();

        int choice;

            System.out.print("Welcome to Wubsay! Can I take your order? (1 for Yes, 2 for No): ");
            choice = scanner.nextInt();
            scanner.nextLine();


            if (choice == 1) {
                menu.displayMenu(); //remove the static call
            } else if (choice == 2) {
                System.out.println("Have a good day!");
                System.exit(0);
            }

            scanner.close();
    }
}
