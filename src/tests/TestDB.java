package tests;

import static org.junit.Assert.*;
import app.*;

import java.util.Hashtable;
import java.util.List;

import org.junit.Test;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;

public class TestDB {
	
	@Test
	public void checkUser() {
		DB db = DB.getInstance();
		User user = db.loadUser("user");
		assertTrue(user.getName().equals("user"));
		user = db.loadUser("herpaderp");
		assertNull(user);
	}
	
	@Test
	public void checkCust() {
		DB db = DB.getInstance();
		Hashtable<Integer,Customer> cust = db.loadCustomers();
		for(Customer c: cust.values()) {
			String name = c.getLastName();
			assertNotNull(name);
			assertTrue(!name.equals(""));
		}
	}
	
//	@Test
//	public void saveCust() {
//		DB db = DB.getInstance();
//		Customer cst = new Customer("Test", "User", 999999);
////		db.saveCustomer(cst);
//		Hashtable<Integer,Customer> cust = db.loadCustomers();
//		Customer c = cust.get(999999);
//		assertNotNull(c);
//		assertEquals("Test", c.getFirstName());
//		assertEquals("User", c.getLastName());
//	}
}
