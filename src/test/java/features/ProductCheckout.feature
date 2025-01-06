Feature: Validate product purchase flow with promo code on Greenkart Website

  @CheckoutProcess
  Scenario: Add a product, apply promo code, and validate Error message
    Given User navigates to the Greenkart website
    And User searches for a product with the name Tomato
    When User increments the product quantity to 3
    And User clicks on "Add to Cart"
    And User clicks on the cart icon
    And User clicks on Proceed to Checkout
    And Verify that the product name from LandingPage is matched with Checkout page
    And User enters the promo code TOMATO10
    And User clicks on the Apply button
    Then User should see a error message Invalid code ..!

  @CheckoutProcess_params
  Scenario Outline: Add a product, apply promo code, and validate Error message for parameterized data
    Given User navigates to the Greenkart website
    And User searches for a product with the name <ProductName>
    When User increments the product quantity to <Quantity>
    And User clicks on "Add to Cart"
    And User clicks on the cart icon
    And User clicks on Proceed to Checkout
    And Verify that the product name from LandingPage is matched with Checkout page
    And User enters the promo code <PromoCode>
    And User clicks on the Apply button
    Then User should see a error message <ErrorMessage>

    Examples: 
      | ProductName | Quantity | PromoCode  | ErrorMessage     |
      | Potato      |        2 | DISCOUNT10 | Invalid code ..! |
      | Cucumber    |        4 | CUCUMBER15 | Invalid code ..! |
