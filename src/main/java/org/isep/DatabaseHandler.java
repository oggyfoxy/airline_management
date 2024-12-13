package org.isep;

import java.sql.*;
import java.util.Date;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // same reason here

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // create tables
    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            // create Airports table
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS airports (" +
                            "name VARCHAR(100) PRIMARY KEY," +
                            "city VARCHAR(100)," +
                            "description TEXT)"
            );
            // create Flights table
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS flights (" +
                            "flight_number INT PRIMARY KEY," +
                            "origin VARCHAR(100)," +
                            "destination VARCHAR(100)," +
                            "departure_time DATETIME," +
                            "arrival_time DATETIME," +
                            "status VARCHAR(50)," +
                            "FOREIGN KEY (origin) REFERENCES airports(name)," +
                            "FOREIGN KEY (destination) REFERENCES airports(name))"
            );

            // create Passengers table
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS passengers (" +
                            "id INT PRIMARY KEY," +
                            "name VARCHAR(100)," +
                            "address TEXT," +
                            "contact VARCHAR(50)," +
                            "passport VARCHAR(50))"
            );

            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    // to save to db
    public static void saveAirport(Airport airport) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO airports (name, city, description) VALUES (?, ?, ?)"
            );
            stmt.setString(1, airport.getName());
            stmt.setString(2, airport.getCity());
            stmt.setString(3, airport.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving airport: " + e.getMessage());
        }
    }

    // save flight to database
    public static void saveFlight(Flight flight) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO flights (flight_number, origin, destination, departure_time, arrival_time, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, flight.getFlightNumber());
            stmt.setString(2, flight.getOrigin().getName());
            stmt.setString(3, flight.getDestination().getName());
            stmt.setTimestamp(4, new Timestamp(flight.getDepartureTime().getTime()));
            stmt.setTimestamp(5, new Timestamp(flight.getArrivalDateTime().getTime()));
            stmt.setString(6, flight.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving flight: " + e.getMessage());
        }
    }

    // load flights from database
    public static void loadFlights() {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM flights");

            while (rs.next()) {
                Airport origin = Airport.retrieveAirport(rs.getString("origin"));
                Airport destination = Airport.retrieveAirport(rs.getString("destination"));

                Flight flight = new Flight(
                        rs.getInt("flight_number"),
                        origin,
                        destination,
                        new Date(rs.getTimestamp("departure_time").getTime()),
                        new Date(rs.getTimestamp("arrival_time").getTime())
                );
                flight.setStatus(rs.getString("status"));
                Flight.addFlight(flight);
            }
        } catch (SQLException e) {
            System.err.println("Error loading flights: " + e.getMessage());
        }
    }
}