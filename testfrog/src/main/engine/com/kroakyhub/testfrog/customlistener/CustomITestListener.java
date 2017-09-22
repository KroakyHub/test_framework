package com.kroakyhub.testfrog.customlistener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.annotations.IBeforeTest;
import com.kroakyhub.testfrog.base.BaseTest;

public class CustomITestListener extends BaseTest implements ITestListener {

	static Logger log = Logger.getLogger(CustomITestListener.class.getName());
	
	public void onFinish(ITestContext arg0) {
		logging("Test suite has has finished");
	}

	public void onStart(ITestContext arg0) {
		logging("Test suite has started");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {

		String screenshotName = captureScreenShot(result);
		logging("Capturing image: " + screenshotName );
		logging(result.getMethod().getDescription() + "-- Test failed\n");

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult result) {
		logging("Starting test: " + result.getMethod().getDescription());

	}

	public void onTestSuccess(ITestResult result) {
		String screenshotName = captureScreenShot(result);
		logging("Capturing image: " + screenshotName );
		logging(result.getMethod().getDescription() + "-- Test passed\n");

	}
	
	private void logging(String message){
		log.info(message);
		Reporter.log(message);
	}

	

}
