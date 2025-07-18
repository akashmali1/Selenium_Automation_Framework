Feature: Add product to cart and place an order

  Background:
    Given person is on the landing page

  @Regression
  Scenario Outline: place an order
    Given the person logs in with <username> and <password>
    When person adds product <productName> to the cart
    And checkout the <productName> and place the order
    Then "THANKYOU FOR THE ORDER." message is displayed in the confirmationPage

    Examples:
      | username             | password  | productName     |
      | akash12345@gmail.com | Akash@123 | ADIDAS ORIGINAL |