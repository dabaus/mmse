package app;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Insurance {
	
	private List<Claim> claims;
	private Date validTo;
	
	public Insurance(Date validTo) {
		this.validTo = validTo;
		claims = new LinkedList<Claim>();
	}
	
	public boolean isActive() { 
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		return (validTo.compareTo(today) >= 0 ? true: false);
	}

	public void addClaim(Claim c) {
		claims.add(c);
		Collections.sort(claims);
	}
	
	public List<Claim> getClaims() {
		return claims;
	}
	
	public Date getValidTo() {
		return validTo;
	}

}
