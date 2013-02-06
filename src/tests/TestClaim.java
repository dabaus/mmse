package tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import app.Claim;
import app.Session;
import app.User;

public class TestClaim {

	Claim c;
	
	@Before
	public void createClaim() {
		c = new Claim(Calendar.getInstance().getTime(), "Description", true);
	}

	@Test
	public void testClaim() {
		assertEquals(c.getStatus(), null);
	}

	@Test
	public void testSetStatus() {
		c.setStatus(Claim.Status.ACCEPTED);
		assertEquals(c.getStatus(), Claim.Status.ACCEPTED);
		c.setStatus(Claim.Status.PENDING);
		assertEquals(c.getStatus(), Claim.Status.PENDING);
		c.setStatus(Claim.Status.REJECTED);
		assertEquals(c.getStatus(), Claim.Status.REJECTED);
	}

	@Test
	public void testToString() {
		c.setStatus(Claim.Status.ACCEPTED);
		assertEquals(c.toString(), "Accepted");
	}

	@Test
	public void testCompareTo() {
		Calendar cal = Calendar.getInstance();
		Claim c2 = new Claim(cal.getTime(), "Description", true);
		assertEquals(c.compareTo(c2), 0);
		cal.roll(Calendar.MONTH, 1);
		c2 = new Claim(cal.getTime(), "Description", true);
		assertTrue(c.compareTo(c2) > 0);
		cal.roll(Calendar.MONTH, -2);
		c2 = new Claim(cal.getTime(), "Description", true);
		assertTrue(c.compareTo(c2) < 0);
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(c.getDescription(), "Description");
	}	
	
	@Test
	public void testAccept() {
		Claim c = new Claim(Calendar.getInstance().getTime(), "Description", true);
		c.setStatus(Claim.Status.PENDING);
		Session s = Session.logIn("user", "password");
		assertFalse(c.accept());
		assertSame(Claim.Status.PENDING, c.getStatus());
		s.logOut();
		Session s2 = Session.logIn("jakob", "password");
		assertTrue(c.accept());
		assertSame(Claim.Status.ACCEPTED, c.getStatus());
		s2.logOut();
		Session s3 = Session.logIn("user", "password");
		assertFalse(c.accept());
	}
	
	@Test
	public void testReject() {
		Claim c = new Claim(Calendar.getInstance().getTime(), "Description", true);
		c.setStatus(Claim.Status.PENDING);
		Session s = Session.logIn("user", "password");
		assertFalse(c.reject());
		assertSame(Claim.Status.PENDING, c.getStatus());
		s.logOut();
		Session s2 = Session.logIn("jakob", "password");
		assertTrue(c.reject());
		assertSame(Claim.Status.REJECTED, c.getStatus());
		s2.logOut();
		Session s3 = Session.logIn("user", "password");
		assertFalse(c.reject());
	}
	
	@Test
	public void testIsComplex() {
		Claim c = new Claim(Calendar.getInstance().getTime(), "Description", true);
		assertEquals(c.isComplex(), true);
		Claim c2 = new Claim(Calendar.getInstance().getTime(), "Description", false);
		assertEquals(c2.isComplex(), false);
	}
}