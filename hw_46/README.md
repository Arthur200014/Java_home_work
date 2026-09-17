# Домашняя работа 46

Продолжение REST API из предыдущей работы.

Добавлено:
- пагинация списка аккаунтов;
- сортировка;
- получение аккаунта по id;
- ответ 404, если аккаунт не найден;
- Swagger/OpenAPI.

## База данных

Нужно создать PostgreSQL базу:

```sql
CREATE DATABASE hw46_db OWNER postgres;
```

Параметры подключения лежат в `src/main/resources/application.properties`.

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
- `GET /api/v1/accounts?page=0&size=5&sort=id`
- `GET /api/v1/accounts/{id}`
- `POST /api/v1/accounts`
