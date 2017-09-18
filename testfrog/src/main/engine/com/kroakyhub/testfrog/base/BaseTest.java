package com.kroakyhub.testfrog.base;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.kroakyhub.testfrog.customlistener.CustomWebDriverEventListner;
import com.kroakyhub.testfrog.helper.PropertiesFileHelper;

public class BaseTest {
	
	public Properties prop = new Properties();
	public EventFiringWebDriver driver;
	public WebDriver baseDriver;
		
	
	public void initializeDriver() {
		
		String log4jConfPath = "./src/main/resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		String testConfigFilePath = "./src/test/resources/testconfig.properties";
		prop = PropertiesFileHelper.loadProperties(testConfigFilePath);
		setbrowser(prop.getProperty("browser"));
		
		driver = new EventFiringWebDriver(baseDriver);
		CustomWebDriverEventListner listener = new CustomWebDriverEventListner();
		driver.register(listener);
		
		driver.get(prop.getProperty("testURL"));
		driver.manage().window().maximize();
	
	}
	
	public void setbrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./src/main/drivers/geckodriver.exe");
			baseDriver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./src/main/drivers/chromedriver.exe");
			baseDriver = new ChromeDriver();
		}
	}
	
	
	
	

}
