package net.aionstudios.dus.logging;

public class StandardOverride {
	
	private static ServerStream serverStream;
	private static ServerErrStream serverErrStream;
	
	public static void enableOverride(){
		serverStream = new ServerStream();
		serverErrStream = new ServerErrStream();
		System.setOut(serverStream);
		System.setErr(serverErrStream);
	}

}
