package com.w2a.API_Automation_BDD.listners;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.w2a.API_Automation_BDD.utilities.ConfigProperty;
import io.restassured.RestAssured;

public class CustomListmers extends ExtentReportListner implements ITestListener{

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		extent = ExtentReportListner.setUp();
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}


		

}