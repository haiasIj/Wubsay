package com.pluralsight;

import java.util.Scanner;

public class App {
    public static void main(String[] args) { //main screen
        Scanner scanner = new Scanner(System.in);

        int choice;

            System.out.print("Welcome to Wubsay! Can I take your order? (1 for Yes, 2 for No): ");

            choice = scanner.nextInt();


            if (choice == 1) {
                WubsayMenu.displayMenu();
            } else if (choice == 2) {
                System.out.println("Have a good day!");
                System.exit(0);
            }




    }
}
