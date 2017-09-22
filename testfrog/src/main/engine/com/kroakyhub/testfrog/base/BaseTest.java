package com.kroakyhub.testfrog.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.kroakyhub.testfrog.customlistener.CustomWebDriverEventListner;
import com.kroakyhub.testfrog.helper.FileStructureHelper;
import com.kroakyhub.testfrog.helper.PropertiesFileHelper;

public class BaseTest {
	
	public Properties prop = new Properties();
	public static EventFiringWebDriver driver;
	public static WebDriver baseDriver;
	public String frameworkClassPath = System.getenv("TESTFROG_HOME");;
	public String testClassPath = System.getProperty("user.dir");;
		
	
	public void initializeDriver() {
				
		String log4jConfPath = frameworkClassPath + "\\src\\main\\resources\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		String testConfigFilePath = testClassPath + "\\src\\test\\resources\\testconfig.properties";
		prop = PropertiesFileHelper.loadProperties(testConfigFilePath);
		setbrowser(prop.getProperty("browser"));
		
		driver = new EventFiringWebDriver(baseDriver);
		CustomWebDriverEventListner listener = new CustomWebDriverEventListner();
		driver.register(listener);
	}
	
	public void killDriver(){
		driver.quit();
	}
	
	public void gotoTestURL(){
		
		driver.get(prop.getProperty("testURL"));
		driver.manage().window().maximize();
		
	}
	
	private void setbrowser(String browser){
		
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", frameworkClassPath + "\\src\\main\\drivers\\geckodriver.exe");
			baseDriver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", frameworkClassPath + "\\src\\main\\drivers\\chromedriver.exe");
			baseDriver = new ChromeDriver();
		}
		
	}
	
	public String captureScreenShot(ITestResult result) {
		
		String customeLocation = "\\src\\test\\resources\\screenshots\\";
		FileStructureHelper.makeDirectory(testClassPath + customeLocation);
		String imageFileName = testClassPath + customeLocation
				+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-"
				+ result.getMethod().getMethodName() + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(imageFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Reporter.log("File path: "+ imageFileName);
		Reporter.log("<a href=\"" + imageFileName + "\"><img src=\"file:///" + imageFileName
				+ "\" alt=\"\"" + "height='100' width='100'/> " + "<br />");

		
		return (imageFileName);
	}
	
	
	
	
	
	

}
