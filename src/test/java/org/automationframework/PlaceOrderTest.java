package org.automationframework;

import org.automationframework.pageobjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;

import java.time.Duration;

public class PlaceOrderTest {
    public static void main(String[] args) {

        String expectedProduct = "ADIDAS ORIGINAL";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.openLandingPage();
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