package org.isep;

public class AirplanePilot extends Employee {
    private String Licence;
    private int flightHours;

    public AirplanePilot(int ID, String Name, String Address, String Contact, int NumberEmp, String HiringDate, String Licence, int flightHours) {
        super(ID, Name, Address, Contact, NumberEmp, HiringDate);
        this.Licence = Licence;
        this.flightHours = flightHours;
    }

    public String getLicence() { return Licence; }
    public void setLicence(String Licence) { this.Licence = Licence; }

    public int getFlightHours() {
        return flightHours;
    }
    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    @Override
    public String getRole() {
        return "Airplane Pilot";
    }

    // method to assign the flight
    public void assignFlight(Flight flight) {
        flight.setPilot(this);
        System.out.println("Pilot " + this.getName() + " assigned to flight " + flight.getFlightNumber());
    }
    // and get the flight
    public Flight obtainVol(int flightNumber) {
        return Flight.retrieveFlight(flightNumber);
    }
}
