Feature: As a user, i want to validate user is able to create new books


  Scenario Outline: User is able to create Book successfully
    Given user have correct book details
      | name   | author   | publication   | category   | pages   | price   |
      | <name> | <author> | <publication> | <category> | <pages> | <price> |
    When user send request to create book
    Then transaction should complete successfully
    And verify book is created with correct details
    And verify user is able get details of new book
    And verify name updated correctly
    And verify author updated correctly
    And verify publication updated correctly
    And verify category updated correctly
    And verify pages updated correctly
    And verify author updated correctly

    Examples:
      | name        | author | publication | category    | pages | price |
      | Clean Code: | Robert | Prentice    | Programming | 500   | 22    |


  Scenario: create book is consistent on multiple request
    Given user have correct book details
      | name   | author   | publication   | category   | pages   | price   |
      | Clean Code: | Robert | Prentice    | Programming | 500   | 22    |
    When user send request to create book
    Then transaction should complete successfully
    And verify response is consistent  on request is sent 10 times