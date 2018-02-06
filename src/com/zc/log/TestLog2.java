package com.zc.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog2 {
	Logger log=Logger.getLogger(TestLog2.class);
	public TestLog2(){
		log.warn("yes ~~~");
	}
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		TestLog2 log2=new TestLog2();
		
	}
}
