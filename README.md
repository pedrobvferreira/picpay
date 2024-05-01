# Picpay API

Java Spring Licence

This project is an API built in Java, Spring, H2 as the database.

The API in to demonstrate how to solve the [PicPay Backend Challenge](https://github.com/PicPay/picpay-desafio-backend) using Java Spring.

Installation
---
Clone the repository:

Usage
---
1. Start the application with Maven
2. The API will be accessible at http://localhost:8080
3. The DB will be accessible at http://localhost:8080/h2-console

API Endpoints
---
The API provides the following endpoints:

GET USERS
```
GET /users - Retrieve a list of all users.
```
```
[
    {
        "id": 1,
        "firstName": "Pedro",
        "lastName": "Silva",
        "document": "123456787",
        "email": "pedro@example.com",
        "password": "senha",
        "balance": 20.00,
        "userType": "MERCHANT"
    }
]
```
POST USERS
```
POST /users - Register a new user into the App
```
```
{
    "firstName": "Lucas",
    "lastName": "Silva",
    "password": "senha",
    "document": "123456783",
    "email": "lucas@example.com",
    "userType": "COMMON",
    "balance": 10
}
```
POST TRANSACTIONS
```
POST /transactions - Register a new Transaction between users (COMMON to COMMON or COMMON to MERCHANT)
```
```
{
  "senderId": 4,
  "receiverId": 1,
  "value": 10
}
```
