package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void saveAll(List<Product> products) {
        String sql = """
                INSERT INTO products (brand, model, price)
                VALUES (?, ?, ?)
                ON DUPLICATE KEY UPDATE
                price = VALUES(price)
                """;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Product product : products) {
                ps.setString(1, product.getBrand());
                ps.setString(2, product.getModel());
                ps.setFloat(3, product.getPrice());

                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("Products inserted on the DB: " + products.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, brand, model, price FROM products";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getFloat("price")
                );
                products.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}