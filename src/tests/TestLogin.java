package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import app.LoginWindow;
import app.Session;

public class TestLogin {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testLogin() {
		try {
			LoginWindow l = new LoginWindow();
			Thread.sleep(1000);
			l.getNameField().setText("jakob");
			l.getPswField().setText("password");
			assertEquals("jakob", l.getNameField().getText());
			assertEquals("password", l.getPswField().getText());
			Thread.sleep(2000);
			l.getLoginButton().doClick();
			Session s = Session.getInstance();
			assertTrue(s.isActive());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Logged in successfully");
	}
}