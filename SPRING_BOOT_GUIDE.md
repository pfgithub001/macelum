# SPRING_BOOT_GUIDE.md

## Purpose

This document defines how any AI assistant must work when creating, editing, reviewing, or restructuring a Spring Boot backend that follows this architecture.

This guide is the source of truth for:
- project creation
- package structure
- backend layering
- API design
- persistence rules
- validation rules
- configuration rules
- CORS rules
- OpenAPI documentation rules
- README expectations
- validation steps

Follow this guide unless the user explicitly requests a different backend architecture.

## Mandatory Rule For AI Assistants

Before making any Spring Boot-related change, always read this guide.

This includes:
- creating a new Spring Boot project
- adding controllers
- adding services
- adding repositories
- adding entities
- adding DTOs
- adding mappers
- editing configuration
- editing persistence
- adding Flyway migrations
- adding or editing OpenAPI documentation
- reviewing or refactoring backend code

If the existing repository already uses a different established backend architecture, inspect the codebase first and preserve the existing project pattern unless the user explicitly asks for a migration.

Do not introduce competing backend patterns without explicit instruction.

## Core Backend Assumptions

Unless the user explicitly requests otherwise, assume the backend must follow these rules:

- Use Spring Boot
- Use Java `21`
- Use `Maven`
- Use `Spring Initializr` as the default project generator
- Use base package `com.macelum.api`
- Use `PostgreSQL` as the database
- Use `Spring Data JPA`
- Use `Flyway` for schema migrations
- Use `application.yml`
- Use DTOs for API requests and responses
- Never expose entities directly from controllers
- Use a service layer for business logic
- Keep controllers thin
- Use centralized configurable CORS
- Do not add authentication by default
- Use `jakarta.validation` on request DTOs
- Use global exception handling
- Use standardized JSON error responses
- Use OpenAPI with an interactive web UI
- Use lightweight OpenAPI annotations only where they add real value
- Allow Lombok with restrictions

## How To Create A New Spring Boot Project

When creating a new backend project, always scaffold a Spring Boot application that matches this architecture.

### Required Defaults

- Folder name: `springboot-be`
- Project generator: `Spring Initializr`
- Language: `Java`
- Java version: `21`
- Build tool: `Maven`
- Group: `com.macelum`
- Artifact: `api`
- Base package: `com.macelum.api`
- Packaging: `jar`

### Mandatory Dependencies

Always include these dependencies at scaffold time:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Flyway Migration
- Validation
- Lombok
- Spring Boot DevTools
- OpenAPI UI support

If OpenAPI UI is not directly available in the generator, add it immediately after scaffolding.

### Preferred Scaffold Rule

Use Spring Initializr as the default project generator.

The exact generator UI or generated defaults may change over time. The required resulting project structure and dependency set matter more than the exact click path.

If another scaffold path is used, the resulting project must still match this architecture exactly.

### Required Result After Scaffolding

After scaffolding, the project must be normalized to this structure and setup:

- base package `com.macelum.api`
- Maven project
- Java `21`
- Spring Web enabled
- Spring Data JPA enabled
- PostgreSQL configured as the intended database
- Flyway enabled for schema migrations
- validation enabled
- Lombok enabled with restricted usage
- DevTools enabled
- OpenAPI UI enabled
- `application.yml` used as the main configuration file
- DTO-based API boundaries
- no authentication layer by default
- centralized CORS configuration
- standardized error handling

If the scaffold tool generates files, folders, or defaults that conflict with this guide, normalize the project immediately before adding features.

### Required Creation Flow

1. Create the Spring Boot project
2. Ensure the project uses Maven
3. Ensure the project uses Java `21`
4. Ensure the base package is `com.macelum.api`
5. Ensure all mandatory dependencies are present
6. Normalize the package structure
7. Create `application.yml`
8. Create the Flyway migration folder
9. Set up centralized CORS configuration
10. Set up global exception handling
11. Ensure DTO-based controller boundaries
12. Create or update `README.md`
13. Verify that the backend runs correctly

