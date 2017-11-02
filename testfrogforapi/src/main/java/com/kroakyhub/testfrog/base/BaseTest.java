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
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
public class BaseTest {

	
	public String frameworkClassPath = System.getenv("DRIVER_HOME");
	public String testClassPath = System.getProperty("user.dir");
	public static ExtentReports report;
	public static ExtentTest test;
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
