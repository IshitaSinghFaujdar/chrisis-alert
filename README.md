#Chrisis_alert

This is a simple **Java-based** application that helps students determine how much they need to score in their **end-semester exams** to achieve a desired overall average.

---

## 🚀 Features  
✅ Calculate required end-semester marks based on internal marks  
✅ User-friendly **Swing UI** for easy input and results  with login, reset password, sign up and log ticket features. 
✅ Simple, fast, and efficient calculations  

## 🛠️ Installation & Setup  

### **1️⃣ Prerequisites**  
Ensure you have the following installed:  
- **Java (JDK 8 or later)**  
- **IDE** I used VS code to run this.

### **2️⃣ Running the Application**
1. Clone this repository or download the source code:  
   ```sh
   git clone https://github.com/IshitaSinghFaujdar/chrisis-alert.git
   
#on windows
   for compiling:
   ```javac Main.java ```
   for running:
   ```java -cp ".;lib/mysql-connector-j-9.1.0.jar" Main```
   There is a JAR file in library folder, ensure you have it with you.

   #on linux
   ```
   javac Main.java
   java -cp ".:lib/mysql-connector-j-9.1.0.jar" Main
```


   Add a **db_config.properties** file and enter your credentials for mySQL like this.
    ```
       db.url=jdbc:mysql://localhost:3306/chrisis_users
       db.username=root
       db.password=sample@1234
       ```

   Create a database in your MYSQL
    ```CREATE DATABASE chrisis_users;
    USE chrisis_users;
    CREATE TABLE users (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    security_question VARCHAR(255) NOT NULL,
    security_answer VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    username VARCHAR(255),
    issue TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'Open',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );```

    
