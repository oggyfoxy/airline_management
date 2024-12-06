package org.isep;

import java.util.Date;

public class Book {
    private static int reservationCounter = 1;
    private int reservationNumber;
    private Date dateReservation;
    private String status; // if its confirmed, cancelled etc.
    private Flight flight;
    private Passenger passenger;
    private double ticketPrice; // added this attribute for ticket price

    public Book(Passenger passenger, Flight flight, double ticketPrice) {
        this.reservationNumber = reservationCounter++;
        this.dateReservation = new Date();
        this.status = "Pending";
        this.flight = flight;
        this.passenger = passenger;
        this.ticketPrice = ticketPrice;
    }

    // list of getters and setters
    public int getReservationNumber() {
        return reservationNumber;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight; }
    public void setFlight(Flight flight) {
        this.flight = flight; }

    public Passenger getPassenger() {
        return passenger; }

    public double getTicketPrice() {
        return ticketPrice; }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice; }

    // list of methods
    public void confirmReservation() {
        this.status = "Confirmed";
    }

    public void cancelReservation() {
        this.status = "Cancelled";
    }

    public void modifyReservation(Flight newFlight) {
        this.flight = newFlight;
    }
}
