package net.aionstudios.dus.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLServerSocketFactory;

public class Server {
	
	private ServerSocket server;
	private SSLServerSocketFactory ssf;
	private boolean running = true;
	private List<Connection> connections = new ArrayList<Connection>();
	private ExecutorService es = Executors.newCachedThreadPool();
	
	public Server() {
		try {
			ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			server = ssf.createServerSocket(11511);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final void start() {
		if(!server.isBound()) {
			System.out.println("The Server was unable to start: Failed binding to socket.");
			System.exit(0);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				shutdown();
			}
			
		});
		while(running) {
			try {
				Socket s = server.accept();
				Connection c = new Connection(s);
				connections.add(c);
				c.startSession(es);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public final void stop() {
		running = false;
		for (Connection c : connections) {
			c.close("Server forcibly closed the connection.");
		}
	}
	
	public final void shutdown() {
		running = false;
		for (Connection c : connections) {
			c.close("Server shutting down.");
		}
	}

}
