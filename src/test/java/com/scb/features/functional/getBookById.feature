Feature: As a user, i want to validate user is able to get book details

  Scenario Outline: User can do a search from the home page
    Given user wants to perform operation on "<book_id>"
    When user triggers get request individual book
    Then transaction should complete successfully
    And validate correct book details is retrieved

    Examples:
      | book_id |
      | 1       |

  Scenario Outline: User can do a search from the home page
    Given user wants to perform operation on "<book_id>"
    When user triggers get request individual book
    Then transaction should complete successfully
    And validate correct book details is retrieved

    Examples:
      | book_id |
      | 1       |