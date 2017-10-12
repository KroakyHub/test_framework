package com.kroakyhub.testfrog.runner;

import java.util.HashMap;
import java.util.Map;

import com.kroakyhub.testfrog.helper.ExcelHelper;

public class TestEnvironmentReader {

	public static Map<String, String> environmentConfigurationMap;

	public static void reader(String configFilePath){
		
		environmentConfigurationMap = new HashMap<String, String>();
		
		String sheetName = "Environment";
		
		ExcelHelper ExcelHelper = new ExcelHelper(configFilePath);
		int rowCount = ExcelHelper.getRowCount(sheetName);
				
		int rowNum;
		
		for(rowNum = 1 ; rowNum <= rowCount ; rowNum ++){
			environmentConfigurationMap.put(ExcelHelper.getCellData(sheetName, rowNum, 1),ExcelHelper.getCellData(sheetName, rowNum, 2));
		}
		
	}

}
