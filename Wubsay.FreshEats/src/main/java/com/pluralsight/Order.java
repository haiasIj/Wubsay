package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private ArrayList<SandwichProduct> orderItems;
    private double totalPrice;

    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(SandwichProduct item) {
        orderItems.add(item);
        if (!item.isIncluded()) {
            totalPrice += item.getPrice();
        }
    }

    public ArrayList<SandwichProduct> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isEmpty() {
        return orderItems.isEmpty();
    }

    public void displayOrder() {
        if (isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        System.out.println("\n--- YOUR ORDER ---");
        for (SandwichProduct item : orderItems) {
            String priceDisplay = item.isIncluded() ? "Included" : String.format("$%.2f", item.getPrice());
            System.out.printf("• %s (%s) - %s%n", item.getName(), item.getSize(), priceDisplay);
        }
        System.out.printf("Total: $%.2f%n", totalPrice);
    }

    public void writeReceiptToFile() {
        if (isEmpty()) {
            System.out.println("Cannot create receipt for empty order.");
            return;
        }

        String fileName = "Receipts.csv";
        boolean fileExists = new java.io.File(fileName).exists();

        FileWriter writer = null;

        try  {

            writer = new FileWriter(fileName, true);

            if(!fileExists) {
                writer.write("Date,Time,Items,Total\n");
            }

            // Format: Date/Time | Items | Total
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


            StringBuilder receipt = new StringBuilder();
            for (int i = 0; i < orderItems.size(); i++) {
                SandwichProduct item = orderItems.get(i);
                receipt.append(item.getName()).append("(").append(item.getSize()).append(")");
                if (i < orderItems.size() - 1) {
                    receipt.append("; ");
                }
            }

            String line = String.format("%s,%s,\"%s\",%.2f%n",
                    now.format(dateFormatter),
                    now.format(timeFormatter),
                    receipt.toString(),
                    totalPrice);

            writer.write(line);
            writer.flush(); //FORCE WRITE TO FILE

        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        } finally { //CLOSES RECOURSES IF NOT CLOSED THEMSELVES
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing file" + e.getMessage());
                }
            }
        }
    }
    public void clearOrder() {
        orderItems.clear();
        totalPrice = 0.0;
    }
}