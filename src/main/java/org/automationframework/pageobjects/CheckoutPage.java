package org.automationframework.pageobjects;

import org.automationframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(partialLinkText = "PLACE")
    WebElement placeOrderLink;

    public void selectCountry(String country) {
        selectCountry.sendKeys(country);
        WebElement element = driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]"));
        waitForElementToAppear(element);
        element.click();
    }

    public OrderConfirmationPage placeOrder() {
        placeOrderLink.click();
        return new OrderConfirmationPage(driver);
    }
}
