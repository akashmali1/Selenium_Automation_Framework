package org.automationframework;

import org.automationframework.TestComponents.BaseTest;
import org.automationframework.pageobjects.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    String orderId = "";
    String expectedProduct = "ADIDAS ORIGINAL";

    @Test
    public void placeOrderTest() {

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
        orderId = orderConfirmation.getOrderIds();

    }

    @Test(dependsOnMethods = {"placeOrderTest"})
    public void verifyPlacedOrderTest() {
        ProductsCatalogue productsCatalogue = landingPage.loginApplication("akash12345@gmail.com", "Akash@123");
        OrdersPage orders = productsCatalogue.openOrders();
        Assert.assertTrue(orders.isOrderIdFound(expectedProduct, orderId));
    }
}