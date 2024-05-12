package com.nhsbsa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.utils.ConfigurationManager;
import com.nhsbsa.utils.DriverManager;

public class BaseStepDefinations {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigurationManager configs;
    protected BaseStepDefinations(DriverManager driverManager){
        this.configs=ConfigurationManager.getInstance();
        this.driver=driverManager
        .getWebDriver(
            configs.getProperty("browser"), 
            Boolean.parseBoolean(configs.getProperty("headless")));
        this.wait=driverManager.getWebDriverWait();
    }
}
