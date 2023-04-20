package ejercicio.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionJDBC {

    
    private static Connection conn;
    public static void main(String[] args) throws SQLException {
        
        try{
            openDatabaseConnection();
            // createData("fran", 20);
            // updateData("fran", 15);
            // deleteData("fran");
            readData();

        } finally {

            closeDatabaseConection();
        }

    }

    private static void openDatabaseConnection() throws SQLException{
        
        System.out.println("Connecting Database...");
        conn = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/jdbc_demo",
            "root", 
            "20Fran04.");

        System.out.println("Connection valid: " + conn.isValid(5));
    }


    private static void closeDatabaseConection() throws SQLException {

        System.out.println("Closing Database Connection...");
        if(conn != null){

            conn.close();
            System.out.println("Connection valid: " + conn.isValid(5));

        }
 
    }

    private static void createData(String name,int number) throws SQLException {
        System.out.println("Creating data...");
        int rowsInserted;
        try(PreparedStatement statement =conn.prepareStatement("""

        INSERT INTO tipos(nombre,numero)
        VALUES (?,?);
            
            """)){

            statement.setString(1, name);
            statement.setInt(2, number);
            rowsInserted = statement.executeUpdate();
            statement.close();
        }
        System.out.println("Rows Inserted " + rowsInserted);
    }

    private static void updateData(String name, int number) throws SQLException {
        
        try (PreparedStatement statement = conn.prepareStatement("""
                UPDATE tipos
                SET numero = ?
                WHERE nombre = ?
            """)) {
        statement.setInt(1, number);
        statement.setString(2, name);
        int rowsUpdated = statement.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
            }
    }

    private static void deleteData(String name) throws SQLException{
        
        try (PreparedStatement statement = conn.prepareStatement("""
                DELETE FROM tipos
                WHERE nombre LIKE ?
            """)) {
        statement.setString(1, name);
        int rowsDeleted = statement.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted); }

    }

    private static void readData() throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement("""
                SELECT nombre, numero
                FROM tipos
                ORDER BY numero DESC
            """)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            boolean empty = true;
            while (resultSet.next()) {
                empty = false;
                String nombre = resultSet.getString("nombre");
                int numero = resultSet.getInt("numero");
                System.out.println("\t> " + nombre + ": " + numero);
            }
            if (empty) {
                System.out.println("\t (no data)");
            }
        }
    }

        
    }


}
