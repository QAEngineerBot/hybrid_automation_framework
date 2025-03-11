Feature: Get User Profile API

  Scenario: Verify if Get Profile API is working
    Given the user logs in with username "uday1234" and password "uday1234"
    When the user sends a request to fetch their profile
    Then the API should return the username as "uday1234"
    And the status code should be 200
