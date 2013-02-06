package app;


public class Session {
	
	private static Session instance = null;
	
	private User user;
	private boolean active;

	public Session(User user) {
		this.user = user;
		this.active = true;
	}
	
	public static Session logIn(String username, String password) {
		User user;
	
		DB db = DB.getInstance();
		user = db.loadUser(username);
		
		//Dummy password check.
		if(user == null || !(user.getPassword().equals(password))) {
			return null;
		} 
			
		if(instance != null) {
			System.err.println("Warning: User " + instance.user.getName() + " was logged out.");	
		}
		instance = new Session(user);
		Debug.println("Login ok");
		return instance;
	}
	
	public void logOut() {
		//TODO save sesssion data.
		instance.active = false;
		instance = null;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public static Session getInstance() {
		return instance;
	}
}
