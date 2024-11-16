package ToDoAppHibernate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    // MySQL Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ToDoAppDB";
    private static final String DB_USER = "root";  
    private static final String DB_PASSWORD = "root@123";
    
    // The Singleton instance
    private static ToDoList instance;

    // Private constructor to prevent instantiation
    private ToDoList() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get the singleton instance
    public static ToDoList getInstance() {
        if (instance == null) {
            instance = new ToDoList();
        }
        return instance;
    }

    // Method to add a new to-do item to the database
    public void addItem(String description) {
        String query = "INSERT INTO ToDoItem (description) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, description);
            System.out.println("Executing query: " + stmt);
            stmt.executeUpdate();
            System.out.println("Item added: " + description);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a to-do item by its index (ID in the DB)
    public void deleteItem(int id) {
        String query = "DELETE FROM ToDoItem WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item with ID " + id + " removed.");
            } else {
                System.out.println("No item found with ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all to-do items from the database
    public List<ToDoItem> getItems() {
        List<ToDoItem> items = new ArrayList<>();
        String query = "SELECT * FROM ToDoItem";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                items.add(new ToDoItem(id, description));
                System.out.println("Fetched item: " + id + " - " + description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Method to clear all to-do items (not usually recommended in web apps)
    public void clearItems() {
        String query = "DELETE FROM ToDoItem";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " items cleared.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}