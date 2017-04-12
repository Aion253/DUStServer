package net.aionstudios.dus;

import java.io.File;
import java.net.URISyntaxException;

import net.aionstudios.dus.logging.Logger;
import net.aionstudios.dus.logging.StandardOverride;
import net.aionstudios.dus.servers.DedicatedServer;
import net.aionstudios.ndf.ANDFAssembler;
import net.aionstudios.ndf.ANDFTree;

public class Dust {
	
	private static File PATH;
	private static ANDFTree dust;
	
	//Dedicated Unified Servers - DUST
	public static void main(String[] args){
		checkSetup();
		Logger.setup();
		StandardOverride.enableOverride();
		System.out.println("Initialization Complete!");
		PATH = getFullStartPath();
	}
	
	private static void checkSetup() {
		File log = new File(PATH+"/logs/");
		File dt = new File(PATH+"/data/");
		boolean not = false;
		if(log.mkdirs()||dt.mkdirs()){
			not = true;
		}
		if(not){
			System.out.println("Created Server Directories...");
		}
	}
	
	private static File getFullStartPath(){
		try {
			return new File(Dust.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace(System.err);
		}
		return null;
	}

	public static File getPATH() {
		return PATH;
	}

	public static ANDFTree getDustConfig() {
		return dust;
	}
	
}
