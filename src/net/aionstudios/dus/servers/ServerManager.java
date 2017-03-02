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
	
	public static DedicatedServer getServer(int serverNumber){
		if(!dsList.isEmpty()){
			return dsList.get(serverNumber);
		}
		return null;
	}
	
	public static DedicatedServer getServer(String name){
		for(DedicatedServer s : dsList){
			if(s.getName()==name){
				return s;
			}
		}
		return null;
	}

	public static DedicatedServer getServer(DedicatedServer server){
		for(DedicatedServer s : dsList){
			if(s==server){
				return s;
			}
		}
		return null;
	}
	
	public static void addServer(DedicatedServer server) {
		if(!dsList.contains(server)) {
			dsList.add(server);
		}
	}
	
	public static DedicatedServer createServer(String config, String name, String launch, String dir) {
		return new DedicatedServer(config, name, launch, dir);
	}
	
	public static void removeServer(int serverNumber) {
		if(!dsList.isEmpty()){
			dsList.remove(serverNumber);
		}
	}
	
	public static void removeServer(String name) {
		for(DedicatedServer s : dsList){
			if(s.getName()==name){
				dsList.remove(s);
			}
		}
	}
	
	public static void removeServer(DedicatedServer server) {
		if(!dsList.isEmpty()){
			dsList.remove(server);
		}
	}
	
}
