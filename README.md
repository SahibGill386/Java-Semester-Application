# Java Library Management System (GUI & Serialization)

## Overview
This repository contains the final iteration (Increment 2) of an academic semester project: a fully functional Library Management System. The application was upgraded from a standard console-based interface to a rich, interactive Graphical User Interface (GUI) utilizing **JavaFX**. Additionally, the system features persistent data storage via **Java Object Serialization**, allowing library inventory, user accounts, and loan records to be saved and loaded across application sessions.

## Core Technologies
* **Language:** Java (JDK 8+)
* **UI Framework:** JavaFX with FXML
* **Data Persistence:** Java Object Serialization (`.ser` files)
* **Architecture:** Model-View-Controller (MVC) Design Pattern

## Key Features

### 1. Persistent Data Storage
The `LibraryDatabase` class leverages `ObjectOutputStream` and `ObjectInputStream` to serialize the state of the application. 
* **Inventory & Accounts:** Automatically saves `Book` and `Account` objects to `library_data.ser` upon any modification (adding a book, creating an account).
* **State Recovery:** Automatically loads the serialized data file upon system boot, ensuring no data loss between sessions.

### 2. Role-Based Access Control (RBAC)
The system differentiates between standard `Patron` accounts and administrative `Librarian` accounts.
* **Patrons:** Can browse the inventory and search for books by Title or Author.
* **Librarians:** Require authentication to access elevated privileges, including processing checkouts, managing returns, and calculating overdue fines.

### 3. JavaFX Graphical Interface
Replaced the legacy CLI with a modern, event-driven JavaFX interface controlled by `GUIController.java`.
* **Search Engine:** Real-time querying of the database by Title or Author, with visual indicators of book availability (Available vs. Rented).
* **Transaction Processing:** Librarians can input a Patron Username and Book ID to instantly process loans or returns, automatically updating the system state and serialized database.
* **Business Logic Enforcement:** The UI prevents Patrons from checking out books if they have exceeded their 5-book limit or possess outstanding fines.

## Project Structure (MVC Architecture)
* **Model:** `Book`, `Account`, `Patron`, `Librarian`, `LoanRecord` (Core data structures).
* **View:** `LibraryUI.fxml` (Defines the visual layout and components).
* **Controller:** `GUIController.java` (Handles UI events, user input, and bridges the View with the Model).
* **Repository:** `LibraryDatabase.java` (Handles local memory storage and file I/O serialization).
