# Домашняя работа 47

В проект добавлены аккаунты и заказы.

Основной сценарий: при создании заказа сначала проверяется, существует ли аккаунт. Если аккаунта нет, сервер возвращает 404. Если аккаунт найден, заказ сохраняется с привязкой к нему.

## База данных

```sql
CREATE DATABASE hw47_db OWNER postgres;
```

## Запуск

```bash
mvn clean package
mvn spring-boot:run
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

Основные запросы:

- `POST /api/v1/accounts`
- `GET /api/v1/accounts`
- `POST /api/v1/orders`
- `GET /api/v1/orders/account/{accountId}`

Пример заказа:

```json
{
  "accountId": 1,
  "date": "2026-09-18"
}
```
