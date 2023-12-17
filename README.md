# Smart City Application

This project is a Smart City Application, aiming to manage users, wallets, and transactions for various city services.

## Overview

The application manages user data, including their associated wallets and transaction records. It offers functionalities to create users, link wallets to users, and handle transactions for city services.

## Tech Stack

- Java
- Spring Boot
- Hibernate
- MySQL
- Lombok

## Setup Instructions

### Prerequisites

- Java 8 or higher
- MySQL

### Steps to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your_username/smart-city-app.git
   ```

2. Navigate to the project directory:

   ```bash
   cd smartcity
   ```

3. Configure the database in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/SmartCity
   spring.datasource.username=root
   spring.datasource.password=root_password
   ```

4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

## Usage

### Creating Users

To create a new user along with a wallet and transactions, use the `/users/create` endpoint.

### Endpoints

- `POST /users/create`: Creates a new user with associated wallet and transactions.

  Example Request Body:

  ```json
  {
    "username": "username",
    "password": "password123",
    "transactions": [
      {
        "amount": 100,
        "serviceType": "TRANSIT"
        // Add more transaction details here...
      },
      // Add more transactions if needed...
    ]
  }
  ```

## Folder Structure

```
smart-city-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── application/
│   │   │           └── smartcity/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── repository/
│   │   │               └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── application/
│                   └── smartcity/
│                       └── (Test files)
├── mvnw
├── mvnw.cmd
├── README.md
└── (Other project files)
```
