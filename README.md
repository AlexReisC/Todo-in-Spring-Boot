# Todo List
This project is a todo list REST API made with Spring Boot.

## Pre-requisites
- JDK version 21
- Maven
- PostgreSQL

## How to run
1. Clone this repository
    ```bash
    git clone https://github.com/AlexReisC/Todo-in-Spring-Boot.git
    cd Todo-in-Spring-Boot
    ```

2. Create the PostgreSQL database:
    ```bash
    CREATE DATABASE todo_spring
    ```
- SET the database credentials (URL, USERNAME, PASSWORD) in the [application.properties](src/main/resources/application.properties) file, if needed.

3. Compile and execute the program:
    ```bash
    ./mvnw spring-boot:run
    ```
    **CTRL+C to shutdown the application**

## API Endpoints

### Create new task
- URL: `/todo`
- Method: `POST`
- Body request:
    ```json
    {
        "title": "task title",
        "description": "task description",
        "completed": false,
        "dueDate": "2024-12-31"
    }
    ```
- Response:
    ```json
    {
        "id": "ID",
        "title": "task title",
        "description": "task description",
        "completed": false,
        "dueDate": "2024-12-31"
    }
    ```

### Get all tasks
- URL: `/todo`
- Method: `GET`
- Success Response:
    ```json
    [
        {
        "id": "ID",
        "title": "task title",
        "description": "task description",
        "completed": false,
        "dueDate": "2024-12-31"
        }
    ]
    ```

### Get task by id
- URL: `/todo/{id}`
- Method: `GET`
- Success Response:
    ```json
    {
        "id": "ID",
        "title": "task title",
        "description": "task description",
        "completed": false,
        "dueDate": "2024-12-31"   
    }
    ```
- Failed Response:
    ```json
    {
        "message": "Task not found"
    }
    ```

### Update a task
- URL: `/todo/{id}`
- Method: `PUT`
- Body request:
    ```json
    {
        "title": "new title",
        "description": "new description",
        "completed": true,
        "dueDate": "2024-12-25"
    }
    ```
- Success Response:
    ```json
    {
        "id": "ID",
        "title": "new title",
        "description": "new description",
        "completed": true,
        "dueDate": "2024-12-25"
    }
    ```
- Failed Response:
    ```json
    {
        "message": "Task not found"
    }
    ```

### Delete a task
- URL: `/todo/{id}`
- Method: `DELETE`
- Success Response:
    ```json
    {
        "message": "Task deleted successfully"
    }
    ```
- Failed Response:
    ```json
    {
        "message": "Task not found"
    }
    ```

## Project structure
- [`TodoApplication.java`](src/main/java/com/app/todo/TodoApplication.java): Run application
- [`model/`](src/main/java/com/app/todo/model/): Model class that will be the table on database
- [`controller/`](src/main/java/com/app/todo/controller/): Controller, handle the API Endpoints
- [`service/`](src/main/java/com/app/todo/service/): Handle the data in database and send to Controller
- [`repository/`](src/main/java/com/app/todo/repository/): Connection with database, implement SQL methods
- [`application.properties`](src/main/resources/application.properties): Configuration file

## Dependencies
- Spring Boot Web
- Spring Data JPA
- Spring Boot Validation
- PostgreSQL Driver
