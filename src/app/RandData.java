package app;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandData {
	private Random rand = new Random(1234123123);
	private String[] sureNames = {"Northwood", 
		  "Smith", 
		  "Johnson", 
		  "Williams", 
		  "Brown", 
		  "Miller",
		  "Davis",
		  "García",
		  "Rodríguez",
		  "Wilson",
		  "Martínez",
		  "López",
		  "Thomas",
		  "Anderson",
		  "Taylor",
		  "Lee",
		  "White",
		  "Moore",
		  "Martin",
		  "Jackson",
		  "Harris",
		  "Clark",
		  "Lewis",
		  "Robinson"};
		
	private String givenNames[] = {"Jacob",
		  "Ethan",
		  "Michael",
		  "Jayden",
		  "William",
		  "Alexander",
		  "Noah",
		  "Daniel",
		  "Lucas",
		  "Isabella",
		  "Sophia",
		  "Emma",
		  "Olivia",
		  "Ava",
		  "Emily",
		  "Madison",
		  "Chloe",
		  "Mia"};
	private String letters[] = {
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
	 		"Z"};

	public String[] randomName() {
		String name[] = new String[2];
		name[0] = givenNames[rand.nextInt(givenNames.length)];
		name[1] = sureNames[rand.nextInt(sureNames.length)];
		return name;
	}
	public String randomRegNo() {
		String no = Integer.toString(rand.nextInt(9) + 1) +
					Integer.toString(rand.nextInt(9) + 1) +
					Integer.toString(rand.nextInt(9) + 1);
 		no += randomLetter() + randomLetter() + randomLetter();
		return no;
	}
	
	public int nextInt() {
		return rand.nextInt();
	}

	public int nextInt(int n) {
		return rand.nextInt(n);
	}
	
	private String randomLetter() {
		return letters[rand.nextInt(letters.length)];
	}
	
	public Date randomDate() {
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.MONTH, rand.nextInt(12));
		cal.roll(Calendar.DAY_OF_MONTH, rand.nextInt(31));
		return cal.getTime();
		
	}
}
