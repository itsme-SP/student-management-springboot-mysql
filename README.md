# 🎓 Student Management System (Spring Boot + JWT + MySQL)

## 📌 Project Overview

This is a backend application built using **Spring Boot** that manages student data with secure authentication using **JWT (JSON Web Token)**.

The system allows users to register, login, and perform CRUD operations on student records.

---

## 🚀 Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* MySQL
* JPA / Hibernate
* Maven

---

## 🔐 Features

### Authentication

* User registration with encrypted password (BCrypt)
* Login using username & password
* JWT token generation after successful login
* Stateless authentication using JWT

### Student Management

* Create student
* View students
* Update student
* Delete student

---

## 🔄 Authentication Flow

1. User sends login request with username & password
2. Spring Security authenticates using `AuthenticationManager`
3. If valid → JWT token is generated
4. Client stores token
5. For protected APIs, client sends:

```
Authorization: Bearer <token>
```

6. JWT Filter:

   * Extracts token
   * Validates token
   * Loads user details
   * Sets authentication in `SecurityContextHolder`

---

## 📂 Project Structure

```
com.palle
│
├── controller       → REST APIs
├── service          → Business logic
├── repository       → Database interaction
├── entity           → Database models
├── securityconfig   → Security configuration
├── filter           → JWT filter
```

---

## 📌 API Endpoints

### Auth APIs

* `POST /user/register` → Register user
* `POST /user/login` → Login and get JWT token

### Student APIs

* `GET /students` → Get all students
* `POST /students` → Add student
* `PUT /students/{id}` → Update student
* `DELETE /students/{id}` → Delete student

---

## 🧪 Sample Request (Login)

```
POST /user/login
Content-Type: application/json

{
  "name": "shiva",
  "password": "12345"
}
```

### Response

```
eyJhbGciOiJIUzI1Ni...
```

---

## 🔑 How to Run

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the application
4. Use Postman to test APIs

---

## 📈 Future Improvements

* Role-based authorization (ADMIN / USER)
* DTO + Validation
* Global Exception Handling
* Swagger documentation

---

## 👨‍💻 Author

Shivaprasad S Shetty

---

## ⭐ Notes

This project demonstrates understanding of:

* Spring Security
* JWT authentication
* Backend API design
* Database integration
