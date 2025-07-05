# UserManagement-JPA

A Spring Boot REST API for user management, using JPA (Java Persistence API) and PostgreSQL.  
This project demonstrates CRUD operations, validation, pagination, and integration testing for a user entity.

---

## Features

- **Create, Read, Update, Delete (CRUD) Users**
- **Bulk user creation**
- **Pagination support**
- **Validation with Jakarta Validation**
- **Integration tests with MockMvc and JUnit 5**
- **PostgreSQL database integration**
- **Spring Boot DevTools for hot reload**

---

## Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring Validation
- PostgreSQL
- Maven
- JUnit 5, MockMvc (for testing)
- Jackson (for JSON serialization)

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.9+
- PostgreSQL running locally (or update `application.properties` for your DB)

### Clone the Repository

```bash
git clone https://github.com/VamshiTejaKumbham/UserManagement-JPA.git
cd UserManagement-JPA
```

### Database Setup

Create a PostgreSQL database named `userdb` and update credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=
```

### Build and Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The API will be available at [http://localhost:8082](http://localhost:8082). or [http://localhost:8082](http://localhost:8082)

---

## API Endpoints

| Method | Endpoint             | Description                    |
|--------|----------------------|--------------------------------|
| POST   | `/api/users`         | Create one or more users       |
| GET    | `/api/users`         | Get all users                  |
| GET    | `/api/users/page`    | Get users with pagination      |
| GET    | `/api/users/{id}`    | Get user by ID                 |
| PUT    | `/api/users/{id}`    | Update user by ID              |
| DELETE | `/api/users/{id}`    | Delete user by ID              |

### Example User JSON

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## Running Tests

```bash
./mvnw test
```

Tests are located in `src/test/java/com/user/usermanagementapi/`.

---

## Project Structure

```
UserManagement-JPA/
├── src/
│   ├── main/
│   │   ├── java/com/user/usermanagementapi/
│   │   │   ├── controller/        # REST controllers
│   │   │   ├── model/             # JPA entities
│   │   │   ├── repository/        # Spring Data JPA repositories
│   │   │   └── exception/         # (For custom exceptions)
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/user/usermanagementapi/
│           └── UserControllerTest.java
├── pom.xml
└── README.md
```

---

## Screenshots

![Successful implementation of Pagination](https://github.com/user-attachments/assets/47d10b5e-2464-4170-9aa7-bc1125a87f33)

Successful HTTP Requests
![](https://github.com/user-attachments/assets/94a51873-8d9e-4bdd-a33c-f60632f5ccb4)

![](https://github.com/user-attachments/assets/2b857ff7-ef0c-4692-95c3-959a1fb3d2c1)


---

## License

This project is licensed under the MIT License.

---

## Author

-  K Vamshi Teja
