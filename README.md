# Expense Tracker API

A RESTful API for tracking personal expenses I built with Spring Boot, JPA, and PostgreSQL.

## Features

- Create expenses with amount and description
- PostgreSQL database with relational design (Users, Expenses, Categories)
- Additional functionality (in progress)

## Tech Stack Used

- **Backend:** Spring Boot 3.x, Java 17
- **Database:** PostgreSQL 14
- **ORM:** Spring Data JPA
- **Build Tool:** Maven

## Setup

### Prereqs
- Java 17 or higher
- PostgreSQL 14
- Maven

### Installation

1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/expense-tracker.git
cd expense-tracker
```

2. Set up database
```bash
createdb expense_tracker
```

3. Configure database credentials
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit application.properties with your PostgreSQL credentials
```

4. Run the application
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Create Expense
```bash
POST /api/expenses
Content-Type: application/json

{
  "amount": 25.75,
  "description": "Coffee and bagel"
}
```

**Response:**
```json
{
  "id": 1,
  "amount": 25.75,
  "description": "Coffee and bagel",
  "createdAt": "2025-12-25T00:49:06.20217",
  "user": {...},
  "category": {...}
}
```

## Project Structure
```
src/main/java/com/example/demo/
├── entity/           # JPA entities (User, Expense, Category)
├── repository/       # JPA repositories
├── controller/       # REST controllers
└── ExpenseTracker.java
```

## Working on 

- [ ] Complete CRUD operations (GET, PUT, DELETE)
- [ ] User authentication
- [ ] Input validation
- [ ] Unit tests
- [ ] CI/CD with GitHub Actions
- [ ] API documentation with Swagger

## License

MIT