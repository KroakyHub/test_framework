package com.kroakyhub.testfrog.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kroakyhub.testfrog.helper.ExcelHelper;

public class TestcaseReader {

	

	public static Map<String,List<String>> TestcaseMap;
	private static String filePath;
	
	public static void runSelect(String filePath){
		TestcaseReader.filePath = filePath;
		String run = TestEnvironmentReader.environmentConfigurationMap.get("Run");
		
		if(run.equalsIgnoreCase("All")){
			allRun();
		}else if(run.equalsIgnoreCase("Selected")){
			selectRun();
		}else if(run.equalsIgnoreCase("Pass")){
			statusRun(run);
		}else if(run.equalsIgnoreCase("Fail")){
			statusRun(run);
		}else if(run.equalsIgnoreCase("Skip")){
			statusRun(run);
		}else{
			System.out.println("Invalid option");
			System.exit(0);
		}
		
	}
	
	private static void allRun() {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		ExcelHelper ExcelHelper = new ExcelHelper(filePath);
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if(tempTestcaseClasspath.equals(testcaseClasspath)){
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					listOfMethods.add(methodName);
				}
			}
			TestcaseMap.put(testcaseClasspath, listOfMethods);
		}
		System.out.println("Running all test cases");
	}
	
	private static void selectRun() {
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		ExcelHelper ExcelHelper = new ExcelHelper(filePath);
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		int counter = 0;
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if(tempTestcaseClasspath.equals(testcaseClasspath)){
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					String run = ExcelHelper.getCellData(sheetName, rowNum, "Run");	
					if(run.equalsIgnoreCase("Y")){
						listOfMethods.add(methodName);
						counter++;
					}
				}
			}
			if(counter == 0){
				System.out.println("No test case found");
				System.exit(0);
			}
			TestcaseMap.put(testcaseClasspath, listOfMethods);
		}
		System.out.println("Running selected test cases");
	}
	
	private static void statusRun(String status){
		TestcaseMap = new HashMap<String, List<String>>();
		String sheetName = "Testcase";
		ExcelHelper ExcelHelper = new ExcelHelper(filePath);
		Set<String> setOftestcaseClasspaths = ExcelHelper.getUniqueColumnItem(sheetName, "Testcase Classpath");
		int rowCount = ExcelHelper.getRowCount(sheetName);
		int counter = 0;
		for (String testcaseClasspath : setOftestcaseClasspaths) {
			List<String> listOfMethods = new ArrayList<String>();
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
				String tempTestcaseClasspath = ExcelHelper.getCellData(sheetName, rowNum, "Testcase Classpath");
				if(tempTestcaseClasspath.equals(testcaseClasspath)){
					String methodName = ExcelHelper.getCellData(sheetName, rowNum, "Method name");
					String run = ExcelHelper.getCellData(sheetName, rowNum, "Status");	
					if(run.equalsIgnoreCase(status)){
						listOfMethods.add(methodName);
						counter ++;
					}
				}
			}
			if(counter == 0){
				System.out.println("No test case found");
				System.exit(0);
			}
			TestcaseMap.put(testcaseClasspath, listOfMethods);
		}
		System.out.println("Running " + status + " test cases");
	}
	
}
