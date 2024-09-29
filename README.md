# Object-Oriented Programming II

This repository contains assignments and code solutions for the COMP249 course. All assignments are developed using Eclipse.
Language: Java (with Javadoc)
**Type:** Individual Assignment

## Course Objective

The course covers object-oriented programming concepts such as inheritance, composition, polymorphism, static and dynamic binding, abstract classes, exception handling, file I/O, recursion, interfaces, inner classes, generics, collections, iterators, and graphical user interfaces. Data structures like Array Lists, Linked Lists, Hash Tables, Trees, and Sets will also be introduced.

## Main takeaway:

- Building modular and scalable software applications.
- Designing class hierarchies with inheritance, polymorphism, and abstract classes.
- Managing complex data using Java collections and custom data structures.
- Implementing robust error handling with exceptions.
- Understanding and applying recursion.
- Developing user-friendly interfaces with GUIs.

## Assignments

### **Assignment 0: Book Inventory Program**

- **Objective:**  
  This assignment reviews key OOP topics, including classes, loops, arrays, inheritance, polymorphism, static attributes, and static methods.

- **Description:**  
  Develop a Java program for a bookstore to manage its book inventory. Users can perform actions like adding, updating, and displaying books, filtering based on price, or searching for specific authors.

- **Key Features:**
  - **Book Class:**  
    A book object contains attributes such as title, author, ISBN, and price. The class includes methods to modify or retrieve these values.
  - **Inventory Management:**  
    Users can add, update, or view books, with access secured by password protection. An array keeps track of the books.
  - **Menu System:**  
    The program offers a menu to the user with options to manage the inventory, view books by a specific author, filter by price, and exit the system.

### **Assignment 1: Library System**

- **Objective:**  
  Develop a library management system that tracks items such as books, journals, and media, as well as client records. This system allows employees to manage items and leases, using inheritance to implement class hierarchies.

- **Description:**  
  Implement a library management system where employees manage items (books, journals, and media) and client leases. The program tracks all items and clients, handling operations like adding, deleting, leasing, and returning items.

- **Key Features:**
  - **UML Design:**  
    Design a UML diagram that represents the hierarchy of classes, including items and clients. The UML accurately shows the relations between these entities.
  - **Inheritance and Packages:**  
    Implement multiple classes (such as books, journals, and clients) using inheritance, organized into three different Java packages.
  - **Menu-Driven Program:**  
    The program provides options for library employees to add or delete items, lease or return items, display leased items, and manage clients.
  - **Additional Methods:**  
    Implement methods like `getBiggestBook()` to find the largest book by pages and `copyBooks()` to create a deep copy of the books array.

### **Assignment 2: Movie File Manager**

- **Objective:**  
  Develop a system to manage movie records using file input/output (I/O), serialization, and error handling. This program will organize movie data into genre-specific files, serialize the records, and allow users to interactively navigate through the data.

- **Description:**  
  Create a program that helps organize and manage movie records. It reads movie data from text files, identifies and handles any syntax or semantic errors, and sorts the valid records into genre-based files. Once organized, the records are serialized into binary files. The program also allows users to interactively navigate through the movie arrays.

- **Key Features:**

  - **Movie Class:**  
    Create a `Movie` class with 10 attributes (year, title, duration, genres, rating, score, director, actor1, actor2, actor3). The class should be serializable and implement accessor, mutator, `equals()`, and `toString()` methods to manage and display movie data.

  - **Input File Processing:**  
    Process CSV-formatted input files containing movie records. The program will partition valid records into genre-specific files, while invalid records (those with syntax or semantic errors) will be logged into a separate error file.

  - **Serialization:**  
    Serialize the valid movie records into arrays, saving them as binary files for each genre. Create a manifest file to track the names of these binary files for easy retrieval later.

  - **Interactive Navigation:**  
    Implement a menu-driven interface that allows users to select a genre and navigate through the movie records. Users can move through the records using simple commands, making the system easy to interact with.

  - **Error Handling:**  
    Include robust exception handling for syntax and semantic errors during file processing. For example, records with missing fields or invalid values will be captured and logged, ensuring the program continues running smoothly.

### **Assignment 3: Vocabulary Control Center**

- **Objective:**  
  Create an interactive vocabulary management system using linked lists that allows students to input, modify, and search vocabulary words under various topics.

- **Description:**  
  Mrs. Jones, an elementary school teacher, needs a system to manage vocabulary words for her students. Each word is grouped under a topic, and students can interact with the system to browse, add, remove, or search for words.

- **Key Features:**
  - **Doubly Linked List for Topics:**  
    Implement a custom doubly linked list to store vocabulary topics (e.g., Sports, Food). Each topic contains an associated list of vocabulary words.
  - **Singly Linked List for Words:**  
    For each topic, implement a singly linked list to store vocabulary words (e.g., Hockey, Tennis). These words are linked under their respective topics.
  - **Interactive Menu:**  
    The program provides a menu with options such as browsing topics, adding or removing words, modifying existing topics, searching for words, and more. Students can easily navigate through these options.
  - **Search Functionality:**  
    The system allows students to search for vocabulary words that start with a specified letter. The matching words are stored in an `ArrayList`, sorted, and displayed.
  - **File Input/Output:**  
    The vocabulary data can be saved to and loaded from a file, allowing the system to persist and retrieve vocabulary topics and words.

## IDE

- **Eclipse Version**: 2022-12 (4.26.0)
