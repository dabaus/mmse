package app;

import java.util.LinkedList;
import java.util.List;

public class Customer {
	
	private String lastName;
	private String firstName;
	private int id;
	private List<Vehicle> vehicles = new LinkedList<Vehicle>();
	private String address;
	private String postNr;
	private String ort;
	private String telNr;

	public Customer(int id, String firstName, String lastName, String address, String postNr, String ort, String telNr) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		this.address = address;
		this.postNr = postNr;
		this.ort = ort;
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
		return postNr;
	}

	public String getOrt() {
		return ort;
	}

	public String getTelNr() {
		return telNr;
	}
}
