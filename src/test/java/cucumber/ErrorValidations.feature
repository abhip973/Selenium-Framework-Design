Feature: Validating error message

  @ErrorValidation
  Scenario Outline: Verify error message with incorrect username or password
    Given I landed on ecommerce page
    When Logged in with email <email> and password <pwd>
    Then Verify error message is visible to the user
    Examples:
      | email               | pwd       |
      | punj.abhi@gmail.com | Abhi@1234 |
