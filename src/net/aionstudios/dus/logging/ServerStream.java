package net.aionstudios.dus.logging;

import java.io.PrintStream;

public class ServerStream extends PrintStream {

	public ServerStream() {
		super(System.out, true);
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
