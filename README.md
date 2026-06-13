# Library Management System

A Java-based Library Management System designed to manage books, users, and library operations efficiently. The application provides an easy-to-use interface for librarians to maintain records of books and users while handling book issue and return operations.

## Features

* Add, update, and delete books
* Search books by title, author, or ID
* Manage library members
* Issue books to members
* Return books and update availability
* Track issued books
* User-friendly interface
* Persistent data storage using a database

## Technologies Used

* Java
* JavaFX (GUI)
* MySQL Database
* JDBC
* Git & GitHub

## Project Structure

```text
library-management-system/
│
├── src/
│   ├── application/
│   ├── controller/
│   ├── model/
│   └── database/
│
├── resources/
├── docs/
├── .gitignore
├── pom.xml
└── README.md
```

## Installation

### Prerequisites

* Java JDK 17 or later
* MySQL Server
* IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Divyansh00000001/Library-Management-System.git
```

2. Navigate to the project directory:

```bash
cd Library-Management-System
```

3. Create a MySQL database and update the database configuration.

4. Run the application from your IDE.

## Database Configuration

Update your database credentials in the configuration file:

```java
String url = "jdbc:mysql://localhost:3306/library_db";
String username = "root";
String password = "your_password";
```

## Future Enhancements

* Fine calculation for late returns
* Email notifications
* Barcode/QR code integration

## Author

Divyansh Chauhan
