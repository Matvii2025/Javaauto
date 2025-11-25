@ui @smoke
Feature: Login functionality

  Background:
    Given User is on the Login page

  Scenario Outline: User logs in with different credentials
    When User enters username "<username>"
    And User enters password "<password>"
    And User clicks Login button
    Then Login message should contain "<result>"

    Examples:
      | username   | password               | result                               |
      | tomsmith   | SuperSecretPassword!   | You logged into a secure area!       |
      | wrongUser  | wrongPass              | Your username is invalid! |