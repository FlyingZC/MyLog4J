package com.zc.zlog;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.jdbc.JDBCAppender;
import org.junit.Test;

public class LogUtils
{
    private static Logger rootLogger = Logger.getRootLogger();

    public static boolean changeLogLevel(String appenderName, LogLevel newLevel)
    {
        Appender appender = getAppenderByName(appenderName);
        if (appender != null)
        {
            setLevel(appender, newLevel);
            return true;
        }
        return false;
    }

    private static Appender getAppenderByName(String appenderName)
    {
        @SuppressWarnings("rawtypes")
        Enumeration allCurrAppenders = rootLogger.getAllAppenders();
        while (allCurrAppenders.hasMoreElements())
        {
            Appender currAppender = (Appender) allCurrAppenders.nextElement();
            //可以获取到配置的appender的name,则可以根据不同的name动态修改不同的日志级别
            if (appenderName.equals(currAppender.getName()))
            {
                return currAppender;
            }
        }
        return null;
    }

    private static void setLevel(Appender appender, LogLevel myLogLevel)
    {
        //默认level
        Level defaultLogLevel = rootLogger.getLevel();
        switch (myLogLevel)
        {
            case TRACE:
                setLevel(appender, Level.TRACE);
                break;
            case DEBUG:
                setLevel(appender, Level.DEBUG);
                break;
            case INFO:
                setLevel(appender, Level.INFO);
                break;
            case WARN:
                setLevel(appender, Level.WARN);
                break;
            case ERROR:
                setLevel(appender, Level.ERROR);
                break;
            case FATAL:
                setLevel(appender, Level.FATAL);
                break;
            case OFF:
                setLevel(appender, Level.OFF);
                break;
            default:
                setLevel(appender, defaultLogLevel);
        }

    }

    private static void setLevel(Appender appender, Level log4jLevel)
    {

        if (appender instanceof AppenderSkeleton)
        {
            ((AppenderSkeleton) appender).setThreshold(log4jLevel);
        }
        else if (appender instanceof JDBCAppender)
        {
            ((JDBCAppender) appender).setThreshold(log4jLevel);
        }
    }

    @Test
    public void test()
    {
        Logger logger=Logger.getLogger(this.getClass());
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        logger.debug("这是改级别前的debug日志");
        logger.info("这是改级别前的info日志");
        logger.warn("这是改级别前的warn日志");
        logger.error("这是改级别前的error日志");
        logger.fatal("这是改级别前的fatal日志");
        LogUtils.changeLogLevel("stdout", LogLevel.ERROR);
        logger.debug("这是改级别后的debug日志");
        logger.info("这是改级别后的info日志");
        logger.warn("这是改级别后的warn日志");
        logger.error("这是改级别后的error日志");
        logger.fatal("这是改级别后的fatal日志");
    }
}

enum LogLevel
{
    OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE;
    public static LogLevel getLogLevel(String logLevelStr)
    {
        return null;
    }

}
