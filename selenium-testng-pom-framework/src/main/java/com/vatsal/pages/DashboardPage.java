package com.vatsal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * DashboardPage: Page Object for the Sauce Demo products/inventory page.
 */
public class DashboardPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnDashboard() {
        return getText(pageTitle).equalsIgnoreCase("Products");
    }

    public int getProductCount() {
        return productItems.size();
    }

    public String getFirstProductName() {
        return getText(productNames.get(0));
    }

    public void openMenu() {
        click(menuButton);
    }

    public LoginPage logout() {
        openMenu();
        waitForClickable(logoutLink);
        click(logoutLink);
        return new LoginPage(driver);
    }

    public String getCartBadgeCount() {
        try {
            WebElement badge = driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_badge")
            );
            return badge.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "0";
        }
    }
}
