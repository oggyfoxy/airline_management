// test programs to check connection w mysql

package org.isep;
import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/airline_db";
        String username = "root";
        String password = "password"; // didnt put mine here for security reasons

        try {
            // connection
            System.out.println("Testing database connection...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");

            // create a test table
            System.out.println("\nTesting table creation...");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS test_table (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(50))");
            System.out.println("test table created successfully");

            // test 3: insert data
            System.out.println("\nTesting data insertion...");
            stmt.execute("INSERT INTO test_table (id, name) VALUES (1, 'Test Flight')");
            System.out.println("Test data inserted successfully!");

            // test 4: Query data
            System.out.println("\ntesting data retrieval...");
            ResultSet rs = stmt.executeQuery("SELECT * FROM test_table");
            while (rs.next()) {
                System.out.println("Retrieved data: ID=" + rs.getInt("id") +
                        ", Name=" + rs.getString("name"));
            }

            // clean up
            System.out.println("\nCleaning up...");
            stmt.execute("DROP TABLE test_table");
            System.out.println("Test table removed.");
            conn.close();
            System.out.println("\nAll tests completed successfully");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        }
    }
}