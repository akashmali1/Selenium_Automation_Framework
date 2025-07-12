package org.automationframework;

import org.automationframework.TestComponents.BaseTest;
import org.automationframework.pageobjects.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void placeOrder() throws IOException {

        String expectedProduct = "ADIDAS ORIGINAL";

        ProductsCatalogue productsCatalogue = landingPage.loginApplication("akash12345@gmail.com", "Akash@123");

        productsCatalogue.addProductToCart(expectedProduct);
        MyCartPage myCart = productsCatalogue.openCart();

        boolean expectedProductFound = myCart.isExpectedProductFoundInCart(expectedProduct);
        Assert.assertTrue(expectedProductFound);
        CheckoutPage checkout = myCart.checkout();

        checkout.selectCountry("india");
        OrderConfirmationPage orderConfirmation = checkout.placeOrder();

        String orderConfirmMessage = orderConfirmation.getOrderConfirmationMessage();
        Assert.assertTrue(orderConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
        orderConfirmation.getOrderIds();

        driver.quit();
    }
}