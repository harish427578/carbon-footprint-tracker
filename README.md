#  Carbon Footprint Tracker  
### Java + JDBC + MySQL + HTML/CSS

A simple application to record daily activities and calculate carbon emissions. Built using **Core Java (JDBC)**, **MySQL**, and **HTML/CSS**. Suitable for beginners and academic projects.

---

## 🚀 Features
- Add / update / delete activity logs  
- Auto emission calculation: `emissions = quantity × emission_factor`  
- View all logs in table format  
- Monthly CO₂ summary  
- Identify highest emission activity  
- Pure Java JDBC (no APIs, no Spring Boot)

---

## 🛠️ Tech Stack
- Java 8+  
- JDBC  
- MySQL  
- HTML/CSS  
- MySQL Connector JAR  

---

##  Project Structure
src/
db/DBConnection.java
model/Activity.java
dao/ActivityDAO.java
ui/MainMenu.java
web/
index.html
add_activity.html
view_activity.html
style.css
sql/schema.sql

pgsql
Copy code

---

##  Database Schema
```sql
CREATE TABLE activities (
  activity_id INT AUTO_INCREMENT PRIMARY KEY,
  activity_type VARCHAR(100),
  date DATE,
  quantity DECIMAL(10,2),
  emission_factor DECIMAL(10,4),
  emissions_kg DECIMAL(10,2),
  notes VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
🔌 JDBC Connection (Example)
java
Copy code
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/carbon_tracker";
    private static final String USER = "root";
    private static final String PASS = "your_password";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("DB Error: " + e);
            return null;
        }
    }
}
 Useful SQL Queries
Monthly emissions

sql
Copy code
SELECT DATE_FORMAT(date, '%Y-%m') AS month, SUM(emissions_kg) AS total
FROM activities GROUP BY month;
Highest emission activity

sql
Copy code
SELECT activity_type, SUM(emissions_kg) AS total
FROM activities GROUP BY activity_type
ORDER BY total DESC LIMIT 1;
How to Run
Create MySQL database:

sql
Copy code
CREATE DATABASE carbon_tracker;
Run schema.sql

Update DB credentials in DBConnection.java

Add MySQL connector JAR to project

Run MainMenu.java (or UI file)
