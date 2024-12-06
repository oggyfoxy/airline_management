package org.isep;

public class StaffCabin extends Employee {
    private String qualification;

    public StaffCabin(int ID, String Name, String Address, String Contact, int NumberEmp, String HiringDate, String qualification) {
        super(ID, Name, Address, Contact, NumberEmp, HiringDate);
        this.qualification = qualification;
    } 
    public String getQualification() {
        return qualification; }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String getRole() {
        return "Staff cabin";
    }

    // to assign flight
    public void assignFlight(Flight flight) {
        flight.addCabinCrew(this);
        System.out.println("Staff Cabin " + this.getName() + " assigned to flight " + flight.getFlightNumber());
    }

    public Flight obtainVol(int flightNumber) {
        return Flight.retrieveFlight(flightNumber);
    }
}
