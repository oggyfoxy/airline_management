// FileHandler.java
package org.isep;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class FileHandler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Save flights to CSV
    public static void saveFlightsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("FlightNumber,Origin,Destination,DepartureTime,ArrivalTime,Status\n");
            for (Flight flight : Flight.flightList) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s\n",
                        flight.getFlightNumber(),
                        flight.getOrigin().getName(),
                        flight.getDestination().getName(),
                        dateFormat.format(flight.getDepartureTime()),
                        dateFormat.format(flight.getArrivalDateTime()),
                        flight.getStatus()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving flights: " + e.getMessage());
        }
    }

    // Load flights from CSV
    public static void loadFlightsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int flightNumber = Integer.parseInt(parts[0]);
                Airport origin = Airport.retrieveAirport(parts[1]);
                Airport destination = Airport.retrieveAirport(parts[2]);
                Date departureTime = dateFormat.parse(parts[3]);
                Date arrivalTime = dateFormat.parse(parts[4]);

                Flight flight = new Flight(flightNumber, origin, destination, departureTime, arrivalTime);
                flight.setStatus(parts[5]);
                Flight.addFlight(flight);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error loading flights: " + e.getMessage());
        }
    }

    // Save passengers to CSV
    public static void savePassengersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Name,Address,Contact,Passport\n");
            for (Passenger passenger : Passenger.getPassengerList()) {
                writer.write(String.format("%d,%s,%s,%s,%s\n",
                        passenger.getID(),
                        passenger.getName(),
                        passenger.getAddress(),
                        passenger.getContact(),
                        passenger.getPassport()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error saving passengers: " + e.getMessage());
        }
    }
}
