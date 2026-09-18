# hw_42_43

Домашняя работа по Spring MVC и Spring Data JPA.

## Сделано

- сущность User;
- контроллер, сервис и репозиторий;
- подключение PostgreSQL;
- получение списка пользователей;
- получение пользователя по id;
- добавление пользователя;
- сборка проекта в WAR.

## База данных

```sql
CREATE DATABASE hw42_db OWNER postgres;
```

Настройки подключения находятся в:

```text
src/main/resources/application.properties
```

## Запуск

```bash
mvn clean package
```

После сборки файл:

```text
target/hw42-43.war
```

нужно развернуть в Tomcat.

## Запросы

- `GET /users`
- `GET /users/{id}`
- `POST /users`

Пример POST:

```json
{
  "name": "Arthur",
  "email": "arthur@mail.ru",
  "age": 20
}
```
