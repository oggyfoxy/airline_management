package org.isep;
import java.util.Date;

/* main function to test final program
- didnt put all functions, just the important ones for a comprehensive end-user experience
- can add more flights, more crew members, airports etc
- book specific flights for each passengers
 */

public class Main {
    public static void main(String[] args) {
        Airport origin = new Airport("isep airport", "Paris", "Main airport in Paris");
        Airport destination1 = new Airport("Notre Dame des Champs airport", "Vavin", "airport for students");
        Airport destination2 = new Airport("Stanford airport", "Los Angeles", "Main airport for good students");

        Airport.addAirport(origin);
        Airport.addAirport(destination1);
        Airport.addAirport(destination2);

        Aircraft aircraft = new Aircraft("EW6HY", "airbus ISEP", 200);
        Aircraft.addAircraft(aircraft);

        Flight flight1 = new Flight(777, origin, destination1, new Date(), new Date());
        Flight flight2 = new Flight(303, origin, destination2, new Date(), new Date());
        Flight.addFlight(flight1);
        Flight.addFlight(flight2);

        aircraft.assignFlight(flight1);
        aircraft.assignFlight(flight2);

        AirplanePilot pilot = new AirplanePilot(1, "Raphael Slama", "10 rue Vanves", "+33 6 53 73 92 11", 100, "2019-04-12", "ATPL", 9000);
        StaffCabin crew = new StaffCabin(2, "Greg Sarto", "24 rue wowee", "+33 7 53 71 32 01", 101, "2023-16-03", "Senior Cabin Crew");

        Employee.addEmployee(pilot);
        Employee.addEmployee(crew);
        pilot.assignFlight(flight1);
        pilot.assignFlight(flight2);
        crew.assignFlight(flight1);
        crew.assignFlight(flight2);

        // Create passengers for flights
        Passenger passenger1 = new Passenger(1, "Ruben Legrandjacques", "paris 14e", "None", "2235921");
        Passenger passenger2 = new Passenger(2, "Madame Aissa", "Paris", "ISEP phone", "9384234");
        Passenger.addPassenger(passenger1);
        Passenger.addPassenger(passenger2);

        passenger1.bookFlight(flight1, 499.99); // Ticket price $499
        passenger2.bookFlight(flight2, 759.99); // Ticket price: $759
        Report.generateReport();
    }
}
