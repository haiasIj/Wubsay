package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WubsayProductDataManager {
    private final String csvFilePath;

    public WubsayProductDataManager() {
        this.csvFilePath = "src/main/resources/sandwhichMenu.csv";
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
            String category = values[0];
            String item = values[1];
            double price = Double.parseDouble(values[2]);
            boolean included = Boolean.parseBoolean(values[3]);

            return new SandwichProduct(category, item, price, included);
        } catch (Exception e) {
            System.out.println("Error parsing line");
            return null;
        }
    }
}

