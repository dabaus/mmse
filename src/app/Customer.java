package app;

import java.util.LinkedList;
import java.util.List;

public class Customer {
	
	private String lastName;
	private String firstName;
	private int id;
	private List<Vehicle> vehicles = new LinkedList<Vehicle>();
	private String address;
	private String zipCode;
	private String city;
	private String telNr;

	public Customer(int id, String firstName, String lastName, String address, String zipCode, String ort, String telNr) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.telNr = telNr;
	}
	
	private Customer(String firstName, String lastName, int id, List<Vehicle> vehicles) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		this.vehicles = vehicles;
	}
	
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public int getId() {
		return id;
	}

	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}
	
	
	protected Customer clone() {
		return new Customer(firstName, lastName, id, vehicles);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPostNr() {
		return zipCode;
	}

	public String getOrt() {
		return city;
	}

	public String getTelNr() {
		return telNr;
	}
}
