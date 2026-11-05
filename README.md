
# OS Management — Service Order REST API

A RESTful API developed with **Java and Spring Boot**, following a layered architecture to manage clients, technicians, and service orders.

The project focuses on backend development, object-oriented programming, relational database integration, and maintainable code organization.

## Features

The API supports management of:

- **Clients:** Customer information and contact details.
- **Technicians:** Technician profiles and specialties.
- **Service Orders:** Service requests, priorities, assignments, and status tracking.

Additional features include:

- CRUD operations
- Request validation
- DTO-based data transfer
- Global exception handling
- PostgreSQL persistence
- Interactive API documentation with Swagger

## Tech Stack

### Backend

- Java 17
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- Hibernate
- Lombok
- Jakarta Validation
- SpringDoc OpenAPI (Swagger UI)

### Database

- PostgreSQL 18

## Project Architecture

The application follows a layered architecture, separating HTTP request handling, business logic, and database access.

```text
src/main/java/com/mejuloli/os_management
│
├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── OsManagementApplication.java
```

### Architecture Components

| Layer | Responsibility |
|---|---|
| Controller | Handles REST API requests and responses |
| Service | Implements business logic |
| Repository | Manages database access using JPA |
| Model | Defines persistence entities |
| DTO | Transfers data between application layers |
| Exception | Provides centralized error handling |

### Design Principles

- Layered architecture
- Separation of concerns
- Object-oriented programming
- DTOs to decouple API contracts from database entities
- Centralized exception handling
- Data persistence with JPA/Hibernate

## Data Model

### Client

| Field | Description |
|---|---|
| `id` | Unique identifier |
| `name` | Client name |
| `email` | Contact email |
| `phone` | Contact phone number |

### Technician

| Field | Description |
|---|---|
| `id` | Unique identifier |
| `name` | Technician name |
| `specialty` | Area of specialization |

### ServiceOrder

| Field | Description |
|---|---|
| `id` | Unique identifier |
| `client` | Associated client |
| `technician` | Assigned technician |
| `description` | Service description |
| `priority` | Service priority |
| `status` | Current service order status |
| `openedAt` | Creation timestamp |
| `closedAt` | Closing timestamp |

**Available priorities:**

- `LOW`
- `MEDIUM`
- `HIGH`

**Available statuses:**

- `OPEN`
- `IN_PROGRESS`
- `CLOSED`

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/mejuloli/os_management.git
cd os_management
```

### 2. Create the PostgreSQL Database

Connect to PostgreSQL and execute:

```sql
CREATE DATABASE os_management;
```

### 3. Configure the Application

The repository includes a configuration template:

```text
config/template_application.properties
```

Use this template to create your local `application.properties` file in the Spring Boot resources directory.

Update your PostgreSQL credentials:

```properties
# Database connection
spring.datasource.url=jdbc:postgresql://localhost:5432/os_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Application server
server.port=8080
```

> **Security:** The local configuration file containing database credentials is excluded from version control.

### 4. Run the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The API will be available at:

http://localhost:8080

## API Documentation

After starting the application, access Swagger UI:

http://localhost:8080/swagger-ui/index.html

Swagger provides interactive documentation for testing the API endpoints.

You can use it to explore:

- Client CRUD operations
- Technician CRUD operations
- Service order CRUD operations
- Status updates
- Request validation

## API Request Examples

### Create a Client

**Endpoint:**

```http
POST /api/clients
```

**Request body:**

```json
{
  "name": "Test Client",
  "email": "client@example.com",
  "phone": "41999999999"
}
```

### Create a Technician

**Endpoint:**

```http
POST /api/technicians
```

**Request body:**

```json
{
  "name": "Technician 1",
  "specialty": "Networking"
}
```

### Create a Service Order

**Endpoint:**

```http
POST /api/orders
```

**Request body:**

```json
{
  "clientId": 1,
  "technicianId": 1,
  "description": "Computer does not turn on",
  "priority": "HIGH"
}
```

### Update a Service Order

**Endpoint:**

```http
PUT /api/orders/1
```

**Request body:**

```json
{
  "clientId": 1,
  "technicianId": 1,
  "description": "Computer does not turn on",
  "priority": "HIGH",
  "status": "CLOSED"
}
```

## Error Handling

The API implements a global exception handler to provide consistent error responses.

### Example: 404 Not Found

```json
{
  "timestamp": "2025-11-17T20:47:00",
  "status": 404,
  "error": "not found",
  "message": "client not found: 99",
  "path": "/api/clients/99"
}
```

### Handled HTTP Errors

- `400 Bad Request` — Invalid request data
- `404 Not Found` — Requested resource not found
- `500 Internal Server Error` — Unexpected server errors

## Learning Objectives

This project was developed to strengthen practical knowledge of:

- Java backend development
- REST API design
- Spring Boot
- Object-oriented programming
- Layered software architecture
- Database persistence
- Request validation
- Exception handling
- API documentation

## License

This project is available for educational use, study, and modification.
