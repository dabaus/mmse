package app;

public class User {
	
	public enum Rank {
		HIGH,
		LOW;
	}

	private String name;
	private String password;
	private String fullName;
	private Rank rank;
	
	public User(String name, 
				String password,
				String fullName, Rank rank) {
		this.setName(name);
		this.setPassword(password);
		this.setFullName(fullName);
		this.setRank(rank);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	private void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}
	
	protected User clone() {
		return new User(this.name, this.password, this.fullName, this.rank);
	}

	public User.Rank getRank() {
		return this.rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
}

