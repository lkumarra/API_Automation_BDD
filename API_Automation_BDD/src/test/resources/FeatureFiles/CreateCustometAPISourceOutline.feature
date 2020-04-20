@CreateCustomerAPI
Feature: Validate Create Customer API

  Scenario Outline: Validate Create Customer API with valid auth key and data
    Given I set a valid auth using the secret key
    And I pass "<email>" as email
    And I pass "<description>" as description
    When I send a post request to url
    Then I should get <statusCode> as the status code in response
    And I should get "<expectedEmailInRes>" as email in the response
    And I should get "<expectedDescriptionRes>" as description in response
    And The customer id filed should not be null

    Examples: 
      | email           | description         | statusCode | expectedEmailInRes | expectedDescriptionRes |
      | test1@gmail.com | sample1 description |        200 | test1@gmail.com    | sample1 description    |
      | test2@gmail.com | sample2 description |        200 | test2@gmail.com    | sample2 description    |
