# 🏢 BTI Real Estate Management System

A robust console-based application developed in **Kotlin** for managing and accounting real estate objects for the BTI (Bureau of Technical Inventory). The system provides a full cycle of CRUD operations, data persistence, and statistical analysis.

## 📖 About the Project

This project was developed as a practical assignment to demonstrate proficiency in Object-Oriented Programming (OOP), file handling, and data manipulation in Kotlin. The application simulates a real-world registry system where users can manage property records efficiently without the need for a complex database.

## ✨ Key Features

*   **Full CRUD Operations:** Create, Read, Update, and Delete real estate records.
*   **Data Persistence (JSON):** Automatically saves and loads data to/from a local JSON file (`realty_data.json`), ensuring data is not lost between sessions.
*   **Advanced Search:** Search for objects by address using partial string matching.
*   **Sorting:** Sort records by any field (ID, address, type, area, or price).
*   **Statistical Analysis:** Calculate total, average, minimum, and maximum property prices in the registry.
*   **User-Friendly Console UI:** Clear menus, error handling for incorrect inputs, and formatted output.

## 🛠 Tech Stack & Concepts

*   **Language:** Kotlin
*   **Core Concepts:** 
    *   Object-Oriented Programming (Data classes, Objects)
    *   Collections framework (`MutableList`, `map`, `filter`, `sortBy`)
    *   File I/O operations (`java.io.File`)
    *   Exception handling (`try-catch` blocks)
    *   String manipulation and formatting

## 📂 Project Structure

The project follows a clean separation of concerns:

*   `Main.kt` — Entry point, contains the main menu loop and user interaction logic.
*   `RealEstate.kt` — Data class representing the real estate object (Model).
*   `FileHandler.kt` — A singleton object responsible for reading and writing data to the JSON file.
*   *(Note: The exact filenames may vary depending on your project setup)*

## 🚀 How to Run

1. Ensure you have **JDK** (Java Development Kit) and **Kotlin** installed on your machine.
2. Clone the repository:
   ```bash
   git clone https://github.com/Makrin-ai/[YOUR_REPO_NAME].git
