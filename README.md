# Spring Boot CRUD with Kafka and ActiveMQ

This project is a **Spring Boot application** that demonstrates:
- **CRUD operations** for `Student` and `Teacher` entities using **Spring Data JPA**.
- **ActiveMQ** messaging for sending and receiving asynchronous messages.
- **Apache Kafka** integration with producer and consumer services.
- **Pagination** and **DTO mapping** for better response handling.

---

## **Features**
- **Student & Teacher CRUD:** REST endpoints for adding, updating, deleting, and assigning teachers to students.
- **Kafka Integration:** 
  - `KafkaProducerService` to send messages.
  - `KafkaConsumerService` to consume messages.
- **ActiveMQ Messaging:**
  - `MessageProducer` and `MessageConsumer` for queue-based messaging.
- **Logging:** Trace, debug, info, warn, and error levels implemented in `StudentController`.
- **DTOs:** `StudentWithTeachersDTO`, `TeacherWithStudentsDTO` for clean API responses.

---

## **Tech Stack**
- **Java 17+**  
- **Spring Boot 3+**  
- **Spring Data JPA & Hibernate**  
- **Apache Kafka**  
- **ActiveMQ**  
- **Lombok**  
- **H2/MySQL (configurable)**  

---

## **Project Structure**
src/main/java/com/example/task
├── TaskApplication.java
├── Student/
│ ├── Student.java
│ ├── StudentController.java
│ ├── StudentTeacherName.java
├── Teacher/
│ ├── Teacher.java
│ ├── TeacherStudentName.java
│ ├── TeacherController.java
├── Repositoy/
│ ├── StudentRepository.java
│ ├── TeacherRepository.java
├── DTO/
│ ├── StudentWithTeachersDTO.java
│ ├── TeacherWithStudentsDTO.java
├── kafka/
│ ├── KafkaProducerService.java
│ ├── KafkaConsumerService.java
│ ├── StudentPartitionedConsumer.java
│ ├── StudentKafkaEventsService.java
│ └── KafkaController.java  
├── activemq/
│ ├── MessageProducer.java
│ ├── MessageConsumer.java
│ ├── MQController.java


---

## **Getting Started**

### **1. Prerequisites**
- Install **Java 17** or higher.
- Install **Maven** or use IntelliJ built-in Maven.
- Install and run:
  - **Apache ActiveMQ** (default port: 61616)
  - **Apache Kafka** (default port: 9092)
    - Start **Zookeeper** and **Kafka Broker** before running the app.

### **2. Clone the Repository**
git clone https://github.com/Saif-here/springboot-activemq-demo.git
cd springboot-activemq-demo

API Endpoints
Students
GET /students - Get all students

GET /students/{id} - Get student by ID

POST /students - Add a new student

PUT /students/{id} - Update student

DELETE /students/{id} - Delete student

PUT /students/{studentId}/assign-teacher/{teacherId} - Assign a teacher to a student

GET /students/page - Get paginated students

GET /students/grouped - Get students with their teachers (DTO format)

Kafka
POST /kafka/send?message=Hello - Send a test message to Kafka

ActiveMQ
POST /mq/send?message=Hello - Send a test message to ActiveMQ


Logging
All CRUD operations in StudentController are logged at various levels (TRACE, DEBUG, INFO, WARN, ERROR).

Logging configuration can be adjusted in application.properties:

properties
logging.level.root=WARN
logging.level.com.example.task=INFO

Kafka Commands (Windows)
Start Zookeeper:

.\bin\windows\zookeeper-server-start.bat ..\..\config\zookeeper.properties
Start Kafka Broker:

.\bin\windows\kafka-server-start.bat ..\..\config\server.properties
Create Topic:

.\bin\windows\kafka-topics.bat --create --topic student-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
Consume Messages:

.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic student-topic --from-beginning

