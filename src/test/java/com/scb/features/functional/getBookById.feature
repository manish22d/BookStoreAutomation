Feature: As a user, i want to validate user is able to get book details

  Scenario Outline: verify user is able to  get details on individual book
    Given user wants to perform operation on "<book_id>"
    When user triggers get request individual book
    Then transaction should complete successfully
    And validate correct book details is retrieved

    Examples:
      | book_id |
      | 1       |

  Scenario Outline: verify user is getting correct status code for non existing book
    Given user wants to perform operation on "<book_id>"
    When user triggers get request individual book
    Then transaction completed with not found request


    Examples:
      | book_id |
      | 500     |