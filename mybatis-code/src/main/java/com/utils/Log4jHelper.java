package com.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * @ClassName : Log4jHelper
 * @Description : 打印日志的工具类
 * @Author : fmx
 * @Date: 2021-07-29 14:34
 */
public class Log4jHelper {

    public static Logger getLogger() {
        StackTraceElement[] stackTraceElements =
                Thread.currentThread().getStackTrace();
        if (Objects.nonNull(stackTraceElements)
                && stackTraceElements.length >= 3) {
            String className = Thread
                    .currentThread().getStackTrace()[2]
                    .getClassName();
            return LogManager.getLogger(Log4jHelper.class);
        }
        return LogManager.getLogger(Log4jHelper.class);
    }

    private static Logger LOGGER =
            LogManager.getLogger(Log4jHelper.class);

    private static void log(Level level,
                                  String message,
                                  Object... args) {
        StackTraceElement[] elements =
                Thread.currentThread().getStackTrace();
        if (Objects.isNull(elements)) {
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement element = elements[i];
            if (Objects.isNull(element)) {
                continue;
            }
            if (!Objects.equals(Log4jHelper.class.getName(),
                    element.getClassName())) {
                continue;
            }
            if (!Objects.equals("log",
                    element.getMethodName())) {
                continue;
            }
            if (!(i + 2 < elements.length)) {
                break;
            }
            element = elements[i + 2];
            if (Objects.isNull(element)) {
                break;
            }
            LOGGER.info(
                    String.format("%s.%s %s:%s", element.getClassName(),
                            element.getMethodName(), element.getLineNumber(),message),
                    args);
        }
    }

    public static void info (String message, Object... args) {
        Log4jHelper.log(Level.INFO, message, args);
    }

    public static void debug (String message, Object... args) {
        Log4jHelper.log(Level.DEBUG, message,args);
    }

    public static void error (String message, Object... args) {
        Log4jHelper.log(Level.ERROR, message, args);
    }

    public static void warn (String message, Object... args) {
        Log4jHelper.log(Level.WARN, message, args);
    }

}
