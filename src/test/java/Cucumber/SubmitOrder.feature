Feature: Purchase the order from ECommerce
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of purchasing the order
    Given logged in with username <name> and password <password>
    When I add product <productName> in the cart
    And I checkout <productName> and submit the order 
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name  					    | password 					| productName   |
      | vedaprada@gmail.com | Stayfocused@22    | IPHONE 13 PRO |
      