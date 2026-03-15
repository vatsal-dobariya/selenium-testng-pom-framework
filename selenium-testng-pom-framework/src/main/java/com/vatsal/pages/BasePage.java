package com.vatsal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage: Parent class for all Page Objects.
 * Provides shared WebDriver utilities and explicit wait helpers.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private static final int DEFAULT_WAIT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(WebElement element) {
        logger.debug("Waiting for element visibility: {}", element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable: {}", element);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForClickable(element).click();
        logger.info("Clicked on element: {}", element);
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element).clear();
        element.sendKeys(text);
        logger.info("Typed '{}' into element", text);
    }

    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
