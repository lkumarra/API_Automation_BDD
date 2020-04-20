package com.w2a.API_Automation_BDD.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.w2a.API_Automation_BDD.listners.ExtentReportListner;

public class CreateCustomerAPISteps extends ExtentReportListner {
	RequestSpecification requestSpecification = null;
	Response response;
	RequestSpecification reqSpecs;
	ExtentTest logInfo = null;

	@Before("@CreateCustomerAPI")
	public void beforeCreateCustomerAPIScenario(cucumber.api.Scenario scenario) {
		System.out.println("Scenario.getId()-->" + scenario.getId());
		String[] splitedName = scenario.getId().split("/");
		String featureName = splitedName[splitedName.length - 1];
		featureName = featureName.substring(0, featureName.indexOf("."));
		String scenarioName = scenario.getName();
		if (createCustomerTest == null) {
			System.out.println("Extent report-->" + extent);
			createCustomerTest = extent.createTest(Feature.class, featureName);
		}
		test = createCustomerTest.createNode(Scenario.class, scenarioName);
		System.out.println("Feature Name-->" + featureName);
	}

	@Given("I set a valid auth using the secret key")
	public void i_set_a_valid_auth_using_the_secret_key() {
		requestSpecification = given().auth().basic(configProperty.getValidAuthKey(), "");
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"),
					" i_set_the_authentication_using_the_invalid_secret_key");
			logInfo.info("I set the authentication successfully");
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("I pass {string} as email")
	public void i_pass_as_email(String customerEmail) {
		requestSpecification = requestSpecification.formParam("email", customerEmail);
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), " i_set_as_email_of_the_customer");
			logInfo.info("I set " + customerEmail + " as the email in the request");
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("I pass {string} as description")
	public void i_pass_as_description(String customerDescription) {
		requestSpecification = requestSpecification.formParam("description", customerDescription);
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "i_set_as_the_description_of_the_customer");
			logInfo.info("I set " + customerDescription + " as the description in the request");
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@When("I send a post request to url")
	public void i_send_a_post_request_to_url() {
		response = requestSpecification.post(configProperty.getCustomerAPIEndPoint());
		response.prettyPrint();
		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "i_send_a_Post_request_to_the_url");
			logInfo.info("I send a post request");
			logInfo.info(response.asString());
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Then("I should get {int} as the status code in response")
	public void i_should_get_as_the_status_code_in_response(int expectedStatusCode) {
		Assert.assertEquals(response.statusCode(), expectedStatusCode);
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"i_should_get_" + expectedStatusCode + "as_the_response_status_code");
			logInfo.info("I got " + response.getStatusCode() + " as the expected status code");
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Then("I should get {string} as email in the response")
	public void i_should_get_as_email_in_the_response(String expectedEmail) {
		Assert.assertEquals(response.jsonPath().get("email"), expectedEmail);
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"),
					"the_email_in_the_response_should_be " + expectedEmail);
			logInfo.info("The email in the response is " + response.jsonPath().get("email"));
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Then("I should get {string} as description in response")
	public void i_should_get_as_description_in_response(String expectedDescription) {
		Assert.assertEquals(response.jsonPath().get("description"), expectedDescription);
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"he_description_in_the_response_should_be " + expectedDescription);
			logInfo.info("The description  in the response is " + response.jsonPath().get("description"));
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Then("The customer id filed should not be null")
	public void the_customer_id_filed_should_not_be_null() {
		Assert.assertNotNull(response.jsonPath().get("id"));
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "i_should_get_id_field_in_the_response");
			logInfo.info("Id field is available in the respons, whose value is " + response.jsonPath().get("id"));
		} catch (AssertionError e) {
			testStepHandle("FAIL", logInfo, e);
		} catch (Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

}
