package tests;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import app.MainWindow;
import app.Session;

public class TestClaimWindow {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testClaimWindow() {
		try {

			Session.logIn("jakob", "password"); 
		
			MainWindow main = new MainWindow();
		
			Thread.sleep(1000);
			
			System.out.println("Skriver in 1");
			main.getIdSearchField().setText("1");
			Thread.sleep(1000);
			System.out.println("Trycker enter");
			//main.getIdSearchField().grabFocus();
			main.getIdSearchField().postActionEvent();
			assertEquals(main.getIdSearchField().getText(), "1");
			
			Thread.sleep(1000);
			
			assertEquals(main.getTable().getValueAt(main.getTable().getSelectedRow(), 0), 1);
			System.out.println("OK");
			Thread.sleep(2000);
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}