package app;

public class Debug {
	static final boolean DEBUG_ENABLED = true;
	
	public static void println(String text) {
		if(DEBUG_ENABLED) 
			System.err.println("Debug: " + text);
	}

}
