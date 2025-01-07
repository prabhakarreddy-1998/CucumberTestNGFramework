Feature: Search for a product on Greenkart Website

  @SearchProducts
  Scenario: Search for a product and verify its availability in offers page
    Given User should be able to navigate to the Greenkart website
    And User should be able to view the homepage
    When User searches for a product on the homepage with the shortname Ora
    And Extract the text of the filtered product on the homepage
    Then User should be able to navigate to the Offers page
    And A new tab opens
    And User should search for the same product on the Offers page with the shortname Ora
    And Extract the text of the filtered product from the offers page
    And Verify that the product available on the homepage also exists on the Offers page

  @SearchProducts_params
  Scenario Outline: Search for a Multiple products and verify its availability in offers page
    Given User should be able to navigate to the Greenkart website
    And User should be able to view the homepage
    When User searches for a product on the homepage with the shortname <Name>
    And Extract the text of the filtered product on the homepage
    Then User should be able to navigate to the Offers page
    And A new tab opens
    And User should search for the same product on the Offers page with the shortname <Name>
    And Extract the text of the filtered product from the offers page
    And Verify that the product available on the homepage also exists on the Offers page

    Examples: 
      | Name |
      | Tom  |
      | Pot  |
      | Beet |
