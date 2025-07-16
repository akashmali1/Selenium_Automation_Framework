package org.automationframework;

import org.automationframework.TestComponents.BaseTest;
import org.automationframework.pageobjects.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderTest extends BaseTest {

    String orderId = "";
    String expectedProduct = "ADIDAS ORIGINAL";

    @Test(dataProvider = "testData", groups = {"Purchase"})
    public void placeOrderTest(HashMap<String, String> testData)  {

        ProductsCatalogue productsCatalogue = landingPage.loginApplication(testData.get("email"), testData.get("password"));

        productsCatalogue.addProductToCart(testData.get("expectedProduct"));
        MyCartPage myCart = productsCatalogue.openCart();

        boolean expectedProductFound = myCart.isExpectedProductFoundInCart(testData.get("expectedProduct"));
        Assert.assertTrue(expectedProductFound);
        CheckoutPage checkout = myCart.checkout();

        checkout.selectCountry("india");
        OrderConfirmationPage orderConfirmation = checkout.placeOrder();

        String orderConfirmMessage = orderConfirmation.getOrderConfirmationMessage();
        Assert.assertTrue(orderConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
        orderId = orderConfirmation.getOrderIds();
    }

    @Test(dependsOnMethods = {"placeOrderTest"}, dataProvider = "testData")
    public void verifyPlacedOrderTest(HashMap<String, String> testData) {
        ProductsCatalogue productsCatalogue = landingPage.loginApplication(testData.get("email"), testData.get("password"));
        OrdersPage orders = productsCatalogue.openOrders();
        Assert.assertTrue(orders.isOrderIdFound(expectedProduct, orderId));
    }

    @DataProvider
    public Object[][] testData() throws IOException {

        String filepath = System.getProperty("user.dir") + "//src//test//java//org//automationframework//data//PurchaseOrder.json";
        List<HashMap<String, String>> data = readJsonData(filepath);

        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };
    }
}