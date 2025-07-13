package org.automationframework.TestComponents;

import org.automationframework.pageobjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/automationframework/resources/GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito", "start-maximized");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--incognito", "start-maximized");
            driver = new EdgeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--incognito", "start-maximized");
            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.openLandingPage();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
