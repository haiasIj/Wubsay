package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WubsayProductDataManager {
    private final String csvFilePath;

    public WubsayProductDataManager() {
        this.csvFilePath = "src/main/resources/sandwichMenu.csv";
    }

    public ArrayList<SandwichProduct> loadSandwichProducts() {
        ArrayList<SandwichProduct> sandwichProducts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                SandwichProduct product = parseProductFromCSV(line);
                if (product != null) {
                    sandwichProducts.add(product);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file");

        } return sandwichProducts;
    }

    private SandwichProduct parseProductFromCSV(String line) {
        try {
            String[] values = line.split(",");

            String category = values[0].trim();
            String item = values[1].trim();
            String size = values[2].trim();
            String priceStr = values[3].trim();
            String notes = values.length > 4 ? values[4].trim() : "";

            String name = item;

            double price = 0.0;
            boolean included = false;

            if(priceStr.equalsIgnoreCase("Included")) {
                price = 0.0;
                included = true;
            } else {
                try {
                    price = Double.parseDouble(priceStr);
                    included = false;
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse price: " + priceStr);
                    price = 0.0;
                    included = false;
                }
            }

            return new SandwichProduct(category, item, name, size, price, included);
        } catch (Exception e) {
            System.out.println("Error parsing line");
            return null;
        }
    }
}