### Canonical Backend Structure

```text
springboot-be/
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

  src/test/java/com/macelum/api/
```

### Creation Rules

Always:
- use Spring Initializr by default
- use Maven
- use Java `21`
- use `com.macelum.api` as the base package
- include all mandatory dependencies
- use `application.yml`
- create the canonical package structure
- use Flyway for schema evolution
- update `README.md`

Never:
- expose entities directly from controllers
- skip the service layer
- use scattered controller-level CORS configuration
- add authentication by default
- leave scaffold artifacts that conflict with this guide

## Lombok Rules

Lombok is allowed, but its usage must stay deliberate and restricted.

### Allowed Lombok Usage

Prefer focused Lombok annotations such as:
- `@Getter`
- `@Setter`
- `@Builder`
- `@RequiredArgsConstructor`
- `@NoArgsConstructor`
- `@AllArgsConstructor`

These are appropriate in places such as:
- DTOs
- service classes
- configuration classes
- simple support classes

### Restricted Lombok Usage

Do not use broad Lombok annotations by default, especially:
- `@Data`
- `@ToString`
- `@EqualsAndHashCode`

These annotations can create unsafe behavior, especially with JPA entities.

### Lombok Rule For Entities

Lombok may be used in entities, but only with care.

Always be deliberate about:
- constructors
- equality
- hash code behavior
- string representation

Never use Lombok in a way that hides or breaks JPA behavior.

Avoid broad annotations on entities unless there is a clear reason and the behavior is understood.

## README Rules

Whenever creating a new backend project, always create or update `README.md`.

Backend project creation is not complete until the README contains accurate setup and run instructions.

### Required README Content

At minimum, the backend README must include:
- project name
- short project description
- Java version
- Maven usage
- installation instructions
- how to run the application
- how to run tests
- how to build the application
- database requirements
- environment or configuration requirements
- Flyway migration note
- short package structure summary
- OpenAPI UI location if available
- architecture constraints

### README Writing Rules

Always:
- keep the README short and operational
- document real commands only
- explain how to run the backend locally
- explain the main package structure
- mention important architectural constraints
- mention database and migration expectations

Never:
- leave placeholder sections
- include inaccurate commands
- omit required backend setup steps

## Canonical Package Structure

Use this structure as the default backend layout:

```text
springboot-be/
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

  src/test/java/com/macelum/api/
```

Keep package responsibilities clear and do not collapse layers casually.

## Layer Responsibilities

Use each package for one responsibility only.

### `config/`

Use `config/` for Spring and application configuration.

Examples:
- CORS configuration
- OpenAPI configuration
- bean configuration
- environment-driven app configuration

Do not place business logic here.

### `controller/`

Use `controller/` for HTTP entry points only.

A controller should:
- define routes
- receive validated request DTOs
- call services
- return response DTOs
- stay thin

A controller should not:
- contain business logic
- talk directly to repositories
- expose entities directly
- perform ad hoc mapping inline repeatedly

### `service/`

Use `service/` for business logic.

A service should:
- coordinate application behavior
- enforce business rules
- orchestrate repository access
- map or coordinate mapping when needed

A service should not:
- behave like a controller
- depend on HTTP concerns
- become a raw SQL or persistence implementation layer

### `repository/`

Use `repository/` for persistence access.

A repository should:
- encapsulate database access
- extend Spring Data JPA repositories where appropriate
- remain focused on querying and persistence boundaries

A repository should not:
- contain business logic
- return API response objects
- replace the service layer

### `entity/`

Use `entity/` for JPA entities only.

An entity should:
- represent persistence models
- define table mappings and relationships
- stay persistence-focused

An entity should not:
- be returned directly by controllers
- contain API response behavior
- become a dumping ground for unrelated logic

### `dto/`

Use `dto/` for request and response models.

A DTO should:
- define API contract shapes
- be used at controller boundaries
- carry validation annotations when used for requests

A DTO should not:
- contain persistence annotations
- replace entities
- contain business logic

