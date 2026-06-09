package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BulkDB {
    public static void main(String[] args) {

        boolean start = true;
        ArrayList<Product> productsList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (start) {
            System.out.println(" \nWelcome - Menu\n ");
            System.out.println(" 1. Add product");
            System.out.println(" 2. Remove product");
            System.out.println(" 3. List products");
            System.out.println(" 4. Send data to database");
            System.out.println(" 5. Fetch data from database");
            System.out.println(" 6. Quit");
            String opt = sc.nextLine();

            switch (opt) {
                case "1":
                    String brand;
                    String model;
                    float price;
                    do {

                        System.out.print("Insert brand: ");
                        brand = sc.nextLine();
                        System.out.print("Insert model: ");
                        model = sc.nextLine();
                        System.out.print("Insert price: ");
                        price = Float.parseFloat(sc.nextLine());

                        if (!brand.equals("0") && !model.equals("0")) {
                            productsList.add(new Product(brand, model, price));
                        }

                    } while (!brand.equals("0") && !model.equals("0") && price != 0 );
                    break;

                case "2":
                    boolean completed = false;

                    while(!completed){

                        System.out.println("Insert model: ");
                        String rmModel = sc.nextLine();

                        boolean removed = productsList.removeIf(p -> p.getModel().equals(rmModel));
                        if (removed) {
                            System.out.println("Product removed successfully.");
                            completed = true;
                        } else {
                            System.out.println("No product found with model: " + rmModel + ". Try with another one.\n");
                        }
                    }

                    break;

                case "3":
                    if (productsList.isEmpty()){
                        System.out.println("Product list is empty");
                    } else {

                        for (Product product : productsList) {
                            System.out.println("\n"+product);
                        }
                    }
                    break;
                case "4":
                    if (!productsList.isEmpty())
                    {
                        new ProductDAO().saveAll(productsList);
                        productsList.clear();
                    } else {
                        System.out.println("Product list is empty");
                    }
                    break;
                case "5":
                    List<Product> res = new ProductDAO().getAll();
                    for (Product resProd: res)
                    {
                        System.out.println(resProd);
                    }
                    break;
                case "6":
                    start = false;
                    System.out.println("Bye");
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }

        }

        sc.close();
    }
}