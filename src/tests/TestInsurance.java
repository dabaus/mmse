package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import app.Claim;
import app.Insurance;

public class TestInsurance {

	Insurance i;
	Calendar cal = Calendar.getInstance();
	Date time;
	
	@Before
	public void testInsurance() {
		cal.roll(Calendar.DATE, 1);
		time = cal.getTime();

		i = new Insurance(time);		
	}

	@Test
	public void testIsActive() {
		assertTrue(i.isActive());
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DATE, -1);
		Insurance in =  new Insurance(cal.getTime());
		assertFalse(in.isActive());
		
	}

	@Test
	public void testAddClaim() {
		Claim c1 = new Claim(Calendar.getInstance().getTime(), "Description", true);
		i.addClaim(c1);
		assertSame(c1, i.getClaims().get(0));
	}

	@Test
	public void testGetClaims() {
		Claim c2 = new Claim(Calendar.getInstance().getTime(), "Description", true);
		i.addClaim(c2);
		assertTrue(i.getClaims().contains(c2));
	}
	
	@Test
	public void testGetValidTo() {
		Date d = i.getValidTo();
		assertEquals(d, time);
	}

}
