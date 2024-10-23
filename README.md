# Book Manager API

The Book Manager API provides a simple and efficient way to manage your book collection. It allows users to retrieve, add, and search for books using a variety of endpoints. 

## Features

- Get a Book by ID: Retrieve detailed information about a specific book using its unique identifier.
  - Endpoint: GET /{bookId}
- Get All Books: Fetch a list of all books in the collection.
  - Endpoint GET /
- Add a New Book: Create and add a new book to the collection by submitting its details.
  - Endpoint POST / 
  - Example Body: {
    "title": "Lo que el viento se llevo",
    "authors":"MCR",
    "publishYear":"2024-01-01T00:00:00",
    "languages":["english", "spanish"] }
- Search Books by Title: Find books in the collection that match a specified title.
  -  Endpoint: GET /search?title={bookTitle}

## Setup
 - First you must clone the repo using git clone command : git clone https://github.com/WiseSandwich/book-manager.git
 
 - Second Book Manager Api has docker-compose integrated with the build process so you don't have to run complicated command
   -  Open terminal and go to the directory of this project
   -  run docker-compose up 
   - Once docker compose finish , you can start to enjoy the book manager api
   - To ensure that is running you can try in your browser http://localhost:8080/book
   - Enjoy :) 

## Technologies
### The Book Manager API is built using the following technologies:

- Java: The primary programming language used for development.
- Spring Boot: Framework for creating stand-alone, production-grade Spring applications.
- Maven: Dependency management tool that simplifies project configuration and builds.
- H2 Database: In-memory database for fast and efficient data storage during development and testing.
- JPA (Java Persistence API): Provides a standard way to manage relational data in Java applications using Object-Relational Mapping (ORM).
- Groovy and Spock: Used for writing tests and creating mocks.
- JaCoCo: Tool for measuring test coverage and generating reports to ensure code quality.
- Docker: Utilized for containerizing the application, ensuring consistent environments across development and production.
- Docker Compose: Simplifies the management and deployment of multi-container applications, streamlining the build process.

