# Домашняя работа 44

Первый проект на Spring Boot. Реализован небольшой REST API для каталога автомобилей с хранением данных в PostgreSQL через Spring Data JPA.

## База данных

Создать базу:

```sql
create database hw44_db;
```

Настройки подключения находятся в `src/main/resources/application.properties`.

## Эндпоинты

- `GET /api/cars?page=0&size=10&sort=id` — список автомобилей с пагинацией
- `GET /api/cars/{id}` — автомобиль по id
- `POST /api/cars` — добавить автомобиль

Пример POST-запроса:

```json
{
  "brand": "Toyota",
  "model": "Camry",
  "year": 2021,
  "price": 2800000
}
```

## Запуск

```bash
mvn spring-boot:run
```

После запуска приложение доступно на `http://localhost:8080`.
