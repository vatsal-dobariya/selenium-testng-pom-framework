package com.vatsal.tests;

import com.vatsal.pages.DashboardPage;
import com.vatsal.pages.LoginPage;
import com.vatsal.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * DashboardTest: Test suite for Dashboard / Inventory page functionality.
 */
public class DashboardTest extends BaseTest {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        dashboardPage = loginPage.loginAs(
            ConfigReader.getValidUsername(),
            ConfigReader.getValidPassword()
        );
    }

    @Test(description = "Dashboard shows correct page title")
    public void testDashboardTitle() {
        Assert.assertTrue(dashboardPage.isOnDashboard(),
            "Page title should be 'Products'");
    }

    @Test(description = "Dashboard displays 6 products")
    public void testProductCount() {
        Assert.assertEquals(dashboardPage.getProductCount(), 6,
            "Expected 6 products on the inventory page");
    }

    @Test(description = "First product name is not empty")
    public void testFirstProductName() {
        String name = dashboardPage.getFirstProductName();
        Assert.assertNotNull(name, "Product name should not be null");
        Assert.assertFalse(name.isEmpty(), "Product name should not be empty");
    }

    @Test(description = "Cart count starts at zero")
    public void testCartInitialCount() {
        Assert.assertEquals(dashboardPage.getCartBadgeCount(), "0",
            "Cart should be empty on fresh login");
    }
}
