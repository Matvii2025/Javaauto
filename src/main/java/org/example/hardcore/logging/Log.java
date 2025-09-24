package org.example.hardcore.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private final Logger logger;

    private Log(Class<?> c) { this.logger = LogManager.getLogger(c); }
    public static Log get(Class<?> c) { return new Log(c); }

    public void debug(String msg, Object... args) { logger.debug(msg, args); }
    public void info (String msg, Object... args) { logger.info (msg, args); }
    public void warn (String msg, Object... args) { logger.warn (msg, args); }
    public void error(String msg, Object... args){ logger.error(msg, args); }
    public void action(String msg, Object... args){ logger.info("[ACTION] " + msg, args); }
}