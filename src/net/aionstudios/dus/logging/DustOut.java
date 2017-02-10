package net.aionstudios.dus.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DustOut {
	
	private static String streamPrefix = "";
	private static Logger logger;
	private static String n = System.getProperty("line.separator");
	
	public static void println(String text){
		if(streamPrefix != ""){
			text = "["+getStreamTime()+" INFO]: ["+streamPrefix+"] "+text;
		} else {
			text = "["+getStreamTime()+" INFO]: "+text;
		}
		Logger.getStream().println(text.replaceAll("\u001B\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", n));
	}
	
	public static void print(String text){
		Logger.getStream().print(text.replaceAll("\u001B\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", n));
	}
	
	public static void errpl(String text){
		text = "["+getStreamTime()+" SERVER ERROR]: "+text;
		Logger.getStream().println(text.replaceAll("\\n", n));
	}
	
	public static void errp(String text){
		Logger.getStream().print(text.replaceAll("\\n", n));
	}
	
	public static String getStreamTime(){
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
	    return ft.format(dNow);
	}
	
	public static String getStreamDate(){
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	    return ft.format(dNow);
	}

	public static String getStreamPrefix() {
		return streamPrefix;
	}

	public static void setStreamPrefix(String streamPrefix1) {
		streamPrefix = streamPrefix1;
	}
	
	public static void clearStreamPrefix(){
		streamPrefix = "";
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		DustOut.logger = logger;
	}
	
}
