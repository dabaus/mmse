package tests;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import app.CreateClaimWindow;
import app.MainWindow;
import app.Session;

public class TestClaimWindow {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testClaimWindow() {
		try {
			int time = 500;
			Session.logIn("jakob", "password"); 
			MainWindow main = new MainWindow();
			Thread.sleep(time);
			System.out.println("Skriver in 1");
			main.getIdSearchField().setText("1");
			Thread.sleep(time);
			System.out.println("Trycker enter");
			//main.getIdSearchField().grabFocus();
			main.getIdSearchField().postActionEvent();
			assertEquals(main.getIdSearchField().getText(), "1");
			Thread.sleep(time);
			assertEquals(main.getTable().getValueAt(main.getTable().getSelectedRow(), 0), 1);
			System.out.println("OK");
			Thread.sleep(time);
			main.getvList().setSelectedIndex(0);
			Thread.sleep(time);
			main.getClaimsPane().getNewClaimButton().doClick();
			Thread.sleep(time);
			CreateClaimWindow claimWindow = main.getClaimsPane().getClaimWindow();
			claimWindow.getDescription().setText("Crashed car into a tree");
			Thread.sleep(time);
			claimWindow.getSubmitButton().doClick();
			Thread.sleep(time);
			main.getClaimList().setSelectedIndex(0);
			Thread.sleep(5*time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}