### `mapper/`

Use `mapper/` for entity and DTO conversion.

A mapper should:
- convert between entities and DTOs
- keep transformation logic explicit
- centralize repetitive mapping behavior

A mapper should not:
- contain business decisions
- call repositories
- become a service substitute

### `exception/`

Use `exception/` for custom exceptions and global exception handling.

This package should contain:
- domain-specific exceptions
- not-found exceptions
- bad-request exceptions when needed
- a global exception handler
- standardized error response models if used

## DTO And Mapper Rules

Controllers must use DTOs for request and response boundaries.

### DTO Rules

Always:
- use request DTOs for incoming payloads
- use response DTOs for outgoing payloads
- keep DTOs focused on transport concerns
- validate request DTOs with `jakarta.validation`

Never:
- expose entities directly from controllers
- use entities as request bodies
- let persistence models define the public API contract

### Mapper Rules

Always:
- keep mapping explicit
- centralize repeated transformations in `mapper/`
- use readable and predictable mapping code

Never:
- scatter repeated mapping logic through controllers
- hide critical mapping behavior inside unrelated layers

## Validation Rules

Use `jakarta.validation` on request DTOs.

### Validation Rules

Always:
- put validation annotations on request DTO fields
- use `@Valid` in controller method boundaries where applicable
- handle validation errors through global exception handling
- return validation failures in the standardized error response style

Examples of valid usage:
- `@NotBlank`
- `@NotNull`
- `@Email`
- `@Size`
- `@Min`
- `@Max`

Never:
- place request validation logic directly into controllers when standard validation annotations are enough
- return inconsistent validation error formats

## Persistence And Flyway Rules

Use PostgreSQL as the intended database and Flyway as the owner of schema evolution.

### Entity Rules

Always:
- define entities in `entity/`
- keep entity relationships deliberate
- design entities for persistence, not API exposure
- be careful with lazy-loaded associations and serialization boundaries

Never:
- expose entities directly through controllers
- rely on entity serialization as the API contract
- place API-specific formatting inside entities

### Repository Rules

Always:
- use repositories for persistence access
- keep repository methods focused and descriptive
- use service methods to orchestrate repository usage

Never:
- place business logic in repositories
- let controllers access repositories directly

### Flyway Rules

Always:
- use Flyway for schema changes
- place migrations in `src/main/resources/db/migration/`
- treat migrations as the source of truth for schema evolution
- create a new migration for each schema change

Never:
- modify the database schema manually without a migration
- silently rewrite old applied migrations in normal workflow
- mix ad hoc schema changes outside Flyway

## CORS And Configuration Rules

Use centralized configurable CORS and structured application configuration.

### Configuration Rules

Always:
- use `application.yml`
- group related configuration under meaningful keys
- keep environment-specific values configurable
- prefer application properties or environment variables over hardcoded values

### CORS Rules

Always:
- configure CORS centrally in `config/`
- make allowed origins configurable
- make relevant methods and headers configurable when needed

Never:
- scatter `@CrossOrigin` across controllers by default
- hardcode environment-specific origins in many places

Recommended configuration direction:
- `app.cors.allowed-origins`
- `app.cors.allowed-methods`
- `app.cors.allowed-headers`

## OpenAPI Rules

OpenAPI and its interactive web UI are required by default.

### OpenAPI Rules

Always:
- enable OpenAPI support
- expose interactive API documentation in development
- keep generated docs aligned with real controllers and DTOs
- use annotations only where they provide meaningful clarity

Use annotations for:
- endpoint summaries
- endpoint descriptions when helpful
- special request or response clarification
- important response documentation

Do not:
- over-annotate every endpoint mechanically
- maintain separate manual API docs that drift from the code
- document entities as public API contracts when DTOs are the real contract

### OpenAPI Documentation Rule

Every public API endpoint should be represented by the generated documentation.

The documentation should reflect:
- request DTOs
- response DTOs
- standard error responses
- route intent

## Error Handling Rules

