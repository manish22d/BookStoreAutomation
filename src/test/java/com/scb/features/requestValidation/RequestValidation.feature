Feature: As User, i want to validate application is responding with correct error on bad input


  Scenario: user is getting bad request error on sending create book request without body
    Given user want to send request without any request body
    When user triggers book request
    Then transaction completed with bad request


  Scenario: user is getting bad request error on sending create book request with empty body
    Given user want to send request with empty object
    When user triggers book request
    Then transaction completed with bad request


  Scenario: verify user is getting bad request error on sending update book request with empty body
    Given user want to update book "3"
    And user want to send update request with empty object
    When user triggers book request
    Then transaction completed with bad request