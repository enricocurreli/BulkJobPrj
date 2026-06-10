# Bulk DB

BulkDB is a small Java console application that demonstrates how to manage products in memory and persist them to a relational database using JDBC batch operations. The project is designed as a beginner-friendly backend exercise focused on core Java, the DAO pattern, and practical database interaction.

## Overview
The application provides a simple command-line menu that allows users to create, remove, display, and persist products. It serves as a portfolio project to demonstrate understanding of Java fundamentals, object-oriented design, and database persistence without relying on heavy frameworks.

## Features
- Add one or more products from the console.
- Remove a product by model (unique key).
- Display the current in-memory product list.
- Persist multiple products to a relational database using JDBC batch execution.
- Retrieve and display all products stored in the database.
- Update existing product prices using database upsert logic.
- Write and read files.

## Architecture
The project follows a simple layered structure:

- `BulkDB.java` handles the user interface and menu flow.
- `Product.java` defines the domain model.
- `ProductDAO.java` contains data access logic.
- `DatabaseConnector.java` is responsible for creating and managing the JDBC connection.

This structure reflects the DAO approach, where database access is cleanly separated from the rest of the application logic.

## Technologies
- Java
- JDBC
- Relational database (e.g., MySQL)
- Git and GitHub

## Database behavior
The `saveAll()` method uses a `PreparedStatement` with `addBatch()` and `executeBatch()` to efficiently persist multiple records. The SQL query leverages `ON DUPLICATE KEY UPDATE`, enabling automatic updates of product prices when a product with the same unique key already exists.

## SQL schema example
```sql
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL
);
```

Because the query relies on upsert behavior, the `model` column must have a `UNIQUE` constraint.

## Getting started

### Prerequisites
Ensure the following are available in your environment:
- JDK 17 or compatible version
- A relational database instance (e.g., MySQL)
- The appropriate JDBC driver
- A configured `DatabaseConnector` class with valid connection settings

### Setup
1. Create the database and the `products` table.
2. Add the JDBC driver to the project classpath.
3. Configure `DatabaseConnector` with the correct database URL, username, and password.
4. Compile and run the application.

## Usage
After starting the program, the console menu provides the following options:

1. Add product
2. Remove product
3. List products
4. Persist data to database
5. Retrieve data from database
6. Export database data to a `.txt` file 
7. Read text file
8. Quit

A typical usage flow is:
- add one or more products;
- review them in memory;
- persist them to the database in batch mode;
- retrieve all saved products to verify persistence;
- export stored products to a text file for logging purposes.
- read data from text file.

## Learning value
This project is useful for practicing:
- Java collections such as `ArrayList`
- object-oriented programming principles
- the DAO pattern
- JDBC connection handling (including best practices like try-with-resources)
- batch inserts and upsert logic
- file handling using classes like `BufferedWriter` and `BufferedReader`
- separation between UI, model, and persistence layers

## Possible improvements
To evolve this project into a stronger portfolio piece, the following enhancements would be valuable:
- input validation for numeric values;
- improved exception handling and user feedback;
- use of `BigDecimal` instead of `float` for price values;
- structured logging support;
- unit tests for DAO methods;
- migration to Maven or Gradle for dependency management;
- a scheduler or queue-based batch mechanism for timed database operations.

## Why this project matters
BulkDB demonstrates the ability to build a small but complete backend-oriented Java application from scratch. It highlights practical knowledge of JDBC, structured code organization, and database-driven design, which are essential foundations for building scalable Java backend systems.

## Author
Created by Enrico Curreli.