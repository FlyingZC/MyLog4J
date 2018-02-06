package com.zc.zlog;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog2
{
    private static Logger logger = Logger.getLogger(TestLog2.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
        logger.debug("这是改级别前的debug日志");
        logger.info("这是改级别前的info日志");
        logger.warn("这是改级别前的warn日志");
        logger.error("这是改级别前的error日志");
        logger.fatal("这是改级别前的fatal日志");
        logger.getEffectiveLevel();
        logger.getLevel();
        logger.getPriority();
        //可修改日志级别,但是修改不了文件中的日志级别
        logger.setLevel(Level.ERROR);
        logger.debug("这是改级别后的debug日志");
        logger.info("这是改级别后的info日志");
        logger.warn("这是改级别后的warn日志");
        logger.error("这是改级别后的error日志");
        logger.fatal("这是改级别后的fatal日志");
        logger.getParent().getAppender("");
        //
    }
}
