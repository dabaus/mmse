package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import app.Customer;
import app.Vehicle;

public class TestCustomer {
	Customer c;
	Vehicle v;
	
	@Before
	public void createCusotmer() {
		c = new Customer(1, "firstName", "lastName", "Adress 123", "12345", "Ort", "0701234567");
		v = new Vehicle(c, "ABC123", 12345);
		c.addVehicle(v);
	}
	
	@Test
	public void createCustomerTest() {
		assertEquals("firstName", c.getFirstName());
		assertEquals("lastName", c.getLastName());
		assertEquals(1, c.getId());
		assertEquals("firstName lastName", c.toString());
		assertSame(v, c.getVehicles().get(0));
		assertEquals("Adress 123", c.getAddress());
		assertEquals("12345", c.getPostNr());
		assertEquals("Ort", c.getOrt());
		assertEquals("0701234567", c.getTelNr());
	}

	@After
	public void destroyCustomer() {
		c = null;
	}
}