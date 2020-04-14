Feature: Validate Create Customer API

Scenario: Validate Create Customer API with valid auth key and data
Given I set a valid auth 
And I pass "tester_1@gmail.com" as email
And I pass "This is sample description" as description 
When I send a post request to "https://api.stripe.com/v1/customers"
Then I should get 200 as the status code in response
And I should get "tester_1@gmail.com" as email in the response
And I should get "This is sample description" as description in response
And The customer id filed should not be null