package com.kroakyhub.testfrog.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {

	private Properties prop = null;
	
	private void loadProperties(String filePath) {
		
		Properties prop = new Properties();
		try{
			InputStream input = new FileInputStream(filePath);
			prop.load(input);
		}catch(IOException e){
			
		}
	}
	
	public String readProperty(String filePath, String key){
		String value;
		loadProperties(filePath);
		value = prop.getProperty(key);
		return value;
		
	}

}
