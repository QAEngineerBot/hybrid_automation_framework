Feature: Update User Profile

  Scenario: Verify user can update their profile information successfully
    Given the user logs in with username "uday1234" and password "uday1234"
    When the user retrieves their profile information
    Then the username in the profile should be "uday1234"
    When the user updates their profile with first name "uday987", last name "Patil", email "udaypatil123@easipro.com", and mobile number "7766554433"
    Then the profile should be updated with first name "uday987", last name "Patil", email "udaypatil123@easipro.com", and mobile number "7766554433"
