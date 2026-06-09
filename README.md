# Bulk DB

BulkDB is a small Java console application that demonstrates how to manage products in memory and persist them to a relational database using JDBC batch operations. The project is designed as a beginner-friendly backend exercise focused on core Java, the DAO pattern, and practical database interaction.

## Overview
The application provides a simple command-line menu that lets users create, remove, display, and store products. It is a good portfolio project for showing understanding of Java fundamentals, object-oriented design, and database persistence without relying on heavy frameworks.

## Features
- Add one or more products from the console.
- Remove a product by model.
- Display the current in-memory product list.
- Persist multiple products to the database using JDBC batch execution.
- Fetch and print all products stored in the database.
- Update existing product prices through database upsert logic.

## Architecture
The project follows a simple layered structure:

- `BulkDB.java` handles the user interface and menu flow.
- `Product.java` defines the domain model.
- `ProductDAO.java` contains data access logic.
- `DatabaseConnector.java` is responsible for creating the JDBC connection.

This structure reflects the DAO approach, where database access is separated from the rest of the application logic.

## Technologies
- Java
- JDBC
- Relational database, such as MySQL
- Git and GitHub

## Database behavior
The `saveAll()` method uses a `PreparedStatement` with `addBatch()` and `executeBatch()` to insert multiple records efficiently. The SQL query also uses `ON DUPLICATE KEY UPDATE`, which allows the application to update the product price when a product with the same unique key already exists.

## SQL schema example
```sql
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL
);
```

Because the query relies on upsert behavior, the `model` column should have a `UNIQUE` constraint.

## Getting started
### Prerequisites
Make sure the following are available in your environment:
- JDK 17 or a compatible version
- A relational database instance, for example MySQL
- The correct JDBC driver
- A configured `DatabaseConnector` class with valid connection settings

### Setup
1. Create the database and the `products` table.
2. Add the JDBC driver to the project classpath.
3. Configure `DatabaseConnector` with the correct database URL, username, and password.
4. Compile and run the application.

## Usage
After starting the program, the console menu offers the following options:

1. Add product
2. Remove product
3. List products
4. Send data to database
5. Fetch data from database
6. Quit

A typical usage flow is:
- add one or more products;
- review them in memory;
- send them to the database in batch mode;
- fetch all saved products to verify persistence.

## Learning value
This project is useful for practicing:
- Java collections such as `ArrayList`
- object-oriented programming
- the DAO pattern
- JDBC connection handling
- batch inserts and upsert logic
- separation between UI, model, and persistence layers

## Possible improvements
To evolve this project into a stronger portfolio piece, the following enhancements would be valuable:
- input validation for numeric values;
- improved exception handling and user feedback;
- use of `BigDecimal` instead of `float` for price values;
- logging support;
- unit tests for DAO methods;
- migration to Maven or Gradle for dependency management;
- a scheduler or queue-based batch mechanism for timed database flushes.

## Why this project matters
BulkDB shows the ability to build a small but complete backend-oriented Java application from scratch. It highlights practical knowledge of JDBC, structured code organization, and database-oriented thinking, which are all relevant foundations for larger Java backend projects.

## Author
Created by Enrico Curreli.