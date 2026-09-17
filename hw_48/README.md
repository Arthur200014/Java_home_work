# Домашняя работа 48

В работе добавлена валидация входных данных и глобальная обработка ошибок.

## Что есть

- создание и получение пользователей;
- создание и получение заказов;
- Bean Validation через `@Valid`;
- проверка имени, email, пароля и возраста;
- проверка обязательных полей заказа;
- `GlobalExceptionHandler`;
- отдельный JSON для ошибок 404;
- отдельный JSON со списком ошибок валидации.

## База данных

```sql
CREATE DATABASE hw48_db OWNER postgres;
```

## Запуск

```bash
mvn clean package
mvn spring-boot:run
```

## Пользователь

`POST /api/v1/users`

```json
{
  "firstName": "Arthur",
  "lastName": "Sarifullin",
  "email": "arthur48@mail.ru",
  "password": "Pass123",
  "age": 20
}
```

## Заказ

`POST /api/v1/orders`

```json
{
  "userId": 1,
  "orderDate": "2026-09-18T12:00:00"
}
```

Также доступны:

- `GET /api/v1/users/{id}`
- `GET /api/v1/orders/{id}`
