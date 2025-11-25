@ui @regression
Feature: Forgot password form submission

  Background:
    Given User opens the Forgot Password page

  Scenario: Submit forgot password form with valid email
    When User enters email "user@example.com"
    And User submits the forgot password form
    Then Heading should be "Internal Server Error"