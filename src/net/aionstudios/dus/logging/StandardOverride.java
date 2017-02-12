package net.aionstudios.dus.logging;

public class StandardOverride {
	
	private static DustStream serverStream;
	private static DustErrStream serverErrStream;
	
	public static void enableOverride(){
		serverStream = new DustStream();
		serverErrStream = new DustErrStream();
		System.setOut(serverStream);
		System.setErr(serverErrStream);
	}

}
