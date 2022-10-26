# Online Shopping App

- This app performs all the fundamental CRUD operations of any Online Shopping Management System platform with user validation at every step.
- This project is fully developed by me.

## Tech Stack

- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Spring Security
- Thymeleaf
- Hibernate
- MySQL
- PostMan
- Lombok

## Modules

- Login, Logout Module
- User Module
- Admin Module
- Category Management Module
- Product Management Module
- Feedback Module
- Cart Module

## Features

- User and Admin authentication & validation with spring security is done.
- Admin Features:

  - Administrator Role of the entire application
  - Only registered admins with valid session can add/update/delete category, product from main database.

- User Features:
  - A user can register himself or herself on the platform.
  - He/She can check the all products and can check products by category.
  - He/She can view the product's all details.
  - User can add products to their cart.
  - Can remove products from their cart or can checkout and pay.
  - When paid they will recieve a receipt with all details of that order.

## Installation & Run

- Before running the server, you should update the database config inside the [application.properties](https://github.com/mrFarooque/OnlineShopping/blob/main/OnlineShopping/src/main/resources/application.properties) file.
- Update the port number, username and password as per your local database config.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/db2;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=farsql

```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/admin/home`

`http://localhost:8080/user/shop`

## Video Explainer of flow control

<a href="#">**Video Drive Link** </a>

### E-R Diagram Of Online Shopping Application

---

<img src="./Images/er-diagram.png" style="max-width:100%;">

---

### Home Page

---

<img src="./Images/home.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Registration Page

---

<img src="./Images/registration.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Login Page

---

<img src="./Images/login.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Admin Home

---

<img src="./Images/adminHome.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Product Management Page

---

<img src="./Images/products.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### User Home

---

<img src="./Images/userHome.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Product Details

---

<img src="./Images/productDetails.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Cart Page

---

<img src="./Images/cart.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Receipt Page

---

<img src="./Images/receipt.png" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

<img src="https://github.com/shivamgarg796/Spring-work/blob/master/Images/Thank-you-word-cloud.jpg?raw=true" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---
