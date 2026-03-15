package com.vatsal.tests;

import com.vatsal.pages.DashboardPage;
import com.vatsal.pages.LoginPage;
import com.vatsal.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest: Test suite for Login functionality.
 * Covers: valid login, invalid login, locked-out user, empty credentials.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Valid user can login successfully")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();

        DashboardPage dashboard = loginPage.loginAs(
            ConfigReader.getValidUsername(),
            ConfigReader.getValidPassword()
        );

        Assert.assertTrue(dashboard.isOnDashboard(),
            "Expected to be on Dashboard after valid login");
    }

    @Test(description = "Invalid credentials show error message")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Expected error message for invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
            "Unexpected error message: " + loginPage.getErrorMessage());
    }

    @Test(description = "Locked-out user sees appropriate error")
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Expected error for locked out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
            "Expected 'locked out' error message");
    }

    @Test(description = "Empty credentials show error message")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Expected error for empty credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
            "Expected 'Username is required' error");
    }

    @Test(description = "User can logout successfully after login")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();

        DashboardPage dashboard = loginPage.loginAs(
            ConfigReader.getValidUsername(),
            ConfigReader.getValidPassword()
        );

        Assert.assertTrue(dashboard.isOnDashboard(), "Login failed before logout test");

        LoginPage backToLogin = dashboard.logout();
        Assert.assertEquals(backToLogin.getPageTitle(), "Swag Labs",
            "Expected to return to Login page after logout");
    }
}
