package com.kroakyhub.testfrog.customlistener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.kroakyhub.testfrog.base.BaseTest;
import com.kroakyhub.testfrog.runner.TestcaseResultWriter;
import com.relevantcodes.extentreports.LogStatus;

public class CustomITestListener extends BaseTest implements ITestListener {

	static Logger log = Logger.getLogger(CustomITestListener.class.getName());

	public void onFinish(ITestContext arg0) {
		logging("Test suite has has finished","info");
		report.flush();
		report.close();
	}

	public void onStart(ITestContext arg0) {
		Reporter.log("Test suite has started");
		log.info("Test suite has started");
		
		//logging("Test suite has started","info");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {

		TestcaseResultWriter writer = new TestcaseResultWriter();
		writer.writeResult(result, "Fail", "NA");
		logging(result.getMethod().getDescription() + "-- Test failed\n","fail");
		report.endTest(test);
	}

	public void onTestSkipped(ITestResult result) {
		TestcaseResultWriter writer = new TestcaseResultWriter();
		writer.writeResult(result, "Skip", "NA");
		logging(result.getMethod().getDescription() + "-- Test skipped\n","skip");
		report.endTest(test);
	}

	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getMethod().getDescription());
		logging("Starting test: " + result.getMethod().getDescription(),"info");

	}

	public void onTestSuccess(ITestResult result) {

		TestcaseResultWriter writer = new TestcaseResultWriter();
		writer.writeResult(result, "Pass", "NA");
		logging(result.getMethod().getDescription() + "-- Test passed\n", "pass");
		report.endTest(test);
	}

	private void logging(String message, String logType) {
		Reporter.log(message);

		if (logType.equalsIgnoreCase("info")) {
			log.info(message);
			test.log(LogStatus.INFO, message);
		}else if(logType.equalsIgnoreCase("fail")) {
			log.error(message);
			test.log(LogStatus.FAIL, message);
		}else if(logType.equalsIgnoreCase("pass")){
			log.info(message);
			test.log(LogStatus.PASS, message);
		}else if(logType.equalsIgnoreCase("skip")){
			log.info(message);
			test.log(LogStatus.SKIP, message);
		}
	}

}