Use global exception handling and standardized JSON error responses.

### Error Handling Rules

Always:
- centralize exception handling in `exception/`
- return a consistent JSON error format
- handle validation failures consistently
- use meaningful status codes

Recommended error response shape:
- `timestamp`
- `status`
- `error`
- `message`
- `path`

Optional when relevant:
- `fieldErrors`

### Exception Rules

Always:
- create domain-appropriate exceptions when needed
- distinguish not-found, validation, and server errors clearly
- keep controller methods free from repetitive try/catch blocks for standard API error handling

Never:
- leak raw stack traces in API responses
- swallow exceptions silently
- return inconsistent error body shapes across endpoints

## Testing Rules

Use a minimal testing baseline by default.

### Testing Rules

Always:
- add tests where they materially protect business logic or important API behavior
- prioritize service tests and important API flow tests
- keep test setup proportional to the feature

Do not:
- overbuild test infrastructure at scaffold time
- add heavy testing complexity without a clear need

This baseline does not require a large testing stack from day one, but important backend behavior should still be covered.

## Rules For Creating New Backend Features

Before creating a new backend feature, inspect the existing project structure and patterns first.

### Creation Rules

Always:
- inspect similar existing code before adding new files
- place code in the narrowest correct layer
- keep controllers thin
- keep business logic in services
- use DTOs at controller boundaries
- use repositories only for persistence concerns
- add Flyway migrations for schema changes
- keep configuration centralized
- prefer small, consistent additions over broad abstractions

Before creating something new, check:
- whether a similar DTO already exists
- whether a mapper already exists
- whether a service already owns the business area
- whether a repository method already solves the persistence need

Prefer extending existing patterns over creating parallel ones.

## Rules For Editing Existing Backend Projects

When editing an existing backend project, preserve the established structure unless the user explicitly requests a structural change.

### Editing Rules

Always:
- inspect nearby code before editing
- preserve naming and placement conventions
- prefer the smallest correct change
- reuse existing patterns when they already solve the problem

Never:
- move packages or classes without a clear reason
- introduce a competing architectural pattern casually
- bypass Flyway for schema changes
- expose entities directly because it is faster
- add controller business logic as a shortcut

If the existing backend conflicts with this guide:
- follow the established project pattern unless the user asks for alignment or migration

## Validation Checklist

After making backend changes, validate the work using the commands and checks that exist in the project.

### Required Validation

Always run, when available:
- tests
- build
- verification commands
- formatting or lint-like checks if configured

Also verify:
- controllers remain thin
- DTO boundaries are respected
- entities are not exposed directly
- migrations exist for schema changes
- configuration remains centralized
- OpenAPI docs still reflect the API behavior
- error handling remains consistent

### Project Creation Validation

After creating a new backend project, verify:
- the project builds successfully
- the application starts successfully
- the dependency set matches this guide
- `application.yml` exists and is used
- Flyway migration folder exists
- package structure matches this guide
- OpenAPI UI is available if configured for local development
- `README.md` contains real setup instructions

## Forbidden Patterns

Do not introduce the following unless the user explicitly requests them:

- exposing entities directly from controllers
- business logic in controllers
- repositories used directly by controllers
- skipped service layer
- scattered `@CrossOrigin` usage as the default CORS strategy
- direct schema changes without Flyway migrations
- inconsistent error response bodies
- broad Lombok usage such as `@Data` by default
- authentication or security architecture by default
- undocumented public endpoints when OpenAPI is required
- placeholder README instructions
- competing persistence or API boundary patterns without a clear reason

## AI Response Format

When finishing backend work, the assistant should summarize:

- what changed
- which files were added or edited
- why the chosen placement was correct
- what validation was run
- any assumptions, limitations, or follow-up notes

Keep the summary concise, factual, and tied to the actual changes.

## Final Rule

When in doubt:
- inspect the existing backend code first
- choose the smallest correct change
- place code in the narrowest correct layer
- preserve consistency over novelty
- prefer explicit backend boundaries over convenience shortcuts
