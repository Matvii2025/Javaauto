@ui @regression
Feature: JavaScript executor interactions

  Background:
    Given User is on the Add Remove Elements page

  Scenario: Highlight and JS click creates Delete button
    When User highlights the Add Element button
    And User clicks the Add Element button using JS
    Then Delete button should be visible

  Scenario: Set input value using JS
    Given User opens the Login page for JS test
    When User finds input "#username" by JS and scrolls into view
    And User highlights the element
    And User sets JS value "tomsmith" into the element
    Then Element should contain value "tomsmith"