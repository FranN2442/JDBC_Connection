# JDBC_Connection

##Index

* [**Introduction**](#Introduction)
* [**Database creation**](#Database-creation)
* [**Maven Project**](#Maven-Project)
* [**POM.XML configuration for JDBC**](#POM.XML-configuration-for-JDBC)
* [**Connecting to MariaDB**](#Connecting-to-MariaDB)




## Introduction

In this unit we have learned how to connect a java file with an MariaDB using the driver JDBC (Java Data Base Connection), I'm going to show you how I connect to the data base, how I insert, delete and update some data in my database.

## Database creation

To create the database follow these commands:

```
CREATE DATABASE jdbc_demo;
```
```
USE jdbc_demo;
```
```
CREATE TABLE tipos(
nombre VARCHAR(45),
numero INT);
```

## Maven Project

For this project I'm going to use Maven. Lets create the project using this command:

``` 
mvn archetype:generate
```
This comand start's the project with a quick start template, then ask us to select the filter:

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/FiltoPlantilla.png)

Select the number of the version: (Default 8 : latest):

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/version.png)

Name of the groupId:

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/groupId.png)

Define value for artifactId:

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/artifactId.png)

For the 'version' press enter and set the default one:

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/versionSnap.png)

In package write the same as the groupId, this is gonig to be the route of the folers:

![IMAGEN](https://github.com/FranN2442/JDBC_Connection/blob/master/images/package.png)

And the first part of the configuration for the maven project is finshed, lets see the second part.

## POM.XML configuration for JDBC

For the second part we need to configure pom.xml file of maven, editing the dependency's we can add new libraries for the project, and we need to add the dependency od MariaDB with jdbc:

```
<dependency>
   <groupId>org.mariadb.jdbc</groupId>
   <artifactId>mariadb-java-client</artifactId>
   <version>YOUR-VERSION</version>
</dependency>
```
And the SLF4J dependency:
```
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.7</version>
</dependency>
```

And...Finished!! Maven Project configuration finished

## Connecting to MariaDB

Lets create the main file named ConnectionJDBC.java, first of all set the main method:

```
//SQLException is added the most of the time's when the method probably is going to throw an exception.
public static void main(String[] args) throws SQLException {

    openDataBaseConnection(); //The method manager to starts the connection with the data base.
    closeDataBaseConnection(); //The method manager to close the connection.


}
```
This method is going to execute the other method's.

The method ___openDataBaseConnection()___ :

```
private static void openDatabaseConnection() throws SQLException{
        
        System.out.println("Connecting Database...");
        conn = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/your_db",
            "your_user", 
            "your_password");

        System.out.println("Connection valid: " + conn.isValid(5)); //Verify that the connection has been made.
    }
```

The method ___closeDatabaseConnection()___ :
```
private static void closeDatabaseConection() throws SQLException {

        System.out.println("Closing Database Connection...");
        conn.close();
        System.out.println("Connection valid: " + conn.isValid(5)); //Verify that the connection has been made.
 
    }
```

Let's add 4 more methods: ___createData___ , ___readData___ , ___deleteData___ , ___updateData___

```
public static void main(String[] args) throws SQLException {

    createData();
    readData();
    deleteData();
    uodateData();
    
```

In my case I'm going to pass different values to this method's:

```
    private static void createData(String name,int number) throws SQLException {
        
    }

    private static void updateData(String name, int number) throws SQLException {
        
    }

    private static void deleteData(String name) throws SQLException {
        
    }

```

This values depends of the number and the type of colums you have in your table. [See SQL Queries](https://github.com/FranN2442/DataBase-JDBC-Connection/blob/main/jdbc/src/main/java/fran/jdbc/ConnectionJDBC.java)
