package org.isep;
// testing file handling
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void testSaveAndLoadFlights() {
        Airport origin = new Airport("Test Origin", "Paris", "Test");
        Airport destination = new Airport("Test Destination", "London", "Test");
        Flight testFlight = new Flight(888, origin, destination, new Date(), new Date());
        Flight.addFlight(testFlight);

        String testFile = "flights.csv";
        FileHandler.saveFlightsToFile(testFile);
        assertTrue(new File(testFile).exists());

        Flight.flightList.clear();
        FileHandler.loadFlightsFromFile(testFile);

        assertFalse(Flight.flightList.isEmpty());
        Flight loadedFlight = Flight.flightList.get(0);
        assertEquals(888, loadedFlight.getFlightNumber());

        new File(testFile).delete();
    }
}