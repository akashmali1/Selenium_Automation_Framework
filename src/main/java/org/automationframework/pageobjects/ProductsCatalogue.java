package org.automationframework.pageobjects;

import org.automationframework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductsCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".col-lg-4")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement productAddedToCartAnimation;

    private By productsLocator = By.cssSelector(".col-lg-4");

    private By addToCartBtn = By.cssSelector("button:last-child");

    private By productAddedToCartToastMsg = By.cssSelector("#toast-container");

    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsLocator);
        return products;
    }

    public WebElement getProduct(String productName) {
        return getProductsList().stream().filter(singleProduct ->
                singleProduct.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProduct(productName);
        product.findElement(addToCartBtn).click();
        waitForElementToAppear(productAddedToCartToastMsg);
        waitForElementToDisappear(productAddedToCartAnimation);
    }

}
