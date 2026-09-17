# Домашняя работа 45

Spring Boot REST API для работы с аккаунтами. Данные сохраняются в PostgreSQL через Spring Data JPA. API описано через OpenAPI/Swagger.

## База данных

Перед запуском нужно создать базу:

```sql
CREATE DATABASE hw45_db OWNER postgres;
```

По умолчанию приложение подключается к PostgreSQL на `localhost:5432` под пользователем `postgres`.

При необходимости параметры можно переопределить через переменные окружения `DB_URL`, `DB_USER`, `DB_PASSWORD`.

## API

- `GET /api/v1/accounts` — получить список аккаунтов
- `POST /api/v1/accounts` — создать аккаунт

Пример тела POST-запроса:

```json
{
  "firstName": "Arthur",
  "lastName": "Sarifullin",
  "email": "arthur45@mail.ru",
  "password": "qwerty123"
}
```

Пароль сохраняется в сущности, но не возвращается в ответе API.

## Запуск

```bash
mvn spring-boot:run
```

Swagger UI:

`http://localhost:8080/swagger-ui.html`
