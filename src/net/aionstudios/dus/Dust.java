package net.aionstudios.dus;

import java.io.File;
import java.net.URISyntaxException;

import net.aionstudios.dus.logging.Logger;
import net.aionstudios.dus.logging.StandardOverride;

public class Dust {
	
	private static File PATH;
	
	//Dedicated Unified Servers - DUST
	public static void main(String[] args){
		Logger.setup();
		StandardOverride.enableOverride();
		System.out.println("Initialization Complete!");
	}
	
	private void checkSetup() {
		PATH = getFullStartPath();
		File log = new File(PATH+"\\logs\\");
		File dt = new File(PATH+"\\data\\");
		boolean not = false;
		if(log.mkdir()||dt.mkdir()){
			not = true;
		}
		if(not){
			System.out.println("Created Missing Server Directories...");
		}
	}
	
	private File getFullStartPath(){
		try {
			return new File(Dust.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace(System.err);
		}
		return null;
	}
	
}
