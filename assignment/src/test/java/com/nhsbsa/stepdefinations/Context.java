package com.nhsbsa.stepdefinations;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.utils.ConfigurationManager;
import com.nhsbsa.utils.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Context {
    protected WebDriver driver;
    private boolean initialized = false;
    protected WebDriverWait wait;
    protected ConfigurationManager configs = ConfigurationManager.getInstance();
    private final Duration implicitWaitTime = Duration
            .ofSeconds(Integer.parseInt(configs.getProperty("implicitWaitTimeOut")));
    private final Duration explicitWaitTime = Duration
            .ofSeconds(Integer.parseInt(configs.getProperty("explicitWaitTimeOut")));

    @Before
    public void setup() {
        if (initialized)
            return;
        DriverManager driverManager = new DriverManager();
        this.driver = driverManager
                .getWebDriver(
                        configs.getProperty("browser"),
                        Boolean.parseBoolean(configs.getProperty("headless")));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime);
        initialized = true;
    }

    @After
    public void quitDriver() {
        driver.quit();
        initialized = false;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, explicitWaitTime);
    }
}
