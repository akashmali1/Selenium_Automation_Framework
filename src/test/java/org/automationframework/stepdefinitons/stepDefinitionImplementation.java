package org.automationframework.stepdefinitons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationframework.TestComponents.BaseTest;
import org.automationframework.pageobjects.*;
import org.testng.Assert;

import java.io.IOException;

public class stepDefinitionImplementation extends BaseTest {

    LandingPage landingPage;
    ProductsCatalogue productsCatalogue;
    MyCartPage cartPage;
    CheckoutPage checkout;
    OrderConfirmationPage orderConfirmation;

    @Given("person is on the landing page")
    public void person_is_on_the_landing_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^the person logs in with (.+) and (.+)$")
    public void the_person_logs_in_with_username_and_password(String username, String password) {
        productsCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^person adds product (.+) to the cart$")
    public void person_adds_product_to_the_cart(String productName) {
        productsCatalogue.addProductToCart(productName);
        cartPage = productsCatalogue.openCart();
    }

    @When("^checkout the (.+) and place the order$")
    public void checkout_the_product_and_place_the_order(String productName) {
        boolean expectedProductFound = cartPage.isExpectedProductFoundInCart(productName);
        Assert.assertTrue(expectedProductFound);
        checkout = cartPage.checkout();
        checkout.selectCountry("india");
        orderConfirmation = checkout.placeOrder();
    }

    @Then("{string} message is displayed in the confirmationPage")
    public void confirmation_message_is_displayed_in_the_confirmationPage(String message) {
        String orderConfirmMessage = orderConfirmation.getOrderConfirmationMessage();
        Assert.assertTrue(orderConfirmMessage.equalsIgnoreCase(message));
        driver.close();
    }

    @Then("^the output is (.+)$")
    public void the_output_is(String message){
        Assert.assertEquals(landingPage.getErrorMessage(), message);
        driver.close();
    }
}