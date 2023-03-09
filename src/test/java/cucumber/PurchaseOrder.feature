Feature: Purchase Order from E-Commerce Website

  Background:
    Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with email <email> and password <pwd>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
    Examples:
      | email                    | pwd      | productName |
      | punj.abhishek1@gmail.com | Abhi@123 | ZARA COAT 3 |