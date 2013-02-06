package app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
		
	public static void main(String args[]) {
		//printCust();
		setLookAndFeel();
		new LoginWindow();
	}
	
	private static void printCust() {
		DB db = DB.getInstance();
		Hashtable<Integer,Customer>cust = db.loadCustomers();
		
		for(Customer c: cust.values()) {
			System.out.println(c);
			List<Vehicle> vList = c.getVehicles();
			for(Vehicle v: vList) {
				System.out.println("\t" + v.getRegNo());
			}
		}
	}
	
	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(
			UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	private static void consoleLogin() {
		//Just a test
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		DB db = null;
		String line = null;
		User usr = null;
		
		System.out.print("User:");
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = DB.getInstance();
		usr = db.loadUser(line);
		if(usr == null) {
			System.err.println("User do not exsist");
			System.exit(1);
		}
		System.out.print("Password:");
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!line.equals(usr.getPassword())) {
			System.err.println("Bad password");
			System.exit(1);
		}
		System.out.println("Logged in as " + usr.getFullName());
	}
}
