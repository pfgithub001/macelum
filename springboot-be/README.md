# springboot-be

Spring Boot backend aligned with `../SPRING_BOOT_GUIDE.md`.

## Tech Stack

- Spring Boot
- Java 21
- Maven
- PostgreSQL
- Spring Data JPA
- Flyway
- Lombok
- OpenAPI UI

## Prerequisites

- Java 21
- Docker

## Installation

```bash
./mvnw clean compile
```

## Available Commands

```bash
./mvnw spring-boot:run
./mvnw test
./mvnw verify
./mvnw clean package
```

## Configuration

Copy the shared root environment file and adjust values if needed:

```bash
cp ../.env.example ../.env
```

Set these environment variables when needed:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`
- `POSTGRES_PORT`
- `POSTGRES_CONTAINER_NAME`

The shared environment file uses generic reusable defaults:

- database: `app_db`
- username: `app_user`
- password: `app_password`
- port: `5432`

## Start PostgreSQL With Docker

```bash
cp ../.env.example ../.env
docker compose -f ../docker-compose.yml up -d
```

The root Docker Compose file reads values from the root `.env`.

The example values start PostgreSQL with these defaults:

- database: `app_db`
- username: `app_user`
- password: `app_password`
- port: `5432`

To stop it:

```bash
docker compose -f ../docker-compose.yml down
```

To stop it and remove the database volume:

```bash
docker compose -f ../docker-compose.yml down -v
```

## Run Locally

```bash
cp ../.env.example ../.env
docker compose -f ../docker-compose.yml up -d
./mvnw spring-boot:run
```

OpenAPI endpoints:

- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api-docs`

## Package Structure

```text
src/main/java/com/macelum/api/
  config/
  controller/
  service/
  repository/
  entity/
  dto/
  mapper/
  exception/

src/main/resources/
  application.yml
  db/migration/
```

## Architecture Constraints

- controllers return DTOs only
- entities are never exposed directly
- business logic lives in `service/`
- schema changes go through Flyway migrations
- CORS is configured centrally
- authentication is not included by default
