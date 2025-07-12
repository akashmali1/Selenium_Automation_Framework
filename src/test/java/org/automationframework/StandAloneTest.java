package org.automationframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        String expectedProduct = "ADIDAS ORIGINAL";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("akash12345@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Akash@123");
        driver.findElement(By.name("login")).click();
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".col-lg-4"));

        WebElement adidasShoe = allProducts.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(expectedProduct)).findFirst().orElse(null);

        assert adidasShoe != null;
        adidasShoe.findElement(By.cssSelector("button:last-child")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartWrap h3 "));
        boolean isExpectedProductFound = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(expectedProduct));
        Assert.assertTrue(isExpectedProductFound);
        driver.findElement(By.xpath("//button[text()='CheckoutPage']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
        WebElement selectCountry = driver.findElement(By.xpath("//span[text()= \" India\"]"));
        wait.until(ExpectedConditions.visibilityOf(selectCountry));
        selectCountry.click();
        driver.findElement(By.partialLinkText("PLACE")).click();

        String orderConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(orderConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
        List<WebElement> orderIds = driver.findElements(By.xpath("//tr/td/label[@class='ng-star-inserted']"));
        orderIds.forEach(orderId -> System.out.println("Order Id : " + orderId.getText().replace("|", "").trim()));

        driver.quit();
    }
}
