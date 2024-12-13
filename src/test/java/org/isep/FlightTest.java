package org.isep;

import org.junit.jupiter.api.*;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        Airport origin = new Airport("Test Origin", "Paris", "Test Airport");
        Airport destination = new Airport("Test Destination", "London", "Test Airport");
        flight = new Flight(999, origin, destination, new Date(), new Date());
        passenger = new Passenger(1, "Test Passenger", "Test Address", "Test Contact", "TEST32313");
    }

    @Test
    void testFlightCreation() {
        assertNotNull(flight);
        assertEquals(999, flight.getFlightNumber());
        assertEquals("Scheduled", flight.getStatus());
    }
    @Test
    void testPassengerBooking() {
        passenger.bookFlight(flight, 100.0);
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(100.0, flight.getTotalRevenue(), 0.01);
    }
    @Test
    void testFlightStatusChange() {
        flight.cancelFlight();
        assertEquals("Cancelled", flight.getStatus());
    }
    @Test
    void testFlightModification() {
        Date newDeparture = new Date();
        Date newArrival = new Date();
        flight.modifyFlight(newDeparture, newArrival);
        assertEquals(newDeparture, flight.getDepartureTime());
        assertEquals(newArrival, flight.getArrivalDateTime());
    }
}
