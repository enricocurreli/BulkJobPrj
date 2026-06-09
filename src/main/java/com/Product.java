package com;

public class Product
{
    private String brand;
    private String model;
    private float price;

    public Product(String brand, String model, float price)
    {
        this.price = price;
        this.brand = brand;
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return "Brand: " + brand + "\n" + "Model: " + model + "\n" + "Price: " + price + "\n";
    }
}