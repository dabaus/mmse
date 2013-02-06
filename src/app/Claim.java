package app;

import java.util.Date;

public class Claim implements Comparable<Claim>{

	public enum Status{
		ACCEPTED,
		REJECTED,
		PENDING;
		
		public String toString() {
			switch(this) {
			case ACCEPTED:
				return "Accepted";
			case REJECTED:
				return "Rejected";
			case PENDING:
				return "Pending";
			default:
				return null;
			}
		}
	}
	
	private Status status;
	private Date fileDate;
	private String description;
	private boolean complex;
	
	public Claim(Date fileDate, String description, boolean complex) {
		this.fileDate = fileDate;
		this.setDescription(description);
		this.complex = complex;
		this.status = Claim.Status.PENDING;
	}
	
	public boolean isComplex(){
		return complex;		
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String toString() {
		return status.toString();
	}
	
	public Date getFileDate() {
		return fileDate;
	}

	@Override
	public int compareTo(Claim c) {
		return c.fileDate.compareTo(this.fileDate);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean accept() {
		if(this.status == Claim.Status.PENDING){
			if(!complex){
				this.status = Claim.Status.ACCEPTED;
				return true;
			} else {
				Session s = Session.getInstance();
				User u = s.getUser();
				if(u.getRank() == User.Rank.HIGH) {
					this.status = Claim.Status.ACCEPTED;
					return true;
				}
			}
		}
		return false;
	}

	public boolean reject() {
		if(this.status == Claim.Status.PENDING){
			if(!complex){
				this.status = Claim.Status.REJECTED;
				return true;
			} else {
				Session s = Session.getInstance();
				User u = s.getUser();
				if(u.getRank() == User.Rank.HIGH) {
					this.status = Claim.Status.REJECTED;
					return true;
				}
			}
		}
		return false;		
	}
}

