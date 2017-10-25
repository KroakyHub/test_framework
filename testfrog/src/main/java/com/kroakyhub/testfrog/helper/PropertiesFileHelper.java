package com.kroakyhub.testfrog.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileHelper {

	
	public static Properties loadProperties(String filePath) {
		
		Properties prop = new Properties();
		try{
			InputStream input = new FileInputStream(filePath);
			prop.load(input);
		}catch(IOException e){
			
		}
		
		return prop;
	}

}
