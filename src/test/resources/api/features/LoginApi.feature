Feature: Login API

  @login_api
  Scenario: Verify login API is working
    Given the base URI is "http://64.227.160.186:8080"
    When the user sends a login request with username and password
    Then the status code should be 200
    And the response should contain a valid token
    And the response should have the correct username "uday1234"
