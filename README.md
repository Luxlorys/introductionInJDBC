# introductionInJDBC

In this repo I will try to connect to MySQL DataBase with JDBC (Java Database Connectivity).

At first we need to download jdbc driver and mysql connector, then in your IDE connect it's. In IDEA it's easy. In command line I connect to mysql server, create new user, create test database that named "db" and create table "Users" with 4 columns

I'm gonna connect to jdbc driver in IDEA u have to go to "Project structure" -> modules and add mysql-connector.jar

In the password column I'm going to store passwords in a secure form - as a hash + salt (salt - unique string)


