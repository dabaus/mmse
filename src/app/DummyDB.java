package app;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class DummyDB extends DB {
	
	private Hashtable<String, User> users = new Hashtable<String, User>();
	private Hashtable<Integer, Customer> customers = new Hashtable<Integer, Customer>();
	private Hashtable<String, Vehicle> vehicles = new Hashtable<String, Vehicle>();
	
	/* Generate data */
	{
		RandData rnd = new RandData();
		
		users.put("user", new User("user", "password", "John Smith", User.Rank.LOW));
		users.put("jakob", new User("jakob", "password", "Jakob Stengard", User.Rank.HIGH));
		
		int id = 0;
		Customer c =  new Customer(id, "Ooi", "Tjan-Chao", "Address 123", "12455", "Akalla", "0701234567");
		Vehicle v = new Vehicle(c, "LOL101", 50000);
		vehicles.put("LOL101", v);
		c.addVehicle(v);
		Insurance in = new Insurance(rnd.randomDate());

		Calendar cal = Calendar.getInstance();
		
		Claim cl = new Claim(cal.getTime() , "Oois bil pajade", true);
		cl.setStatus(Claim.Status.PENDING);
		in.addClaim(cl);
		v.setInsurance(in);

		cal.roll(Calendar.DAY_OF_MONTH, -1);
		cl = new Claim(cal.getTime(), "Description", false);
		cl.setStatus(Claim.Status.ACCEPTED);
		in.addClaim(cl);
		
		v = new Vehicle(c, "HEJ007", 100000);
		vehicles.put("HEJ007", v);
		c.addVehicle(v);
		in = new Insurance(rnd.randomDate());
		cl = new Claim(cal.getTime(), "HEJ", true);
		cl.setStatus(Claim.Status.REJECTED);
		in.addClaim(cl);
		v.setInsurance(in);

		cal.roll(Calendar.DAY_OF_MONTH, -1);
		cl = new Claim(cal.getTime(), "Description", false);
		cl.setStatus(Claim.Status.ACCEPTED);
		in.addClaim(cl);
		
		customers.put(id, c);
		
		Customer c2 = new Customer( ++id, "Toni", "Minkkinen","Hejvägen 555", "99999", "Derp", "0100100100");
		Vehicle v2 = new Vehicle(c2, "TON1", 200000);
		vehicles.put("TON1", v2);
		v2.setInsurance(new Insurance(rnd.randomDate()));
		c2.addVehicle(v2);
		customers.put(id, c2);

		/*
		
		for(int i=0; i<30; i++) {
			String[] name = rand.randomName();
			Customer cust = new Customer(name[0], name[1], i);
			Vehicle vcl;
	
			int numv = rand.nextInt(100);
			if(numv < 70) { // 70%
				numv = 1;
			} else if (numv > 70 && numv < 90) { // 20 %
				numv = 2;
			} else { // 10%
				numv = 3;
			}
			for(int j=0; j<numv; j++) {
				vcl = new Vehicle(cust, rand.randomRegNo());
				cust.addVehicle(vcl);
				vehicles.put(vcl.getRegNo(), vcl);
				Calendar cal = Calendar.getInstance();
				cal.roll(Calendar.DAY_OF_MONTH, rand.nextInt(31));
				cal.roll(Calendar.MONTH, rand.nextInt(12));
				Date date = cal.getTime();
				Insurance in = new Insurance(date);
			}
			customers.put(name[1] + " " + name[0], cust);
		}*/
	}

	public User loadUser(String userName) {
		try {
			return users.get(userName).clone(); 
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void saveUser(User user) {
		User save = user.clone();
		users.put(save.getName(), save);
	}
	
	public Hashtable<Integer,Customer> loadCustomers() {
		return customers;
		/*Enumeration<Customer> e = customers.elements();
		List<Customer> list = new LinkedList<Customer>();
		// Copy objects so that changes to objects do not directly affect the "database";
		while(e.hasMoreElements()) {
			Customer c = e.nextElement();
			list.add(c.clone());
		}
		return list;*/
	}
	
	public Vehicle loadVehicle(String regNo) {
		return vehicles.get(regNo);
	}
	
	public List<Vehicle> loadVehicles() {
		List<Vehicle> list = new LinkedList<Vehicle>();
		for(Customer c: customers.values()){
			list.addAll(c.getVehicles());
		}
		return list;
	}
}
