@ui @regression
Feature: Actions interactions

  Background:
    Given User opens the application homepage

  Scenario: Drag and Drop changes text to Dropped
    Given User navigates to Drag and Drop page
    When User drags draggable element onto droppable area
    Then Droppable text should contain "dropped"

  Scenario: Context menu right-click shows alert
    Given User navigates to Context Menu page
    When User right-clicks the context menu box
    Then Alert text should be "You selected a context menu"

  Scenario Outline: User selects multiple items
    Given User navigates to Selectable page
    When User selects items with indexes <i1>, <i2>, <i3>
    Then Exactly <count> items should be selected

    Examples:
      | i1 | i2 | i3 | count |
      | 0  | 1  | 3  | 3     |