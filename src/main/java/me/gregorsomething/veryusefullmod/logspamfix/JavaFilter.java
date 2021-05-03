package me.gregorsomething.veryusefullmod.logspamfix;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;

import java.util.ArrayList;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JavaFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        for (String s : VeryUsefullModClient.VUM_CONF.whitelist) {
            if (record.getMessage().contains(s)) {
                return false;
            }
        }
        return true;
    }
    public static void applyFilter() {
        Logger.getLogger("").setFilter(new JavaFilter());
    }
}
