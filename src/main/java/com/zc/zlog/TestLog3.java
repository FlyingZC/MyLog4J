package com.zc.zlog;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.jdbc.JDBCAppender;

public class TestLog3
{
    private static Logger logger = Logger.getLogger(TestLog3.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        logger.debug("这是改级别前的debug日志");
        logger.info("这是改级别前的info日志");
        logger.warn("这是改级别前的warn日志");
        logger.error("这是改级别前的error日志");
        logger.fatal("这是改级别前的fatal日志");
        Logger rootLogger = Logger.getRootLogger();
        @SuppressWarnings("rawtypes")
        Enumeration allCurrAppenders = rootLogger.getAllAppenders();
        while (allCurrAppenders.hasMoreElements())
        {
            Appender currAppender = (Appender) allCurrAppenders.nextElement();
            //可以获取到配置的appender的name,则可以根据不同的name动态修改不同的日志级别
            System.out.println(currAppender.getName());
            if (currAppender instanceof AppenderSkeleton)
            {
                ((AppenderSkeleton) currAppender).setThreshold(Level.ERROR);
            }
            else if (currAppender instanceof JDBCAppender)
            {
                ((JDBCAppender) currAppender).setThreshold(Level.ERROR);
            }
        }

        //可修改日志级别,但是修改不了文件中的日志级别
        logger.setLevel(Level.ERROR);
        logger.debug("这是改级别后的debug日志");
        logger.info("这是改级别后的info日志");
        logger.warn("这是改级别后的warn日志");
        logger.error("这是改级别后的error日志");
        logger.fatal("这是改级别后的fatal日志");
    }
    
}
