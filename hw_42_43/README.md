# Домашняя работа 42–43

Приложение на Spring MVC без Spring Boot. Данные хранятся в PostgreSQL через Spring Data JPA.

## База данных

Создать базу:

```sql
create database hw42_db;
```

Настройки подключения находятся в `src/main/resources/application.properties`.

Таблица `users` создаётся Hibernate автоматически при первом запуске.

## Эндпоинты

- `GET /users` — получить всех пользователей
- `GET /users/{id}` — получить пользователя по id
- `POST /users` — добавить пользователя

Пример тела запроса:

```json
{
  "name": "Arthur",
  "email": "arthur@mail.ru",
  "age": 20
}
```

## Запуск

Собрать проект:

```bash
mvn clean package
```

После сборки файл `target/hw42-43.war` нужно развернуть в Tomcat 10+.
