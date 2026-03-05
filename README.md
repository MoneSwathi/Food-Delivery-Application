🍔 Food Delivery Application 🚀
A java based food delivery web application that allows users to browse restaurants, order food, and manage their orders.

📜 Features
🔐 User Authentication Page → Allows users to log in or sign up
🏠 Home Page → Displays a list of restaurants
📜 Menu Page → Shows menus of a selected restaurant
🛒 Cart → Users can add/remove items
✅ Checkout Page → Users can place an order by providing address details and selecting a payment method.
📖 Order History → Displays past orders
🎉 Order Confirmation → Shows order details
🛠 Technologies Used
Frontend: JSP, CSS
Backend: Java (JDBC,Servlets)
Database: MySQL
IDE: Eclipse
Server: Apache Tomcat
📂 Project Structure
📂 FoodDeliveryApplication
   ├── 📂 src/main/java
   │   ├── 📁 dao
   │   ├── 📁 daoimplementation
   │   ├── 📁 model
   │   ├── 📁 servlets
   │   ├── 📁 utility
   │
   ├── 📂 webapp
   │   ├── .jsp files
   │   ├── .css files
   │   ├── 📂 WEB-INF
   │       ├── web.xml
   │       ├── 📂 lib
   │           ├── mysql-connector-j-8.0.33.jar
🚀 How to Run the Project
To run this project, ensure you have the following installed on your system:

Eclipse IDE
Java JDK 11 or higher
Apache Tomcat 9 or higher
MySQL Database
MySQL Connector JAR (Add to WEB-INF/lib folder)
✅ Step 1: Clone the Repository
git clone 🍔 Food Delivery Application 🚀
A java based food delivery web application that allows users to browse restaurants, order food, and manage their orders.

📜 Features
🔐 User Authentication Page → Allows users to log in or sign up
🏠 Home Page → Displays a list of restaurants
📜 Menu Page → Shows menus of a selected restaurant
🛒 Cart → Users can add/remove items
✅ Checkout Page → Users can place an order by providing address details and selecting a payment method.
📖 Order History → Displays past orders
🎉 Order Confirmation → Shows order details
🛠 Technologies Used
Frontend: JSP, CSS
Backend: Java (JDBC,Servlets)
Database: MySQL
IDE: Eclipse
Server: Apache Tomcat
📂 Project Structure
📂 FoodDeliveryApplication
   ├── 📂 src/main/java
   │   ├── 📁 dao
   │   ├── 📁 daoimplementation
   │   ├── 📁 model
   │   ├── 📁 servlets
   │   ├── 📁 utility
   │
   ├── 📂 webapp
   │   ├── .jsp files
   │   ├── .css files
   │   ├── 📂 WEB-INF
   │       ├── web.xml
   │       ├── 📂 lib
   │           ├── mysql-connector-j-8.0.33.jar
🚀 How to Run the Project
To run this project, ensure you have the following installed on your system:

Eclipse IDE
Java JDK 11 or higher
Apache Tomcat 9 or higher
MySQL Database
MySQL Connector JAR (Add to WEB-INF/lib folder)
✅ Step 1: Clone the Repository
git clone https://github.com/MoneSwathi/Food-Delivery-Application
✅ Step 2: Set Up MySQL Database
Open MySQL Workbench or Command Line.
Create the database using the following command:
CREATE DATABASE FoodDelivery;
Select the Database:
USE FoodDelivery;
Create required tables:
CREATE TABLE user (
 userId INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 username VARCHAR(20),
 password VARCHAR(25),
 email VARCHAR(100) UNIQUE,
 phone VARCHAR(15),
 address VARCHAR(200),
 role 
 enum('admin','customer','restaurantOwner','deliveryPartner'),
 createdDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 lastLoginDate  TIMESTAMP
);

CREATE TABLE restaurant (
 restaurantId INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 address VARCHAR(60),
 phone varchar(20),
 rating  float,
 cusineType varchar(45),
 isActive tinyint,
 eta varchar(45),
 adminUserId int,
 imagePath varchar(200),
 FOREIGN KEY (adminUserId) REFERENCES user(userId)
);

