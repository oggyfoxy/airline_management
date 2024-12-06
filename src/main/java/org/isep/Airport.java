package org.isep;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String Name;
    private String City;
    private String Description;
    private List<Flight> flights;

    private static List<Airport> airportList = new ArrayList<>();
    public Airport(String Name, String City, String Description) {
        this.Name = Name;
        this.City = City;
        this.Description = Description;
        this.flights = new ArrayList<>();
    }

    // list of getters and Setters
    public String getName() {
        return Name; }
    public void setName(String Name) {
        this.Name = Name; }
    public String getCity() { return City; }
    public void setCity(String City) { this.City = City; }

    public String getDescription() { return Description; }
    public void setDescription(String Description) { this.Description = Description; }




    public List<Flight> getFlights() { return flights; }

    public void assignFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Flight " + flight.getFlightNumber() + " assigned to airport " + this.Name);
    }

    // CRUD Operations
    public static void addAirport(Airport airport) {
        airportList.add(airport);
    }

    public static void modifyAirport(String name, Airport newAirport) {
        for (Airport a : airportList) {
            if (a.getName().equals(name)) {
                a.setCity(newAirport.getCity());
                a.setDescription(newAirport.getDescription());
                break;
            }
        }
    }

    public static Airport retrieveAirport(String name) {
        for (Airport a : airportList) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public static void deleteAirport(String name) {
        airportList.removeIf(a -> a.getName().equals(name));
    }
}
