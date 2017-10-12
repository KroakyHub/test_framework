package com.kroakyhub.testfrog.runner;

import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestFrog {

	public void run(String configFilePath){
		List<XmlSuite> suites = XMLBuilder.build(configFilePath);
		TestNG testng = new TestNG();
    	testng.setXmlSuites(suites);
    	testng.run();
	}
	
}
