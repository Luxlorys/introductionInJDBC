# introductionInJDBC

In this repo i will try to connect to MySQL DataBase with JDBC (Java Database Connectivity).

At first we need to download jdbc driver and mysql connector, then in our IDE connect it's. In IDEA it'e easy. In command line i connect to mysql server, create new user, create test database that named "db" and create table "User" with 3 columns (id - primary key, login - user login and password - user password)

i u wanna connect to jdbc driver in IDEA u have to go to "Project structure" -> modules and add mysql-connector.jar

In the password column I'm going to store passwords in a secure form - as a hash (and, yea, i know i need concat password hash with "salt" ^_^)


