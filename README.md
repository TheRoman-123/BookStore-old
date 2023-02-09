# BookStore Project - Backend

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is an Online Book-Store backend part powered by Spring that provides the main functions 
you'd expect from store, such as Log in, Getting info about books for sale, possibility to buy it, etc.

## Technologies
Project is created with:
* OpenJDK-11
* Spring Boot 2.7.8
* PostgreSQL 11.18
* JJWT library
* JUnit, Mockito

## Setup
Run this project: 

```
$ ./mvnw spring-boot:run
```

## Try it!
Send the following request in Postman!

To register send a request to "http://localhost:8080/register" with:
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "078111222",
    "email": "john.doe@yahoo.com",
    "password": "HelloWorld!2"
}
```

Login to "http://localhost:8080/api/auth/login" with:
```json
{
    "email": "john.doe@yahoo.com",
    "password": "HelloWorld!2",
    "rememberMe": false
}
```

Copy access token and paste to _Auth -> Bearer -> Token_. Enjoy your adventure as a customer user!

###### Or try frontend-part powered by Angular (dev-version):
[BookStore Project - Frontend](https://github.com/TheRoman-123/BookStore-Frontend)
