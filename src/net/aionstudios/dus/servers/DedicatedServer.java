package net.aionstudios.dus.servers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import net.aionstudios.dus.Dust;
import net.aionstudios.ndf.ANDFTree;
import net.aionstudios.ndf.util.ANDFFormats;

public class DedicatedServer {
	
	private String config;
	private ANDFTree serverConfig;
	
	private Process process;
	private boolean running = false;
	
	private BufferedReader reader;
	private BufferedReader readere;
	private BufferedWriter writer;
	
	
	/**
	 * Creates a dedicated server from an already existing server file.
	 * 
	 * @param config The path, relative to DustServer, to a server's dust configuration file.
	 */
	public DedicatedServer(String config, String dir){
		serverConfig.parseFrom(Dust.getPATH() + dir+config, ANDFFormats.RECURSIVE_NODE);
		ServerManager.addServer(this);
	}
	
	/**
	 * Creates a dedicated server and populates the server's config for Dust.
	 * 
	 * @param config The path, relative to dir, to a server's dust configuration file.
	 * @param name The name of the server or it's type.
	 * @param launch The launch command for the server.
	 * @param dir The path, relative to DustServer, to a directory containing all this servers files. Ending and beginning with a /
	 */
	public DedicatedServer(String config, String name, String launch, String dir){
		if(!dir.endsWith("/")){
			dir = dir+"/";
		}
		if(!dir.startsWith("/")){
			dir = "/"+dir;
		}
		serverConfig.setValueAtPath("server.name", name);
		serverConfig.setValueAtPath("server.launch", launch);
		serverConfig.setValueAtPath("server.dir", dir);
		serverConfig.assembleTo(dir+config, ANDFFormats.RECURSIVE_NODE);
		this.config = Dust.getPATH()+dir+config;
		ServerManager.addServer(this);
	}
	
	/**
	 * Start a new thread to run the server with an ExecutorService. 
	 * Uses launch file information from the DedicatedServer constructor to start the server.
	 * 
	 * @param config The path, relative to DustServer, to a server's dust configuration file.
	 */
	private final void start() {
		ServerManager.getExecutor().execute(new Runnable(){

			@Override
			public void run() {
				try {
					ProcessBuilder builder = new ProcessBuilder(serverConfig.getValueAtPath("server.launch"));
					builder.directory(new File(serverConfig.getValueAtPath("server.dir")));
					builder.redirectErrorStream(true);
					
					process = builder.start();
					
					OutputStream stdin = process.getOutputStream();
					InputStream stderr = process.getErrorStream();
					InputStream stdout = process.getInputStream();
					reader = new BufferedReader (new InputStreamReader(stdout));
					readere = new BufferedReader (new InputStreamReader(stderr));
					writer = new BufferedWriter(new OutputStreamWriter(stdin));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		running = true;
	}
	
	private final void stop() {
		try {
			writer.write("stop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		running = false;
	}
	
	private final void forceStop() {
		try {
			writer.write("stop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		process.destroy();
		running = false;
	}
	
	public boolean saveConfigFile(){
		return true;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getName() {
		return serverConfig.getValueAtPath("server.name");
	}

	public void setName(String name) {
		serverConfig.setValueAtPath("server.name", name);
	}

	public String getLaunch() {
		return serverConfig.getValueAtPath("server.launch");
	}

	public void setLaunch(String launch) {
		serverConfig.setValueAtPath("server.launch", launch);
	}

	public String getDir() {
		return serverConfig.getValueAtPath("server.dir");
	}

	public void setDir(String dir) {
		serverConfig.setValueAtPath("server.dir", dir);
	}
	
	public boolean isRunning() {
		return running;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}
	

	public BufferedReader getReaderError() {
		return readere;
	}

	public void setReaderError(BufferedReader readere) {
		this.readere = readere;
	}

	public Process getProcess() {
		return process;
	}

}
