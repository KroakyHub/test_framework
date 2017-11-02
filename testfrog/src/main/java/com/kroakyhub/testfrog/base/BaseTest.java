package com.kroakyhub.testfrog.base;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.kroakyhub.testfrog.customlistener.CustomWebDriverEventListner;
import com.kroakyhub.testfrog.helper.ExcelHelper;
import com.kroakyhub.testfrog.helper.FileStructureHelper;
import com.kroakyhub.testfrog.helper.PropertiesFileHelper;
import com.kroakyhub.testfrog.runner.TestEnvironmentReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTest {

	// <----------------------------------- UI Testing
	// -------------------------------------->

	public Properties prop = new Properties();
	public EventFiringWebDriver driver;
	public WebDriver baseDriver;
	public String frameworkClassPath = System.getenv("DRIVER_HOME");
	public String testClassPath = System.getProperty("user.dir");
	public static ExtentReports report;
	public static ExtentTest test;

	public EventFiringWebDriver getDriver() {
		return driver;
	}

	public void initializeDriver() {

		initializeReports();

		setbrowser(TestEnvironmentReader.environmentConfigurationMap.get("Browser"));

		driver = new EventFiringWebDriver(baseDriver);
		CustomWebDriverEventListner listener = new CustomWebDriverEventListner();
		driver.register(listener);
	}

	public void killDriver() {
		driver.quit();
	}

	public void gotoTestURL() {

		driver.get(TestEnvironmentReader.environmentConfigurationMap.get("Test URL"));

	}

	private void setbrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", frameworkClassPath + "\\geckodriver.exe");
				baseDriver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", frameworkClassPath + "\\chromedriver.exe");
				baseDriver = new ChromeDriver();
				baseDriver.manage().window().maximize();
			} else {
				System.out.println("Browser not available");
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String captureScreenShot(ITestResult result) {
		String imageFileName = "";

		Object currentClass = result.getInstance();
		EventFiringWebDriver driverObject = ((BaseTest) currentClass).getDriver();
		if (driverObject == null) {
			imageFileName = "No Image Captured";
		} else {
			String customeLocation = "\\src\\test\\resources\\screenshots\\";
			FileStructureHelper.makeDirectory(testClassPath + customeLocation);
			imageFileName = testClassPath + customeLocation
					+ new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new GregorianCalendar().getTime()) + "-"
					+ result.getMethod().getMethodName() + ".png";
			File scrFile = ((TakesScreenshot) driverObject).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(imageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Reporter.log("File path: " + imageFileName);
			Reporter.log("<a href=\"" + imageFileName + "\"><img src=\"file:///" + imageFileName + "\" alt=\"\""
					+ "height='100' width='100'/> " + "<br />");

			return (imageFileName);
		}

		return (imageFileName);
	}

	public void autocompleteTyper(String text, WebElement element) {
		try {
			List<String> textAsArray = Arrays.asList(text.split(""));
			for (String character : textAsArray) {
				element.sendKeys(character);
				Thread.sleep(300);
			}
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.TAB);
		} catch (InterruptedException e) {
		}
	}

	// <----------------------------------- API Testing
	// -------------------------------------->

	public String baseURI;
	public String basePath;
	public String endPoint;
	public Map<String, Object> headerMap = new HashMap<String, Object>();
	public Map<String, Object> queryParamMap = new HashMap<String, Object>();
	public Map<String, Object> pathParamMap = new HashMap<String, Object>();
	public Object pojo;
	public String jsonFilePath;

	public void initializeReports() {
		String reportPath = testClassPath + "\\testfrogreport.html";
		report = new ExtentReports(reportPath);

		String log4jConfPath = frameworkClassPath + "\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}

	public Response get() {
		RestAssured.baseURI = baseURI;
		RestAssured.basePath = basePath;
		Response response = given().headers(headerMap).queryParams(queryParamMap).pathParams(pathParamMap).when()
				.get(endPoint);
		return response;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Response post(Class c) {

		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {

			pojo = objectMapper.readValue(new File(testClassPath + jsonFilePath), c);
			RestAssured.baseURI = baseURI;
			RestAssured.basePath = basePath;
			response = given().headers(headerMap).queryParams(queryParamMap).pathParams(pathParamMap).body(pojo).when()
					.post(endPoint);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Response patch(Class c) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {

			pojo = objectMapper.readValue(new File(testClassPath + jsonFilePath), c);
			RestAssured.baseURI = baseURI;
			RestAssured.basePath = basePath;
			response = given().headers(headerMap).queryParams(queryParamMap).pathParams(pathParamMap).body(pojo).when()
					.patch(endPoint);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

}
