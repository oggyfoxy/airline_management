package org.isep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aircraft {
    private String Registration;
    private String Model;
    private int Capacity;
    private List<Flight> assignedFlights;
    private static List<Aircraft> aircraftList = new ArrayList<>();

    public Aircraft(String Registration, String Model, int Capacity) {
        this.Registration = Registration;
        this.Model = Model;
        this.Capacity = Capacity;
        this.assignedFlights = new ArrayList<>();
    }

    // list of getters and setters
    public String getRegistration() {
        return Registration; }
    public void setRegistration(String Registration) {
        this.Registration = Registration; }

    public String getModel() {
        return Model; }
    public void setModel(String Model) {
        this.Model = Model; }

    public int getCapacity() { return Capacity; }
    public void setCapacity(int Capacity) { this.Capacity = Capacity; }

    // to assign flight
    public void assignFlight(Flight flight) {
        if (checkAvailability(flight)) {
            assignedFlights.add(flight);
            flight.setAircraft(this);
            System.out.println("Aircraft " + this.Registration + " assigned to flight " + flight.getFlightNumber());
        } else {
            System.out.println("Aircraft " + this.Registration + " is not available for flight " + flight.getFlightNumber());
        }
    }

    // to check availability
    public boolean checkAvailability(Flight flight) {
        for (Flight f : assignedFlights) {
            Date fDepart = f.getDepartureTime();
            Date fArrival = f.getArrivalDateTime();
            Date flightDepart = flight.getDepartureTime();
            Date flightArrival = flight.getArrivalDateTime();

            if (fDepart.before(flightArrival) && fArrival.after(flightDepart)) {
                return false; // Overlapping flights
            }
        }
        return true;
    }

    // CRUD operations
    public static void addAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

    public static void modifyAircraft(String registration, Aircraft newAircraft) {
        for (Aircraft a : aircraftList) {
            if (a.getRegistration().equals(registration)) {
                a.setModel(newAircraft.getModel());
                a.setCapacity(newAircraft.getCapacity());
                break;
            }
        }
    }

    public static Aircraft retrieveAircraft(String registration) {
        for (Aircraft a : aircraftList) {
            if (a.getRegistration().equals(registration)) {
                return a;
            }
        }
        return null;
    }

    public static void deleteAircraft(String registration) {
        aircraftList.removeIf(a -> a.getRegistration().equals(registration));
    }
}
