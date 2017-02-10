package net.aionstudios.dus.servers;

import java.io.IOException;

public abstract class DedicatedServer {
	
	private String config;
	private String name;
	private String launch;
	
	/**
	 * Creates a dedicated server from an already existing server file.
	 * 
	 * @param config The path, relative to DustServer, to a server's dust configuration file.
	 */
	public DedicatedServer(String config){
		
	}
	
	/**
	 * Creates a dedicated server and populates the server's config for Dust.
	 * 
	 * @param config The path, relative to DustServer, to a server's dust configuration file.
	 * @param name The name of the server or it's type.
	 * @param launch The launch command for the server.
	 */
	public DedicatedServer(String config, String name, String launch){
		
	}
	
	/**
	 * Start a new thread to run the server with an ExecutorService. 
	 * Uses launch file information from the DedicatedServer constructor to start the server.
	 * 
	 * @param config The path, relative to DustServer, to a server's dust configuration file.
	 */
	public final void start(){
		ServerManager.getExecutor().execute(new Runnable(){

			@Override
			public void run() {
				try {
					Runtime.getRuntime().exec(launch);
				} catch (IOException e) {
					
				}
			}
			
		});
	}

}
