package net.aionstudios.dus.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import net.aionstudios.dus.Dust;

public class Logger {
	
	private static File outFile;
	private static FileOutputStream fos;
	private static PrintStream stream;
	private static int logCountToday = 1;
	private static boolean setup = false;
	
	public static void setup(){
		if(!setup){
			outFile = new File(getFullStartPath()+"\\logs\\"+DustOut.getStreamDate()+"-"+logCountToday+".log");
			outFile.getParentFile().mkdirs();
			correctFileNaming();
			try {
				fos = new FileOutputStream(outFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stream = new PrintStream(fos);
			setup = true;
		}
	}

	private static void correctFileNaming() {
		if(hasFile()){
			logCountToday++;
			outFile = new File(getFullStartPath()+"\\logs\\"+DustOut.getStreamDate()+"-"+logCountToday+".log");
			correctFileNaming();
		}
	}

	public static PrintStream getStream() {
		return stream;
	}
	
	public static boolean hasFile(){
		if(outFile.exists()){
			return true;
		}
		return false;
	}
	
	private static File getFullStartPath(){
		try {
			return new File(Dust.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace(System.err);
		}
		return null;
	}

}
