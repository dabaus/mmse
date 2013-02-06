package app;

public class Vehicle {
	
	private Customer owner;
	private String regNo;
	private Insurance insurance = null;
	private Integer price;

	public Vehicle(Customer owner, String regNo, Integer price) {
		this.owner = owner;
		this.regNo = regNo;
		this.price = price;
	}

	public String getRegNo() {
		return regNo;
	}
	
	public String toString() {
		return getRegNo();
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Customer getOwner() {
		return owner;
	}
	
	public int getPrice() {
		return price;
	}
}