CREATE TABLE menu (
 menuId INT AUTO_INCREMENT PRIMARY KEY,
 restaurantId INT,
 itemName VARCHAR(30),
 description varchar(100),
 price float,
 ratings float,
 isAvailable tinyint,
 imagePath varchar(200),
 FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

CREATE TABLE order (
 orderId INT AUTO_INCREMENT PRIMARY KEY,
 userId INT,
 restaurantId int,
 order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 totalAmount float,
 status enum('placed','shipped','delivered','cancelled') 
 DEFAULT "placed",
 address varchar(500),
 phone varchar(20),
 paymentMode  enum('upi','cod','card') DEFAULT "cod",
 FOREIGN KEY (userId) REFERENCES user(userId),
 FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

CREATE TABLE orderitem (
 orderItemId INT AUTO_INCREMENT PRIMARY KEY,
 orderId INT,
 menuId int,
 quantity int,
 totalPrice float,
 FOREIGN KEY (orderId) REFERENCES order(orderId),
 FOREIGN KEY (menuId) REFERENCES menu(menuId)
);
Update the database connection details in DBConnection.java:
String url = "jdbc:mysql://localhost:3306/your_database_name";
String user = "root";
String password = "your_password";

✅ Step 3: Open the Project in Eclipse
Open Eclipse IDE.
Go to File → Import → Existing Projects into Workspace.
Select "Select root directory" and browse to the cloned project folder.
Click Finish to import the project.
✅ Step 4: Add MySQL Connector JAR
Download MySQL Connector JAR from the MySQL Official Website.
Copy the JAR file to the WEB-INF/lib folder inside your project.
Add the JAR to the Build Path in Eclipse:
Right-click the project in Eclipse → Build Path → Configure Build Path.
Click Add External JARs.
Select the MySQL Connector JAR file and click Apply and Close.
✅ Step 5: Configure the Apache Tomcat Server
In Eclipse, go to Window → Show View → Servers.
Click "No servers are available. Click this link to create a new server...".
Select Apache Tomcat 9 or higher and click Next.
Browse and select the Tomcat installation directory.
Click Finish to complete the setup.
✅ Step 6: Run the Project
Right-click the project in Eclipse → Run As → Run on Server.
Select Apache Tomcat Server and click Finish.
Open your browser and go to:
http://localhost:8080/ProjectName
🤝 Contributing
Feel free to contribute by creating a pull request if any issuse.

📧 Contact
For any queries or suggestions, feel free to reach out at:
📩 m.vyshnavi915@gmail.com
✅ Step 2: Set Up MySQL Database
Open MySQL Workbench or Command Line.
Create the database using the following command:
CREATE DATABASE FoodDelivery;
Select the Database:
USE FoodDelivery;
Create required tables:
CREATE TABLE user (
 userId INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 username VARCHAR(20),
 password VARCHAR(25),
 email VARCHAR(100) UNIQUE,
 phone VARCHAR(15),
 address VARCHAR(200),
 role 
 enum('admin','customer','restaurantOwner','deliveryPartner'),
 createdDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 lastLoginDate  TIMESTAMP
);

CREATE TABLE restaurant (
 restaurantId INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 address VARCHAR(60),
 phone varchar(20),
 rating  float,
 cusineType varchar(45),
 isActive tinyint,
 eta varchar(45),
 adminUserId int,
 imagePath varchar(200),
 FOREIGN KEY (adminUserId) REFERENCES user(userId)
);

CREATE TABLE menu (
 menuId INT AUTO_INCREMENT PRIMARY KEY,
 restaurantId INT,
 itemName VARCHAR(30),
 description varchar(100),
 price float,
 ratings float,
 isAvailable tinyint,
 imagePath varchar(200),
 FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

CREATE TABLE order (
 orderId INT AUTO_INCREMENT PRIMARY KEY,
 userId INT,
 restaurantId int,
 order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 totalAmount float,
 status enum('placed','shipped','delivered','cancelled') 
 DEFAULT "placed",
 address varchar(500),
 phone varchar(20),
 paymentMode  enum('upi','cod','card') DEFAULT "cod",
 FOREIGN KEY (userId) REFERENCES user(userId),
 FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

CREATE TABLE orderitem (
 orderItemId INT AUTO_INCREMENT PRIMARY KEY,
 orderId INT,
 menuId int,
 quantity int,
 totalPrice float,
 FOREIGN KEY (orderId) REFERENCES order(orderId),
 FOREIGN KEY (menuId) REFERENCES menu(menuId)
);
Update the database connection details in DBConnection.java:
String url = "jdbc:mysql://localhost:3306/your_database_name";
String user = "root";
String password = "your_password";

✅ Step 3: Open the Project in Eclipse
Open Eclipse IDE.
Go to File → Import → Existing Projects into Workspace.
Select "Select root directory" and browse to the cloned project folder.
Click Finish to import the project.
✅ Step 4: Add MySQL Connector JAR
Download MySQL Connector JAR from the MySQL Official Website.
Copy the JAR file to the WEB-INF/lib folder inside your project.
Add the JAR to the Build Path in Eclipse:
Right-click the project in Eclipse → Build Path → Configure Build Path.
Click Add External JARs.
Select the MySQL Connector JAR file and click Apply and Close.
✅ Step 5: Configure the Apache Tomcat Server
In Eclipse, go to Window → Show View → Servers.
Click "No servers are available. Click this link to create a new server...".
Select Apache Tomcat 9 or higher and click Next.
Browse and select the Tomcat installation directory.
Click Finish to complete the setup.
✅ Step 6: Run the Project
Right-click the project in Eclipse → Run As → Run on Server.
Select Apache Tomcat Server and click Finish.
Open your browser and go to:
http://localhost:8080/ProjectName
🤝 Contributing
Feel free to contribute by creating a pull request if any issuse.

📧 Contact
For any queries or suggestions, feel free to reach out at:
📩 moneswathilatha@gmail.com
