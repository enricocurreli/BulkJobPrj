    package com;

    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.StandardOpenOption;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
    import java.util.List;

    public class ProductDAO {
        private static final Path FILE_PATH = Path.of(System.getenv("FILE_PATH"));
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

        public void exportAllProducts() {
            List<Product> products = getAll();
            String timestamp = LocalDateTime.now().format(FORMATTER);
            System.out.println("Writing file....");
                try (BufferedWriter writer = Files.newBufferedWriter(
                        FILE_PATH,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND
                )) {
                    writer.write("===== EXPORT " + timestamp + " =====\n");
                    writer.newLine();

                    for (Product product : products) {
                        writer.write(product.toString());
                        writer.newLine();
                    }

                    writer.write("===== END EXPORT =====");
                    writer.newLine();
                    writer.newLine();
                System.out.println("EXPORT COMPLETE");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }