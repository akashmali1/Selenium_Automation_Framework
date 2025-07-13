package org.automationframework.pageobjects;

import org.automationframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']")
    List<WebElement> allOrdersList;

    public boolean isOrderIdFound(String productName, String orderId) {

        String trimmedOrderId = orderId.replace("|", "").trim();
        waitForElementToAppear(allOrdersList.get(0));

        List<WebElement> filteredOrders = allOrdersList.stream().filter(order ->
                order.findElement(By.xpath("td[2]")).getText().equals(productName)).collect(Collectors.toList());

        List<String> filteredOrderIds = filteredOrders.stream().map(order ->
                order.findElement(By.xpath("th")).getText().replace("|", "").trim()).collect(Collectors.toList());

        return filteredOrderIds.stream().anyMatch(id -> id.equals(trimmedOrderId));
    }
}
