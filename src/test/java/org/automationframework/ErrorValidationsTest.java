package org.automationframework;

import org.automationframework.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"Error Handling"})
    public void verifyIncorrectUsernameErrorMessage() {

        landingPage.loginApplication("123412345@gmail.com", "Akash@123");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }

    @Test(groups = {"Error Handling"})
    public void verifyIncorrectPasswordErrorMessage() {

        landingPage.loginApplication("akash12345@gmail.com", "dfgg@123");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
}
