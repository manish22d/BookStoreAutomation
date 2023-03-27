Feature: As a user, i want to validate user is able to create new books


  Scenario Outline: User can do a search from the home page
    Given user have correct book details
      | name   | author   | publication   | category   | pages   | price   |
      | <name> | <author> | <publication> | <category> | <pages> | <price> |
    When user send request to create book
    Then transaction should complete successfully
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