Feature: Feature file containing scenarios to test Awesome Notes app

  @smoke
  Scenario: C_AN_001 - Language change functionality
    Given user navigates to application address
    Then language selected should be "English"
    When user changes application language to "Romanian"
    Then language selected should be "Romana"

  @smoke
  Scenario: C_AN_002 - View the list of the notes
    Given user navigates to application address
    Then a list of available should be displayed
    And note has priority icon
    And note has text
    And note has delete button

  @smoke
  Scenario: C_AN_003 - Delete note - complete remove flow
    Given user navigates to application address
    When user selects delete icon for 2 note
    Then a confirmation modal for delete should be displayed
    When user confirms changes displayed in modal
    Then previously deleted note should not be available

  @smoke
  Scenario: C_AN_004 - Delete note - discard delete process
    Given user navigates to application address
    When user selects delete icon for 2 note
    Then a confirmation modal for delete should be displayed
    When user discards changes displayed in modal
    Then previously attempted note to delete should be available

  @wip @bug
  Scenario: C_AN_005 - View notes - page scrolling
    Given user navigates to application address
    When user resizes browser to 800 width and 400 height
    Then verify the add note button is not visible
    When user scrolls down to the add button location
    Then verify the add note button is visible

  @smoke
  Scenario: C_AN_006 - Create note
    Given user navigates to application address
    When user adds text "Hello this a test" into new note input field
    And user clicks add note button
    Then added note should be displayed in the list

  @smoke
  Scenario: C_AN_007 - Create note - custom priority
    Given user navigates to application address
    When user adds text "Hello this a test" into new note input field
    And user selects "Blocker" note priority
    And user clicks add note button
    Then added note should be displayed in the list
    And added note should have "Blocker" priority