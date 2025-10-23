🛒 Electronics Hub

Electronics Hub is a Spring Boot–based e-commerce backend application that allows users to manage products, categories, carts, and orders. It provides a complete shopping workflow — from browsing and adding items to the cart to placing orders — with robust authentication and authorization using Spring Security with JWT and refresh tokens.

🚀 Features
🧩 Core Functionality

Product Management – Create, update, delete, and view products.

Category Management – Organize products under multiple categories.

Cart Management – Add, remove, and clear items from the shopping cart.

Order Management – Place and track orders with proper relationships between users, carts, and products.

⚙️ Backend Technologies

Spring Boot – Framework for building the RESTful backend.

Spring Data JPA – ORM for database persistence and repository layer.

Spring Security (JWT) – Secure authentication and authorization.

Lombok – Simplifies code with builder patterns and automatic getters/setters.

Swagger / OpenAPI – Interactive API documentation.

REST Template – For integration with external APIs.

DTOs (Data Transfer Objects) – Ensures clean separation between API and entity models.

Global Exception Handling – Centralized management of errors and exceptions.

Pagination & Sorting – Efficient data fetching with pageable responses.

🔐 Authentication & Authorization

Uses JWT Tokens for secure access to protected resources.

Implements Refresh Tokens for maintaining long-lived sessions.

🧰 Prerequisites

Java 17+

Maven 3.8+

Spring Boot 3.x

MySQL / PostgreSQL (configurable in application.properties)

Postman / Swagger UI for API testing

