package tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import app.User;

public class TestUser{
	User user;
	
	@Before
	public void createUser() {
		user = new User("Test", "password", "TestName", User.Rank.HIGH);
	}
	@Test
	public void createUserTest() {
		assertEquals("Test",user.getName());
		assertEquals("password", user.getPassword());
		assertEquals("TestName", user.getFullName());
	}
	@After
	public void destroyUser() {
		user = null;
	}
	
	@Test
	public void getUserRankTest() {
		User u = new User("Test", "password", "Testname", User.Rank.HIGH);
		User.Rank r = u.getRank();
		assertEquals(r, User.Rank.HIGH);
	}
	
	@Test
	public void setUserRankTest() {
		User u = new User("Test", "password", "Testname", User.Rank.HIGH);
		u.setRank(User.Rank.LOW);
		assertEquals(User.Rank.LOW, u.getRank());
	}
}