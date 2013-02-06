package app;

import java.util.Hashtable;
import java.util.List;

public abstract class DB {
	
	private static DB instance = null;
	
	public static DB getInstance() {
		if(instance == null) {
			instance = new DummyDB();
		}
		return instance;
	}
	
	public abstract User loadUser(String name);
	
	public abstract List<Vehicle> loadVehicles();
	
	public abstract Vehicle loadVehicle(String RegNo);
	
	public abstract void saveUser(User user);
	
	public abstract Hashtable<Integer,Customer> loadCustomers();
}
