Feature: As a User, I want to validate user is able to get list of books


  Scenario: user is able to get list of books
    Given user want to get book list
    Then transaction should complete successfully


  Scenario: verify response is consistent on multiple request
    Given user want to get book list
    Then transaction should be consistent on request is sent 10 times