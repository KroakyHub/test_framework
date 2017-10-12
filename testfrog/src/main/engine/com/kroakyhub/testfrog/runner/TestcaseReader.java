package com.kroakyhub.testfrog.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kroakyhub.testfrog.helper.ExcelHelper;

public class TestcaseReader {

	public static Map<String,List<String>> TestcaseMap;

	public static void reader(String configFilePath) {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		ExcelHelper ExcelHelper = new ExcelHelper(configFilePath);
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if(tempTestcaseClasspath.equals(testcaseClasspath)){
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					String run = ExcelHelper.getCellData(sheetName, rowNum, "Run");	
					if(run.equalsIgnoreCase("Y")){
						listOfMethods.add(methodName);
					}
				}
			}
			TestcaseMap.put(testcaseClasspath, listOfMethods);
		}
	}
	
}
