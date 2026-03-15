package com.vatsal.tests;

import com.vatsal.utils.ConfigReader;
import com.vatsal.utils.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest: Parent class for all test classes.
 * Handles driver init/teardown and provides shared test utilities.
 */
public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", ConfigReader.getBrowser());
        boolean headless = Boolean.parseBoolean(
            System.getProperty("headless", String.valueOf(ConfigReader.isHeadless()))
        );
        logger.info("Initializing {} driver (headless={})", browser, headless);
        DriverManager.initDriver(browser, headless);
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quitting driver");
        DriverManager.quitDriver();
    }
}
