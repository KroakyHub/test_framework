package com.infrrd.roostify.testcase;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.infrrd.roostify.pagelibrary.HomePagePageLibrary;
import com.kroakyhub.testfrog.base.BaseTest;

public class HomePageTestCase extends BaseTest {
	
	String response = null;
	
	@BeforeClass
	public void driverSetup(){
		initializeDriver();
	}
	
	@BeforeMethod
	public void testSetup(){
		gotoTestURL();
	}
	
	@AfterTest
	public void clearResponse(){
		response = null;
	}
	
	@Test(description = "Validating presence of relevant fields for purchase ")
	public void validateOptionsForPurchase() throws InterruptedException{
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.addUserInputForPurchase();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating presence of relevant fields for refinance ")
	public void validateOptionsForRefinance() throws InterruptedException{
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.addUserInputForRefinance();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating presence of relevant fields for refinance cashout")
	public void validateOptionsForRefinanceCashout(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response =  homePagePageLibrary.addUserInputForRefinanceCashOut();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating graph legends")
	public void validateGraphLegends(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.verifyingGraphLegends();
		Assert.assertEquals(response.contains("Success"), true);
	}


	@Test(description = "Validating other optimizations section")
	public void validateOtherOptimizations(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.validateOtherOptimizationsAndOptions();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating Lead form upon clicking get this loan button ")
	public void validatingLeadForm(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.validatingLeadFormDetailsContainer();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating Get My Options is not enabled if madatory data is not filled")
	public void checkEnablingOfGetMyOptionsButton(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.getMyOptionsEnablingCheck();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validating Zip code error block")
	public void validateZipCodeError(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response = homePagePageLibrary.validateZipCodeLookupError();
		Assert.assertEquals(response.contains("Success"), true);
	}

	@Test(description = "Validate zip code look up container")
	public void validateZipCodeLookUp(){
		HomePagePageLibrary homePagePageLibrary = new HomePagePageLibrary(driver);
		response =  homePagePageLibrary.validateZipCodeLookUpContainer();
		Assert.assertEquals(response.contains("Success"), true);
	}
	

}
