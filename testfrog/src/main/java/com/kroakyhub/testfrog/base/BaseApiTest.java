package com.kroakyhub.testfrog.base;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.kroakyhub.testfrog.helper.PropertiesFileHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {
	
	public String testClassPath = System.getProperty("user.dir");
	public Response response = null;
	
	private String fetchToken(){
		String token;
		PropertiesFileHelper PropertiesFileHelper = new PropertiesFileHelper();
		token = PropertiesFileHelper.readProperty(testClassPath+"\\src\\test\\resources\\testconfig.properties", "token");
		return token;
	}
	
	private RequestSpecification getRequestSpecification(){
		return RestAssured.given().contentType(ContentType.JSON);
	}
	
	public Response get(String Endpoint){
		RequestSpecification requestSpecification = new BaseApiTest().getRequestSpecification();
		requestSpecification.auth().oauth2(fetchToken());
		response = given().spec(requestSpecification).get(Endpoint);
		return response;
	}
	
	public Response post(String Endpoint, String jsonFilePath, Object pojo){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			pojo = objectMapper.readValue(new File(jsonFilePath), pojo.getClass());
			RequestSpecification requestSpecification = new BaseApiTest().getRequestSpecification();
			requestSpecification.auth().oauth2(fetchToken());
			requestSpecification.body(pojo);
			response = given().spec(requestSpecification).post(Endpoint);
			String str;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return response;
	}
	
	public Response patch(String Endpoint, String jsonFilePath, Object pojo){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			pojo = objectMapper.readValue(new File(jsonFilePath), pojo.getClass());
			RequestSpecification requestSpecification = new BaseApiTest().getRequestSpecification();
			requestSpecification.auth().oauth2(fetchToken());
			requestSpecification.body(pojo);
			response = given().spec(requestSpecification).patch(Endpoint);
			
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
