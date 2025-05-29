package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class WubsayMenu {

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Order currentOrder = new Order();

        WubsayProductDataManager wubsayDataManager = new WubsayProductDataManager();
        ArrayList<SandwichProduct> sandwichProducts = wubsayDataManager.loadSandwichProducts();

        while (running) {
            System.out.println("\nWhat would you like to add to your order? (Please choose a number 1-5): ");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("5. Cancel Order");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("---Add Sandwich---");
                    System.out.println("Available sandwich sizes:");
                    System.out.println("• 4\" - $5.50");
                    System.out.println("• 8\" - $7.00");
                    System.out.println("• 12\" - $8.50");

                    System.out.print("Please enter sandwich size (4, 8, or 12): ");
                    String sizeChoice = scanner.nextLine().trim();
                    String selectedSize = null;

                    // Validate size choice
                    if (sizeChoice.equals("4")) {
                        selectedSize = "4\"";
                    } else if (sizeChoice.equals("8")) {
                        selectedSize = "8\"";
                    } else if (sizeChoice.equals("12")) {
                        selectedSize = "12\"";
                    } else {
                        System.out.println("Invalid size. Please select 4, 8, or 12.");
                        break;
                    }

                    System.out.println("You selected: " + selectedSize + " sandwich");

                    // Find and add sandwich base
                    SandwichProduct sandwichBase = null;
                    for (SandwichProduct product : sandwichProducts) {
                        if (product.getCategory().equalsIgnoreCase("Sandwich Base") &&
                                product.getItem().equals("Sandwich (Base)")) {
                            if (product.getSize().equals(selectedSize)) {
                                sandwichBase = product;
                                break;
                            }
                        }
                    }

                    if (sandwichBase != null) {
                        currentOrder.addItem(sandwichBase);
                        System.out.printf("Added %s sandwich base - $%.2f%n", selectedSize, sandwichBase.getPrice());
                    } else {
                        System.out.println("ERROR: Could not find sandwich base for size " + selectedSize);
                        break;
                    }

                    // Select bread type
                    ArrayList<SandwichProduct> breadOptions = new ArrayList<>();
                    System.out.println("\nAvailable bread options:");

                    for (SandwichProduct product : sandwichProducts) {
                        if (product.getCategory().equalsIgnoreCase("Sandwich Base") &&
                                product.getItem().startsWith("Bread")) {
                            String breadType = product.getName().replace("Bread - ", "");
                            System.out.printf("• %s - Included%n", breadType);
                            breadOptions.add(product);
                        }
                    }

                    System.out.print("Please enter the bread type: ");
                    String breadChoice = scanner.nextLine().trim();

                    SandwichProduct selectedBread = null;
                    for (SandwichProduct bread : breadOptions) {
                        if (bread.getName().toLowerCase().contains(breadChoice.toLowerCase())) {
                            selectedBread = bread;
                            break;
                        }
                    }

                    if (selectedBread != null) {
                        currentOrder.addItem(selectedBread);
                        System.out.println("Added " + selectedBread.getName() + " to your sandwich!");

                        // Sandwich toasted??
                        System.out.println("Would you like your sandwich toasted? (y for yes, n for no)");
                        String toastedChoice = scanner.nextLine().toLowerCase().trim();

                        if (toastedChoice.equals("yes") || toastedChoice.equals("y")) { //Interesting piece of code (i didn't know || meant or)
                            System.out.println("Your sandwich will in-fact be toasted");

                            SandwichProduct toasted = new SandwichProduct("Service", "Toasted", "Toasted", "Free", 0.0, true);
                            currentOrder.addItem(toasted);
                        } else {
                            System.out.println("lame... sandwich will not be toasted.");
                        }

                        // Add toppings
                        boolean addToppings = true;
                        while (addToppings) {
                            System.out.println("\n---Toppings---");
                            System.out.println("Would you like to add any toppings to your sandwich? (Type Y or N)");
                            String toppingChoice = scanner.nextLine().trim().toLowerCase();

                            if (toppingChoice.equals("yes") || toppingChoice.equals("y")) {
                                System.out.println("Topping categories:");
                                System.out.println("1. Meat");
                                System.out.println("2. Cheese");
                                System.out.println("3. Veggies");
                                System.out.println("4. Sauce");
                                System.out.print("Please Select 1-4: ");

                                String categoryChoice = scanner.nextLine();
                                String toppingCategory = null;

                                switch (categoryChoice) {
                                    case "1":
                                        toppingCategory = "Topping - Meat";
                                        break;

                                    case "2":
                                        toppingCategory = "Topping - Cheese";
                                        break;

                                    case "3":
                                        toppingCategory = "Topping - Regular";
                                        break;

                                    case "4":
                                        toppingCategory = "Topping - Sauce";
                                        break;

                                    default:
                                        System.out.println("That's not an option.");
                                        continue;
                                }

                                // Show toppings in selected category for sandwich size
                                System.out.println("\nAvailable options:");
                                boolean foundAny = false;

                                for (SandwichProduct product : sandwichProducts) {
                                    // accept "ALL SIZES" for veggies and saucec (They're free)
                                    boolean sizeMatches = product.getSize().equals(selectedSize) ||
                                            product.getSize().equals("All Sizes");

                                    if (product.getCategory().equals(toppingCategory) && sizeMatches) {
                                        System.out.printf("- %s ($%.2f)%n", product.getItem(), product.getPrice());
                                        foundAny = true;
                                    }
                                }

                                if (!foundAny) {
                                    System.out.println("No " + toppingCategory.replace("Topping - ", "").toLowerCase() + " available.");
                                    continue;
                                }

                                System.out.print("Type the name of what you want: ");
                                String itemName = scanner.nextLine().trim();

                                // Find and add the topping
                                boolean found = false;
                                for (SandwichProduct product : sandwichProducts) {
                                    // For veggies and sauces, accept "All Sizes" OR the specific size
                                    boolean sizeMatches = product.getSize().equals(selectedSize) ||
                                            product.getSize().equals("All Sizes");

                                    if (product.getCategory().equals(toppingCategory) && sizeMatches &&
                                            product.getItem().toLowerCase().contains(itemName.toLowerCase())) {

                                        currentOrder.addItem(product);
                                        System.out.printf("Added %s - $%.2f%n", product.getItem(), product.getPrice());
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    System.out.println("Could not find " + itemName + " ");
                                }

                            } else {
                                addToppings = false;
                                System.out.println("Sandwich complete!");
                            }
                        }

                    } else {
                        System.out.println("Sorry, " + breadChoice + " is not available.");
                    }
                    break;

                case "2":
                    System.out.println("---Add Drink---");

                    // Filter and collect drink options
                    ArrayList<SandwichProduct> drinkOptions = sandwichProducts.stream()
                            .filter(product -> product.getCategory().equalsIgnoreCase("Other Product") &&
                                    product.getItem().startsWith("Drink"))
                            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

                    System.out.println("Available drink sizes:");
                    drinkOptions.stream()
                            .forEach(product -> System.out.printf("• %s - $%.2f%n",
                                    product.getSize(), product.getPrice()));

                    if (drinkOptions.isEmpty()) {
                        System.out.println("No drinks found.");
                        break;
                    }

                    System.out.print("What drink size would you like: ");
                    String drinkChoice = scanner.nextLine().trim();

                    SandwichProduct selectedDrink = drinkOptions.stream()
                            .filter(drink -> drink.getSize().equalsIgnoreCase(drinkChoice))
                            .findFirst()
                            .orElse(null);

                    if (selectedDrink != null) {
                        currentOrder.addItem(selectedDrink);
                        System.out.printf("Drink (%s) - $%.2f added to order!%n",
                                selectedDrink.getSize(), selectedDrink.getPrice());
                    } else {
                        System.out.println("Sorry, " + drinkChoice + " is not available.");
                    }
                    break;

                case "3":
                    System.out.println("---Add Chips---");

                    // Look specifically for chips
                    SandwichProduct chips = sandwichProducts.stream()
                            .filter(product -> product.getCategory().equalsIgnoreCase("Side") &&
                                    product.getItem().toLowerCase().contains("chip"))
                            .findFirst()
                            .orElse(null);

                    if (chips != null) {
                        currentOrder.addItem(chips);
                        System.out.printf("Chips - $%.2f added to order!%n", chips.getPrice());
                    } else {
                        SandwichProduct defaultChips = new SandwichProduct("Side", "Chips", "Chips", "Regular", 1.50, false);
                        currentOrder.addItem(defaultChips);
                        System.out.printf("Chips - $%.2f added to order!%n", defaultChips.getPrice());
                    }
                    break;

                case "4":
                    System.out.println("---Checking Out---");
                    if (currentOrder.isEmpty()) {
                        System.out.println("Your order is empty. Add some items first!");
                    } else {
                        currentOrder.displayOrder();
                        currentOrder.writeReceiptToFile();
                        System.out.println("Thank you for your order!");
                        running = false;
                    }
                    break;

                case "5":
                    System.out.println("---Cancel Order---");
                    System.out.println("Have a good day!");
                    running = false;
                    break;

                default:
                    System.out.println("Not an option. Please select 1-5.");
                    break;
            }
        }
    }
}