🧭 Workshop Analytics Microservice with NoSQL Databases

Welcome to the Workshop Analytics Microservice! This repository contains the source code for a microservice-based system designed to manage workshop-related data using NoSQL databases, implement transactional data processing, and generate analytical reports. This project is part of a larger system for managing a psychological clinic's workshop operations.

🚀 Technologies Used

  Backend
- **Java Spring Boot**: Used for developing the backend microservices.
- **Apache Cassandra**: Employed as the columnar NoSQL database for storing workshop analytics data.
- **Neo4j**: Utilized as the graph database for managing relationships between workshop participants, sessions, and categories.
- **Apache Kafka**: Used for implementing the Saga pattern to ensure data consistency across microservices.
- **Grafana**: Optionally used for visualizing report data.

🧱 Microservice Architecture
- The application follows a microservice architecture, enabling independent development, deployment, and scaling of services.

🛠️ Features
 Data Management with NoSQL Databases
- **Columnar Storage (Cassandra)**: Efficient storage and retrieval of workshop analytics data, such as participant counts and session feedback.
- **Graph Database (Neo4j)**: Stores relationships between participants, workshop sessions, and categories with at least 4 node types (e.g., Participant, Session, Category, Feedback) and 3 edge types (e.g., ATTENDED, BELONGS_TO, PROVIDED_FEEDBACK).
- **Test Data Population**: Both databases are populated with sufficient test data to demonstrate CRUD operations and complex queries.
- **CRUD Operations**: Implemented for all node and edge types in Neo4j and for analytics data in Cassandra.
- **Complex Queries (Neo4j)**: Each group member implements at least 5 complex queries, including:
  - 2 queries related to complex CRUD operations (e.g., updating edge properties based on conditions).
  - 3 queries using `MATCH`, `WHERE`, `WITH`, and aggregation functions (e.g., recommending sessions based on participant history).

 Transactional Data Processing
- **Saga Pattern Implementation**: Uses Kafka to ensure data consistency across Cassandra and Neo4j via choreography or orchestration.
- **Cross-Database Transactions**: Supports at least one functionality (e.g., adding a participant to a session) that involves data insertion, update, or deletion across both databases.
- **Example Functionality**: When a participant is added to a session in Neo4j, the participant count for the session category is incremented in Cassandra.

 Report Generation
- **Simple Report Sections**: At least 2 simple sections visualizing filtered data (e.g., listing all sessions with participant counts above a threshold).
- **Complex Report Sections**: At least 1 complex section visualizing results of complex queries (e.g., recommending sessions based on participant feedback and session categories).
- **Optional Features**:
  - Visualization of results using charts in Grafana.
  - Retrieval of data from both Cassandra and Neo4j for unified reports.
- **Report Format**: Generated as PDF documents via programmatic code or visualized in Grafana.
- **Example Report**:
  - **Simple Section**: Display all workshops held in 2024 with participant counts.
  - **Complex Section (Neo4j)**: Recommend sessions to participants based on their previous attendance and feedback, filtered by session category.
  - **Complex Section (Cassandra)**: Display the top 10 most attended workshop categories, grouped by category and sorted by available session slots.

📦 Installation
 Prerequisites
- Java 11 or higher
- Apache Cassandra
- Neo4j
- Apache Kafka
- Grafana (optional for report visualization)
- Maven

🧭 Configuration
 Database Configuration
- **Cassandra**: Update the `application.properties` file in the backend directory with your Cassandra credentials and connection details.
- **Neo4j**: Update the `application.properties` file with your Neo4j credentials and connection details.

 Kafka Configuration
- Ensure Kafka is running and update the `application.properties` file with your Kafka server details.

 Environment Variables
- Set up necessary environment variables for database and Kafka configurations as required.

🚀 Usage
Once the microservices are running, they will:
- Listen for Kafka events to synchronize data across Cassandra and Neo4j.
- Execute transactional operations (e.g., adding a participant to a session and updating analytics).
- Generate reports based on configured queries, accessible as PDFs or visualized in Grafana.

🧱 Contribution
We welcome contributions to improve this project. Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push them to your forked repository.
4. Submit a pull request with a detailed description of your changes.

🛠️ Project Structure
- **Team Organization**: The project is developed by a four-member team, split into two groups. Each group implements:
  - At least one microservice with NoSQL database integration.
  - At least one transactional functionality.
  - At least one report generator.

This project fulfills the requirements outlined in the final project defense, ensuring a robust implementation of microservices, NoSQL databases, transactional processing, and report generation for workshop analytics.
