@ui @smoke
Feature: Logout functionality

  Background:
    Given User is on the Login page
    And User logs in with valid credentials

  Scenario: Successful logout redirects to login page
    When User clicks Logout button
    Then User should be redirected to Login page