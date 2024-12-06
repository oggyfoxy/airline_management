package org.isep;

import java.util.*;

// here i created a list of methods to get nbr of flights, nbr of passengers, revenue, popular destinations etc.
public class Report {
    public static int getTotalNumberOfFlights() {
        return Flight.flightList.size();
    }

    public static int getTotalNumberOfPassengers() {
        int totalPassengers = 0;
        for (Flight flight : Flight.flightList) {
            totalPassengers += flight.getNumberOfPassengers();
        }
        return totalPassengers;
    }

    public static double getTotalRevenueGenerated() {
        double totalRevenue = 0;
        for (Flight flight : Flight.flightList) {
            totalRevenue += flight.getTotalRevenue();
        }
        return totalRevenue;
    }

    public static List<Map.Entry<String, Integer>> getMostPopularDestinations() {
        Map<String, Integer> destinationCount = new HashMap<>();
        for (Flight flight : Flight.flightList) {
            String destinationName = flight.getDestination().getName();
            destinationCount.put(destinationName, destinationCount.getOrDefault(destinationName, 0) + 1);
        }
        // get them in descending order
        List<Map.Entry<String, Integer>> sortedDestinations = new ArrayList<>(destinationCount.entrySet());
        sortedDestinations.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedDestinations;
    }

    public static void generateReport() {
        System.out.println();
        System.out.println("---Airline Report---");
        System.out.println();
        System.out.println("Total number of flights: " + getTotalNumberOfFlights());
        System.out.println("Total number of passengers carried: " + getTotalNumberOfPassengers());
        System.out.println("Total revenue generated: $" + getTotalRevenueGenerated());

        System.out.println("\nMost Popular Destinations:");
        List<Map.Entry<String, Integer>> popularDestinations = getMostPopularDestinations();
        for (Map.Entry<String, Integer> entry : popularDestinations) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " flights");
        }
    }
}
