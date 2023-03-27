Feature: As a User, i want to validate user is able to update book details correcly


  Scenario Outline: User can do a search from the home page
    Given user wants to perform operation on "<book_id>"
    And user have correct book details
      | id   | name   | author   | publication   | category   | pages   | price   |
      | <id> | <name> | <author> | <publication> | <category> | <pages> | <price> |
    When user send request to update book
    Then transaction should complete successfully
    And book details are updated correctly

    Examples:
      | book_id | id | name                                                   | author           | publication   | category    | pages | price |
      | 1       | 1  | Clean Code: A Handbook of Agile Software Craftsmanship | Robert C. Martin | Prentice Hall | Programming | 500   | 22    |