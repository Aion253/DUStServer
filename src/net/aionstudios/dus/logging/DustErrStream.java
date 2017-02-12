package net.aionstudios.dus.logging;

import java.io.PrintStream;

public class DustErrStream extends PrintStream {

	public DustErrStream() {
		super(Logger.getStream(), true);
	}
	
	@Override
    public final void print(String s)
    {//do what ever you like
        DustOut.errp(s);
    }
	
	@Override
    public final void println(String s)
    {//do what ever you like
        DustOut.errpl(s);
    }

}
