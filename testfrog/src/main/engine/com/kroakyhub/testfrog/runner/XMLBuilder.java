package com.kroakyhub.testfrog.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class XMLBuilder {
	
	public static List<XmlSuite> build(String configFilePath){
						
		TestEnvironmentReader.reader(configFilePath);
		TestcaseReader.reader(configFilePath);
		
		// Creating "suite"
		XmlSuite xmlSuite = new XmlSuite();
    	xmlSuite.setName(TestEnvironmentReader.environmentConfigurationMap.get("Suite Name"));
    	    	
    	// Creating listener tag
    	List<String> listeners = new ArrayList<String>();
    	listeners.add("com.kroakyhub.testfrog.customlistener.CustomITestListener");
    	xmlSuite.setListeners(listeners);
    	
    	// Creating "test" tag
    	XmlTest xmlTest = new XmlTest(xmlSuite);
    	xmlTest.setName("Sanity Tests");
    	
    	// Linking test to the suite
    	List<XmlSuite> suites = new ArrayList<XmlSuite>();
    	List<XmlClass> classes = new ArrayList<XmlClass>();
    	
    	// Creating class tag
    	for(Map.Entry<String, List<String>> entry : TestcaseReader.TestcaseMap.entrySet()){
    		XmlClass xmlClass = new XmlClass(entry.getKey());
    		// Including the test methods
    		List<XmlInclude> includedMethods = new ArrayList<XmlInclude>();
    		for(String methodName : entry.getValue()){
    			includedMethods.add(new XmlInclude(methodName));
    		}
    		xmlClass.setIncludedMethods(includedMethods);
    		classes.add(xmlClass);
    	}
    	
    	xmlTest.setXmlClasses(classes);
    	suites.add(xmlSuite);
    	
    	return suites;
    	
    }

}
