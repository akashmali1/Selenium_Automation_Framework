package org.automationframework.AbstractComponents;

import org.automationframework.pageobjects.MyCartPage;
import org.automationframework.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;


    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartBtn;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersBtn;

    public void waitForElementToAppear(By webElementLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(webElementLocator));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public MyCartPage openCart() {
        cartBtn.click();
        return new MyCartPage(driver);
    }

    public OrdersPage openOrders() {
        ordersBtn.click();
        return new OrdersPage(driver);
    }
}
