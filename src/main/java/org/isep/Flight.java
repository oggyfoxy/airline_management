package org.isep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {
    private int flightNumber;
    private Airport Origin;
    private Airport Destination;
    private Date DepartureTime;
    private Date ArrivalDateTime;
    private String Status;
    private AirplanePilot pilot;
    private List<StaffCabin> cabinCrew;
    private List<Passenger> passengers;
    private Aircraft aircraft;

    private List<Book> reservations;
    static List<Flight> flightList = new ArrayList<>();

    public Flight(int flightNumber, Airport Origin, Airport Destination, Date DepartureTime, Date ArrivalDateTime) {
        this.flightNumber = flightNumber;
        this.Origin = Origin;
        this.Destination = Destination;
        this.DepartureTime = DepartureTime;
        this.ArrivalDateTime = ArrivalDateTime;
        this.Status = "Scheduled";
        this.cabinCrew = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // list of getters and setters
    public int getFlightNumber() { return flightNumber; }
    public void setFlightNumber(int flightNumber) { this.flightNumber = flightNumber; }

    public Airport getOrigin() { return Origin; }
    public void setOrigin(Airport Origin) { this.Origin = Origin; }

    public Airport getDestination() { return Destination; }
    public void setDestination(Airport Destination) { this.Destination = Destination; }

    public Date getDepartureTime() { return DepartureTime; }
    public void setDepartureTime(Date DepartureTime) { this.DepartureTime = DepartureTime; }

    public Date getArrivalDateTime() { return ArrivalDateTime; }
    public void setArrivalDateTime(Date ArrivalDateTime) { this.ArrivalDateTime = ArrivalDateTime; }

    public String getStatus() { return Status; }
    public void setStatus(String Status) { this.Status = Status; }

    public AirplanePilot getPilot() { return pilot; }
    public void setPilot(AirplanePilot pilot) { this.pilot = pilot; }

    public List<StaffCabin> getCabinCrew() { return cabinCrew; }
    public void addCabinCrew(StaffCabin staff) { this.cabinCrew.add(staff); }

    public List<Passenger> getPassengers() { return passengers; }
    public void addPassenger(Passenger passenger) { this.passengers.add(passenger); }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public void addReservation(Book reservation) {
        this.reservations.add(reservation);
    }


    public int getNumberOfPassengers() {
        return passengers.size();
    }

    // total rev of flight
    public double getTotalRevenue() {
        double revenue = 0;
        for (Book reservation : reservations) {
            if ("Confirmed".equals(reservation.getStatus())) {
                revenue += reservation.getTicketPrice();
            }
        }
        return revenue;
    }

    public void planFlight() {
        this.Status = "Scheduled";
    }
    public void cancelFlight() {
        this.Status = "Cancelled";
    }
    public void modifyFlight(Date newDepartureTime, Date newArrivalTime) {
        this.DepartureTime = newDepartureTime;
        this.ArrivalDateTime = newArrivalTime;

    }



    public void listingPassenger() {
        System.out.println("Passengers on flight " + flightNumber + ":");
        for (Passenger p : passengers) {
            System.out.println(p.getName());
        }
    }

    // CRUD Operations (again)
    public static void addFlight(Flight flight) {
        flightList.add(flight);
    }

    public static void modifyFlight(int flightNumber, Flight newFlight) {
        for (Flight f : flightList) {
            if (f.getFlightNumber() == flightNumber) {
                f.setOrigin(newFlight.getOrigin());
                f.setDestination(newFlight.getDestination());
                f.setDepartureTime(newFlight.getDepartureTime());
                f.setArrivalDateTime(newFlight.getArrivalDateTime());
                f.setStatus(newFlight.getStatus());
                break;
            }
        }
    }

    public static Flight retrieveFlight(int flightNumber) {
        for (Flight f : flightList) {
            if (f.getFlightNumber() == flightNumber) {
                return f;
            }
        }
        return null;
    }

    public static void deleteFlight(int flightNumber) {
        flightList.removeIf(f -> f.getFlightNumber() == flightNumber);
    }
}
