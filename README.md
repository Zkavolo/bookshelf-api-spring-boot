# Database Diagram

***

![Bookshelf.png](Images/Bookshelf.png)

***

# Spring Boot Configs

### Project
- Maven

### Language
- Java 17

### Spring Boot ver
- 3.3.2

### Dependencies
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- Spring Boot DevTools
- Spring Security
- Validation
- JSON Web Token

### Env

- ${port}
- ${postgresUser}
- ${postgresPassword}
- ${databasePath}
- ${JWT_Secret}

***
### Post Man Documentation
link : https://documenter.getpostman.com/view/36650261/2sA3s7iULG

***
### End Points

### Users Table

#### Register

```shell
[POST] api/v1/auth/register
```

#### Request

```json
{
    "name" : "Axel",
    "email" : "example@gmail.com",
    "password" : 1234
}
```

#### Response

```json
{
    "name": "Axel",
    "email": "example@gmail.com"
}
```
***

#### Login

```shell
[POST] api/v1/auth/login
```

#### Request

```json
{
    "email" : "example@gmail.com",
    "password" : 1234
}
```

#### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGdtYWlsLmNvbSIsImlhdCI6MTcyMzcyNjgzOSwiZXhwIjoxNzIzNzMwNDM5fQ.YTnycNcXeZE4po28Zv3ZaRqBT8ZBLszOtdQivjaCfNo"
}
```
***
### Categories Table

Before every endpoints configure The Post Man request using Bearer Token and use the token from login.
![img.png](img.png)
This is required because every endpoint in categories needs authentication
#### Create Category

```shell
[POST] api/v1/categories
```

#### Request

```json
{
  "name" : "History"
}
```

#### Response

```json
{
  "categoryName": "History",
  "createdAt": "2024-08-15",
  "updatedAt": "2024-08-15"
}
```
***
#### Get All Categories

```shell
[GET] api/v1/categories
```

#### Response

```json
{
  "items": [
    {
      "categoryName": "History",
      "createdAt": "2024-08-14",
      "updatedAt": "2024-08-14"
    },
    {
      "categoryName": "Education",
      "createdAt": "2024-08-14",
      "updatedAt": "2024-08-14"
    },
    {
      "categoryName": "Sci-fi",
      "createdAt": "2024-08-14",
      "updatedAt": "2024-08-14"
    }
  ],
  "totalElements": 3,
  "totalPages": 1,
  "size": 10,
  "page": 0
}
```
***
#### Get One Category

```shell
[GET] api/v1/categories/{id}
```

#### Request
Path Variable : {id}

#### Response

```json
{
  "categoryName": "History",
  "createdAt": "2024-08-14",
  "updatedAt": "2024-08-14"
}
```
***
#### Update One Category

```shell
[PUT] api/v1/categories/{id}
```

#### Request
Path Variable : {id}
```json
{
    "name" : "Fantasy"
}
```

#### Response

```json
{
  "categoryName": "Fantasy",
  "createdAt": "2024-08-14",
  "updatedAt": "2024-08-15"
}
```
***
#### Delete One Category

```shell
[DELETE] api/v1/categories/{id}
```

#### Request
Path Variable : {id}

#### Response

```json
{
  "id": 3,
  "message": "Sci-fi has been succesfully deleted!"
}
```
***
### Books Table

Before every endpoints configure The Post Man request using Bearer Token and use the token from login.
![img_1.png](img_1.png)
This is required because every endpoint in books needs authentication
#### Create Book

```shell
[POST] api/v1/books
```

#### Request

```json
{
  "categoryId" : 2,
  "name" : "Guide To Java",
  "author" : "James T. T. Streib",
  "publisher" : " Springer-Verlag",
  "pages" : 371
}
```

#### Response

```json
{
  "name": "Guide To Java",
  "pages": 371,
  "category": "Education",
  "author": "James T. T. Streib",
  "publisher": " Springer-Verlag"
}
```
***
#### Get All Books

```shell
[GET] api/v1/books
```

#### Response

```json
{
  "items": [
    {
      "name": "The C# Player's Guide (5th Edition)",
      "pages": 495,
      "category": "Education",
      "author": "RB WhiteTaker",
      "publisher": "Paperback"
    },
    {
      "name": "Harry Potter",
      "pages": 340,
      "category": "Education",
      "author": "J.K Rowling",
      "publisher": "Amazon"
    }
  ],
  "totalElements": 2,
  "totalPages": 1,
  "size": 10,
  "page": 0
}
```
***
#### Get All Books With Specification

```shell
[GET] api/v1/books?category={value}
```

#### Request
Request Param : category : {value}

#### Response

```json
{
  "items": [
    {
      "name": "The C# Player's Guide (5th Edition)",
      "pages": 495,
      "category": "Education",
      "author": "RB WhiteTaker",
      "publisher": "Paperback"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "page": 0
}
```
***
#### Get One Book

```shell
[GET] api/v1/books/{id}
```

#### Request
Path Variable : {id}

#### Response

```json
{
  "name": "Guide To Java",
  "pages": 371,
  "category": "Education",
  "author": "James T. T. Streib",
  "publisher": " Springer-Verlag"
}
```
***
#### Update One Book

```shell
[PUT] api/v1/books/{id}
```

#### Request
Path Variable : {id}
```json
{
  "categoryId" : 1,
  "name" : "Harry Potter",
  "author" : "J.K Rowling",
  "publisher" : "Amazon",
  "pages" : 340
}
```

#### Response

```json
{
  "name": "Harry Potter",
  "pages": 340,
  "category": "Fantasy",
  "author": "J.K Rowling",
  "publisher": "Amazon"
}
```
***
#### Delete One Book

```shell
[DELETE] api/v1/books/{id}
```

#### Request
Path Variable : {id}

#### Response

```json
{
  "id": 1,
  "message": "Guide To Java has been succesfully deleted!"
}
```
***