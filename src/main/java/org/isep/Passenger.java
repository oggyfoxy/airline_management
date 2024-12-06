package org.isep;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Person {
    private String Passport;
    private List<Book> reservations;
    private static List<Passenger> passengerList = new ArrayList<>();

    public Passenger(int ID, String Name, String Address, String Contact, String Passport) {
        super(ID, Name, Address, Contact);
        this.Passport = Passport;
        this.reservations = new ArrayList<>();
    }

    public String getPassport() { return Passport; }
    public void setPassport(String Passport) { this.Passport = Passport; }

    // bookflight: book a flight
    public void bookFlight(Flight flight, double ticketPrice) {
        Book reservation = new Book(this, flight, ticketPrice);
        reservation.confirmReservation();
        reservations.add(reservation);
        flight.addPassenger(this);
        flight.addReservation(reservation);
        System.out.println("reservation made for flihgt " + flight.getFlightNumber());
    }


    // cancelBook: cancel a flight reservation
    public void cancelBook(int reservationNumber) {
        reservations.removeIf(reservation -> {
            if (reservation.getReservationNumber() == reservationNumber) {
                reservation.cancelReservation();
                System.out.println("Reservation " + reservationNumber + " cancelled");
                return true;
            }
            return false;
        });
    }



    public List<Book> getBooks() {
        return reservations;
    }

    // CRUD Operations
    public static void addPassenger(Passenger p) {
        passengerList.add(p);
    }

    public static void modifyPassenger(int ID, Passenger newPassenger) {
        for (Passenger p : passengerList) {
            if (p.getID() == ID) {
                p.setName(newPassenger.getName());
                p.setAddress(newPassenger.getAddress());
                p.setContact(newPassenger.getContact());
                p.setPassport(newPassenger.getPassport());
                break;
            }
        }
    }

    public static Passenger retrievePassenger(int ID) {
        for (Passenger p : passengerList) {
            if (p.getID() == ID) {
                return p;
            }
        }
        return null;
    }

    public static void deletePassenger(int ID) {
        passengerList.removeIf(p -> p.getID() == ID);
    }
}
