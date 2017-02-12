package net.aionstudios.dus.logging;

import java.io.PrintStream;

public class DustStream extends PrintStream {

	public DustStream() {
		super(Logger.getStream(), true);
	}
	
	@Override
    public final void print(String s)
    {//do what ever you like
        DustOut.print(s);
    }
	
	@Override
    public final void println(String s)
    {//do what ever you like
        DustOut.println(s);
    }

}
