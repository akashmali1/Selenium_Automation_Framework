package org.automationframework.pageobjects;

import org.automationframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyCartPage extends AbstractComponent {

    WebDriver driver;

    public MyCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartWrap h3")
   private List<WebElement> allCartProducts;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkoutBtn;

    private By cartProductsLocator = By.cssSelector(".cartWrap h3");

    public boolean isExpectedProductFoundInCart(String expectedProduct) {
        waitForElementToAppear(cartProductsLocator);
        return allCartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(expectedProduct));
    }

    public CheckoutPage checkout(){
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}