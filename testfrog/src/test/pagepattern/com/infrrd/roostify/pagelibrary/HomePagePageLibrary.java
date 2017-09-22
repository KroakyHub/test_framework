package com.kroakyhub.testfrog.pagelibrary;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kroakyhub.testfrog.base.BasePageLibrary;
import com.kroakyhub.testfrog.pageobject.HomePagePageObject;

public class HomePage extends BasePageLibrary<HomePagePageObject> {

	EventFiringWebDriver driver;
		
	public HomePage(EventFiringWebDriver driver, HomePagePageObject repository) {
		super(driver, repository);
		this.driver = driver;
	}
	
	Select dropdown;
	
	public String addUserInputForPurchase() throws InterruptedException{			
		try {
			WebDriverWait wait =  new WebDriverWait(driver, 10);
			dropdown = new Select(ObjectRepository.purposeOfLoan);
			dropdown.selectByValue("purchase");
			ObjectRepository.purchasePrice.sendKeys("350000");
			//downPayment.sendKeys("20");
			ObjectRepository.zipCode.sendKeys("85001");
			ObjectRepository.CTALink.click();
			dropdown =  new Select(ObjectRepository.propertyType);
			dropdown.selectByValue("SingleFamily");
			dropdown =  new Select(ObjectRepository.propertyUse);
			dropdown.selectByValue("primary");
			ObjectRepository.firstTimeHomeBuyerCheckBox.click();
			ObjectRepository.veteranCheckBox.click();
			ObjectRepository.hoaFees.sendKeys("25000");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectRepository.getMyOptionsButtonPath)));
			ObjectRepository.getMyOptionsButton.click();

		} catch (NoSuchElementException e) {	
			e.printStackTrace();
		}
		return "Successfully Entered Relevant Data For Purchase ";
	}


	public String addUserInputForRefinance(){
		try{		
			dropdown = new Select(purposeOfLoan);
			dropdown.selectByValue("refi");
			currentMortgageBalance.sendKeys("53000");
			currentPropertyValue.sendKeys("350000");
			zipCode.sendKeys("85001");
			CTALink.click();
			dropdown =  new Select(propertyType);
			dropdown.selectByValue("SingleFamily");
			dropdown =  new Select(propertyUse);
			dropdown.selectByValue("primary");
			veteranCheckBox.click();

		}catch (ElementNotFoundException e) {
			System.out.println("Element is not visible ");
		}catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}	
		return "Successfully Entered Relevant Data For Refinance ";
	}


	public String addUserInputForRefinanceCashOut(){	
		try{
			dropdown =  new Select(purposeOfLoan);
			dropdown.selectByValue("reficashout");
			currentMortgageBalance.sendKeys("350000");
			desiredCashOut.sendKeys("53000");
			zipCode.sendKeys("85001");
			CTALink.click();
			dropdown =  new Select(propertyType);
			dropdown.selectByValue("Condo");
			dropdown =  new Select(propertyUse);
			dropdown.selectByValue("investment");
			veteranCheckBox.click();
			annualPropertyTax.sendKeys("3456");
			homeOwnerInsurance.sendKeys("5500");
			currentPropertyValue.sendKeys("5500");
			/*WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")))*/;
			getMyOptionsButton.click();

		}catch (ElementNotFoundException e) {
			System.out.println("Element is not visible ");
		}
		return "Successfully Entered Relevant Data For Refinance Cashout";
	}

	public String verifyingGraphLegends(){
		try{
			WebDriverWait wait =  new WebDriverWait(driver, 10);
			dropdown = new Select(purposeOfLoan);
			dropdown.selectByValue("purchase");
			purchasePrice.sendKeys("350000");
			//downPayment.sendKeys("20");
			zipCode.sendKeys("85001");
			CTALink.click();
			dropdown =  new Select(propertyType);
			dropdown.selectByValue("SingleFamily");
			dropdown =  new Select(propertyUse);
			dropdown.selectByValue("primary");
			firstTimeHomeBuyerCheckBox.click();
			veteranCheckBox.click();
			hoaFees.sendKeys("25000");
			getMyOptionsButton.click();	
			principleAndInterestField.isDisplayed();
			propertyTextField.isDisplayed();
			homeInsuranceField.isDisplayed();
			PMIField.isDisplayed();
			getThisLoanButton.isDisplayed();

		}catch (ElementNotFoundException e) {
			System.out.println("Element is not visible ");
		}catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}	
		return "Successfully validated Graph Legends ";
	}


	public String validateOtherOptimizationsAndOptions() {

		try{
			WebDriverWait wait =  new WebDriverWait(driver, 10);
			dropdown = new Select(purposeOfLoan);
			dropdown.selectByValue("purchase");
			purchasePrice.sendKeys("123456");
			//downPayment.sendKeys("20");
			zipCode.sendKeys("85001");
			CTALink.click();
			dropdown =  new Select(propertyType);
			dropdown.selectByValue("SingleFamily");
			dropdown =  new Select(propertyUse);
			dropdown.selectByValue("primary");
			firstTimeHomeBuyerCheckBox.click();
			veteranCheckBox.click();
			hoaFees.sendKeys("25000");
			getMyOptionsButton.click();	
			otherOptionSection.isDisplayed();
			otherOptimizationText.isDisplayed();	

		}catch (ElementNotFoundException e) {
			e.printStackTrace();
		}catch (NoSuchFieldError  e) {
			e.printStackTrace();
		}
		return "Successfully Validated Other Optimizations And Others Section";

	}


	public String validatingLeadFormDetailsContainer() {
		try{
			WebDriverWait wait =  new WebDriverWait(driver, 10);
			dropdown = new Select(purposeOfLoan);
			dropdown.selectByValue("purchase");
			purchasePrice.sendKeys("123456");
			//downPayment.sendKeys("20");
			zipCode.sendKeys("85001");
			CTALink.click();
			dropdown =  new Select(propertyType);
			dropdown.selectByValue("SingleFamily");
			dropdown =  new Select(propertyUse);
			dropdown.selectByValue("primary");
			firstTimeHomeBuyerCheckBox.click();
			veteranCheckBox.click();
			hoaFees.sendKeys("25000");
			getMyOptionsButton.click();
			getThisLoanButton.click();
			leadFormDetailsHeader.isDisplayed();
		}catch (ElementNotFoundException e) {
			e.printStackTrace();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return "Successfully Validated LeadForm ";
	}


	public String getMyOptionsEnablingCheck() {
		String value = null;
		try {
			dropdown = new Select(purposeOfLoan);
			dropdown.selectByValue("purchase");
			if(!getMyOptionsButton.isEnabled()){
				value = "Success";
			}else{
				value = "Failed";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return value;
	}


	public String validateZipCodeLookupError() {
		String value = null;
		try{		
			zipCode.sendKeys("9999");
			if(zipCodeErrorMesage.isDisplayed()){
				value = "Success";
			}else{
				value = "Failed";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return value;
	}


	public String validateZipCodeLookUpContainer() {
		String value = null;
		try{

			zipCode.sendKeys("8500");
			if(zipCodeLookUpDropDown.isDisplayed()){
				value = "Success";
			}else{
				value = "Failed";
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	

}
