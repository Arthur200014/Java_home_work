# Homework 42-43

Spring MVC application without Spring Boot. Data is stored in PostgreSQL through Spring Data JPA.

## Database

Create database:

```sql
create database hw42_db;
```

Database settings are in `src/main/resources/application.properties`.

## Endpoints

- `GET /users`
- `GET /users/{id}`
- `POST /users`

Example request body:

```json
{
  "name": "Arthur",
  "email": "arthur@mail.ru",
  "age": 20
}
```

Build the project with Maven and deploy `target/hw42-43.war` to Tomcat 10+.
