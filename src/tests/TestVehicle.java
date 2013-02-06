package tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Customer;
import app.Vehicle;

public class TestVehicle{
	Vehicle v;
	Customer c = new Customer(1, "Test", "Name", "a 1", "123", "ort", "02052500");
	
	@Before
	public void createVehicle() {
		v = new Vehicle(c , "ABC123", 12345);
	}
	
	@Test
	public void createVehicleTest() {
		assertEquals("ABC123", v.getRegNo());
		assertSame(c, v.getOwner());
		assertEquals("ABC123", v.toString());
	}
	
	@Test
	public void getPriceTest() {
		assertEquals(v.getPrice(), 12345);
	}
	
	@After
	public void destroyVehicle() {
		v = null;
	}
}