# Spring Boot CRUD REST App

Lightweight Spring Boot application providing a CRUD REST API for managing tasks.

---

## Project Overview

- Java 17+ (or compatible), Spring Boot
- JPA/Hibernate for persistence
- Simple Task domain with status and timestamps
- REST API under the /api prefix


## Quick Start

Prerequisites:
- Java 17+ installed
- Maven installed (or use the bundled mvnw/mvnw.cmd)

Steps to run locally:
1. Clone the repository.
2. From project root, build and run:

   Using Maven wrapper (Windows):
   mvnw.cmd spring-boot:run

   Or with installed Maven:
   mvn spring-boot:run

3. The application starts on http://localhost:8080 by default.

Configuration:
- application.yml contains application-specific settings. Adjust datasource or other properties if needed.


## Project Workflow

Development:
- Create feature branches from main (or master).
- Implement logic in src/main/java and tests in src/test/java.
- Use Lombok annotations already present (ensure IDE Lombok plugin enabled).
- Run unit tests with `mvn test`.

Build & CI:
- Build with `mvn clean package` to produce a jar in target/.
- Include unit tests and static analysis in CI pipeline.

Running & Debugging:
- Use the Spring Boot run goal or run the generated jar with `java -jar target/*.jar`.
- Logs are printed to console; adjust logging in application.yml.


## API Documentation

Base URL: http://localhost:8080/api

Common response format (JSON):
{
  "success": boolean,
  "message": string,
  "data": object | array | null,
  "error": object | null
}

Model: Task
- id: Long (generated)
- title: String (required)
- description: String
- startDate: Date (optional)
- endDate: Date (optional)
- status: Enum (TODO, IN_PROGRESS, DONE) — stored as string
- createdAt: LocalDateTime (set on create)
- updatedAt: LocalDateTime (set on update)


Endpoints

1) Create Task
- Method: POST
- URL: /api/tasks
- Request JSON body (example):
{
  "title": "Write unit tests",
  "description": "Add tests for TaskService",
  "startDate": "2026-01-01T00:00:00.000Z",
  "endDate": "2026-01-10T00:00:00.000Z",
  "status": "TODO"
}
- Response: 201 Created
  success true and data contains created Task object.

Curl example:
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Write unit tests","description":"tests","status":"TODO"}'

2) Get All Tasks
- Method: GET
- URL: /api/tasks
- Response: 200 OK
  success true and data is an array of Task objects.

Curl example:
curl http://localhost:8080/api/tasks

3) Get Task By ID
- Method: GET
- URL: /api/tasks/{id}
- Response: 200 OK
  success true and data is the Task object for the provided id.
- If not found: service may throw an exception — ensure exception handlers in controller/service return appropriate error responses.

Curl example:
curl http://localhost:8080/api/tasks/1

4) Update Task
- Method: PUT
- URL: /api/tasks/{id}
- Request JSON body: same shape as create (fields to update)
- Response: 200 OK
  success true and data contains updated Task object.

Curl example:
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated title","status":"IN_PROGRESS"}'

5) Delete Task
- Method: DELETE
- URL: /api/tasks/{id}
- Response: 200 OK
  success true and data null on successful deletion.

Curl example:
curl -X DELETE http://localhost:8080/api/tasks/1


Notes & Best Practices
- Validate request bodies (add @Valid and DTOs if needed).
- Add exception handlers (@ControllerAdvice) to return standardized error payloads.
- Consider pagination for GET /tasks if dataset grows.
- Ensure timezone handling for date fields in JSON (use ISO-8601 and configure Jackson if needed).


License
- No license specified. Add LICENSE file if you plan to open-source the project.



