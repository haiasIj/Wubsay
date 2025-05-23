package com.pluralsight;

public class SandwichProduct {
    private String category;
    private String item;
    private double price;
    private boolean included; //no up-charge if included

    public SandwichProduct(String category, String item, double price, boolean included) {
        this.category = category;
        this.item = item;
        this.price = price;
        this.included = included;
    }

    //getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    @Override
    public String toString() {
        return "SandwichProduct{" +
                "category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", included=" + included +
                '}';
    }
}
