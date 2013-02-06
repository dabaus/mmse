package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Session;
import app.User;

public class TestSession {

	Session session;
	
	@Before
	public void setup() {
		this.session = Session.logIn("user", "password");
	}
	
	@After
	public void tearDown() throws Exception {
		session = null;
	}

	@Test
	public void testLogIn() {
		assertNull(Session.logIn("derpderp", "herpaderp"));
		assertNotNull(session);
	}

	@Test
	public void testLogOut() {
		session.logOut();
		Session session2 = Session.logIn("user", "password");
		assertNotSame(session, session2);
		assertFalse(session.isActive());
		session = session2;
	}

	@Test
	public void testGetUser() {
		User user = session.getUser();
		assertEquals("user", user.getName());
		assertEquals("password", user.getPassword());
	}


}
