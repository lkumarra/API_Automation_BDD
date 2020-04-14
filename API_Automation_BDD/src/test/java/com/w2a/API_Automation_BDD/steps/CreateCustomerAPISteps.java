package com.w2a.API_Automation_BDD.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

public class CreateCustomerAPISteps {
	RequestSpecification requestSpecification = null;
	Response response;

	@Given("^I set a valid auth$")
	public void i_set_a_valid_auth() {
		requestSpecification = given().auth().basic("sk_test_fgiiPiR9kkp58k8JswnP9iXf00MnxlK4KT", "");
	}

	@Given("^I pass \"([^\"]*)\" as email$")
	public void i_pass_something_as_email(String strArg1) {
		requestSpecification = requestSpecification.formParam("email", strArg1);

	}

	@Given("^I pass \"([^\"]*)\" as description$")
	public void i_pass_as_description(String arg1) {
		requestSpecification = requestSpecification.formParam("description", arg1);
	}

	@When("^I send a post request to \"([^\"]*)\"$")
	public void i_send_a_post_request_to_something(String strArg1) {
		response = requestSpecification.post(strArg1);
		response.prettyPrint();

	}

	@Then("^I should get 200 as the status code in response$")
	public void i_should_get_200_as_the_status_code_in_response() {
		System.out.println("The status code is " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

	}

	@Then("^I should get \"([^\"]*)\" as email in the response$")
	public void i_should_get_something_as_email_in_the_response(String strArg1) {
		System.out.println("The email in response is " + response.jsonPath().get("email"));
		Assert.assertEquals(response.jsonPath().get("email"), strArg1);

	}

	@Then("^I should get \"([^\"]*)\" as description in response$")
	public void i_should_get_something_as_description_in_response(String strArg1) {
		System.out.println("The description  in response is " + response.jsonPath().get("description"));
		Assert.assertEquals(response.jsonPath().get("description"), strArg1);
	}

	@And("^The customer id filed should not be null$")
	public void the_customer_id_filed_should_not_be_null() {
		Assert.assertNotNull(response.jsonPath().get("id"));

	}
}
