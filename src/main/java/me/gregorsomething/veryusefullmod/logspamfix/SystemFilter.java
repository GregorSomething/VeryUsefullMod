package me.gregorsomething.veryusefullmod.logspamfix;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.LogRecord;

public class SystemFilter extends PrintStream {

    public SystemFilter(OutputStream out) {
        super(out, true);
    }

    @Override
    public void println(String s) {
        if (!shouldFilter(s)) {
            super.println(s);
        }
    }

    private boolean shouldFilter(String s) {
        for (String filter : VeryUsefullModClient.VUM_CONF.whitelist) {
            if (s.contains(filter)) {
                return true;
            }
        }
        return false;
    }

    public static void applyFilter() {
        System.setOut(new SystemFilter(System.out));
    }

}
