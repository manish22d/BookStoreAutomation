Feature: As a user, i want to validate user is able to delete book


  Scenario Outline: verify user is able to delete book
    Given user wants to perform operation on "<book_id>"
    When user triggers delete request individual book
    Then transaction should complete successfully
    And validate book does not exists in book list

    Examples:
      | book_id |
      | 1       |