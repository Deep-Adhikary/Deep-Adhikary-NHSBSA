package com.nhsbsa.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.utils.ConfigurationManager;

public class Elements {
    private final ConfigurationManager configs;
    private final int timeOuts;
    protected final WebDriverWait wait;

    protected Elements(WebDriver driver) {
        configs = ConfigurationManager.getInstance();
        timeOuts = Integer.parseInt(configs.getProperty("ajaxElementTimeOut"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOuts), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOuts));
    }

    Elements(WebDriver driver, WebDriverWait wait) {
        configs = ConfigurationManager.getInstance();
        timeOuts = Integer.parseInt(configs.getProperty("ajaxElementTimeOut"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOuts), this);
        this.wait = wait;
    }
}