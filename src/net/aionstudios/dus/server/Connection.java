package net.aionstudios.dus.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Connection {
	
	private Socket connection;
	private DataInputStream conIn;
	private DataOutputStream conOut;
	
	public Connection(Socket con) {
		connection = con;
		try {
			conIn = new DataInputStream(connection.getInputStream());
			conOut = new DataOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final Socket getConnection() {
		return connection;
	}

	public final DataInputStream getInputStream() {
		return conIn;
	}

	public final DataOutputStream getOutputStream() {
		return conOut;
	}
	
	public final void startSession(ExecutorService es) {
		try {
			conOut.writeUTF("connected");
		} catch (IOException e) {
			e.printStackTrace();
		}
		es.submit(new Runnable() {

			@Override
			public void run() {
				
			}
			
		});
	}
	
	private final void writeOut(String str) {
		try {
			conOut.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final String readIn() {
		try {
			return conIn.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final void close(String disconnectMessage) {
		try {
			writeOut("disconnected "+disconnectMessage);
			conOut.close();
			conIn.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
