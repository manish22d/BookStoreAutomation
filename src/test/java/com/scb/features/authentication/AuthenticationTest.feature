Feature: User is able to get list of books


  Scenario: user is getting authorization error on using invalid credential
    Given user want to send request with invalid credential
    When user triggers get request with invalid credential
    Then transaction should complete with authentication error