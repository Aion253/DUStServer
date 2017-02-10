package net.aionstudios.dus.servers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerManager {
	
	private static List<DedicatedServer> dsList = new ArrayList<DedicatedServer>();
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static List<DedicatedServer> getDsList() {
		return dsList;
	}
	
	public static ExecutorService getExecutor() {
		return executor;
	}

}
