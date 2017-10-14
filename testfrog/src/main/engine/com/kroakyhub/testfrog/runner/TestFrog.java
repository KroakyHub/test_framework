package com.kroakyhub.testfrog.runner;

import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestFrog {

	public static String filePath;
	
	public TestFrog(String filePath){
		TestFrog.filePath = filePath;
	}
	
	public void run(){
		List<XmlSuite> suites = XMLBuilder.build(filePath);
		TestcaseResultWriter writer = new TestcaseResultWriter(filePath);
		TestNG testng = new TestNG();
    	testng.setXmlSuites(suites);
    	testng.run();
	}
	
}
