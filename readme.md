# Exercise Application

## Description

This project is a Spring Boot application that manages products in a store, with functionalities to search for products within a price range and sort products by price. Data is stored in a JSON file, without using a database.

## Features

1. **Retrieve Products by Price Range**: Returns products whose prices fall within a specified range.
2. **Retrieve Sorted Products by Price**: Returns products sorted by price in ascending or descending order.

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Maven**
- **JUnit 5**
- **MockMvc**
- **Jackson (for JSON manipulation)**

## Running Instructions

### Clone the Repository

```bash
git clone https://github.com/username/exercise-application.git
cd exercise-application
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn mvn spring-boot:run
```
**The application will be available at http://localhost:8080.**

### Endpoints

- GET /api/products/price-range: Search for products within a price range.

- - Request Parameters:

- - - initialPrice: The starting price of the range.

- - - finalPrice: The ending price of the range.

- - Response: A JSON array of products whose price is within the given range.

- GET /api/products/sorted: Retrieve products sorted by price.

- - Response: A JSON array of products sorted by price ascending.

### Testing

The project includes unit tests and integration tests. You can run them with the following command:
```bash
mvn test
```
## Technical Decisions
### JSON Data Handling
#### Instead of using a database, this application uses a static JSON file (products.json) to store product data. Jackson is used to deserialize this data into Java objects and serialize them back to JSON for API responses.

### Optional Handling in Java
#### The method retrieveProductsByPriceRange return Optional objects to gracefully handle cases where no products match the query. This avoids returning null and provides a more robust response handling in the API.

### Spring Boot and Maven
#### The project uses Spring Boot to simplify application configuration and development. Maven is used for dependency management and building the project.

