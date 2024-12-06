package org.isep;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int ID;
    private String Name;
    private String Address;
    private String Contact;

    //CRUD = create read update delete
    private static List<Person> personList = new ArrayList<>();

    public Person(int ID, String Name, String Address, String Contact) {
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.Contact = Contact;
    }

    public int getID() { return ID; }

    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address = Address; }

    public String getContact() { return Contact; }
    public void setContact(String Contact) { this.Contact = Contact; }

    public void getInfos() {
        System.out.println("ID: " + ID);
        System.out.println("Name: " + Name);
        System.out.println("Address: " + Address);
        System.out.println("Contact: " + Contact);
    }

    // CRUD Operations
    public static void addPerson(Person p) {
        personList.add(p);
    }

    public static void modifyPerson(int ID, Person newPerson) {
        for (Person p : personList) {
            if (p.getID() == ID) {
                p.setName(newPerson.getName());
                p.setAddress(newPerson.getAddress());
                p.setContact(newPerson.getContact());
                break;
            }
        }
    }

    public static Person retrievePerson(int ID) {
        for (Person p : personList) {
            if (p.getID() == ID) {
                return p;
            }
        }
        return null;
    }

    public static void deletePerson(int ID) {
        personList.removeIf(p -> p.getID() == ID);
    }
}

