package com.pluralsight;

import java.util.ArrayList;

public class SearchProducts {
    public static ArrayList<SandwichProduct> getProductCategory(String category, ArrayList<SandwichProduct> products) {
        ArrayList<SandwichProduct> matches = new ArrayList<>();

        for (SandwichProduct sandwichProduct : products) {
            if (category.equals(sandwichProduct.getCategory())) {
                matches.add(sandwichProduct);
            }
        }
        return matches;
    }
}


