package com.kroakyhub.testfrog.customlistener;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.kroakyhub.testfrog.base.BaseTest;
import com.kroakyhub.testfrog.helper.PropertiesFileHelper;

public class CustomWebDriverEventListner extends BaseTest implements WebDriverEventListener{
	
	static Logger log = Logger.getLogger(CustomWebDriverEventListner.class.getName());
		

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		String filePath = testClassPath + "\\src\\test\\resources\\testconfig.properties";
		Properties prop = new Properties();
		prop = PropertiesFileHelper.loadProperties(filePath);
		int waitDuration = Integer.parseInt(prop.getProperty("waitduration"));
		WebDriverWait wait = new WebDriverWait(driver, waitDuration);
		String loaderxpath = prop.getProperty("loaderxpath"); 
		
		if(!loaderxpath.equalsIgnoreCase("na")){
			try{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loaderxpath)));
			}catch(Exception e){
				logging("Loader animation took time to disappear");
			}
		}
			
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e){
			logging("Not able to locate element " + by.toString() + " due to " + e.toString());
		}
			
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		logging("Clicked on element: "+element.toString());
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		logging("Changed value of element: "+element.toString());
		
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		
		
	}
	
	private void logging(String message){
		log.info(message);
		Reporter.log(message);
	}

}
