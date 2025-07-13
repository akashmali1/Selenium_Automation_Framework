package org.automationframework.pageobjects;

import org.automationframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement orderPlacedMessage;

    @FindBy(xpath = "//tr/td/label[@class='ng-star-inserted']")
    List<WebElement> allOrderIds;

    public String getOrderConfirmationMessage() {
        return orderPlacedMessage.getText();
    }

    public String getOrderIds() {
        return allOrderIds.stream().map(WebElement::getText).findFirst().orElse("");
    }
}