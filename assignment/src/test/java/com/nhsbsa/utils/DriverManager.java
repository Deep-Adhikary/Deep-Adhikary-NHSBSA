package com.nhsbsa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private WebDriver driver;
    private URL gridUrl;
    private ConfigurationManager configs = ConfigurationManager.getInstance();

    public DriverManager() {
        try {
            gridUrl = new URL(configs.getProperty("seleniumRemoteUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private void createChromeLocal(boolean isHeadless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (isHeadless) {
            chromeOptions.addArguments("--headless=new");
        }
        driver = new ChromeDriver(chromeOptions);
    }

    private void createChromeRemote(boolean isHeadless) {

        ChromeOptions chromeOptions = new ChromeOptions();
        if (isHeadless) {
            chromeOptions.addArguments("--headless=new");
        }
        driver = new RemoteWebDriver(gridUrl, chromeOptions);
    }

    private void createFirefoxRemote(boolean isHeadless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (isHeadless) {
            firefoxOptions.addArguments("--headless");
        }
        driver = new RemoteWebDriver(gridUrl, firefoxOptions);
    }

    public WebDriver getWebDriver() {
        driver = getWebDriver("chrome:local", false);
        return driver;
    }

    public WebDriver getWebDriver(String browser) {
        driver = getWebDriver(browser, false);
        return driver;
    }

    public WebDriver getWebDriver(String browser, boolean isHeadless) {
        if (browser.equalsIgnoreCase("chrome:local")) {
            createChromeLocal(isHeadless);
        }
        if (browser.equalsIgnoreCase("chrome:remote")) {
            createChromeRemote(isHeadless);
        }
        if (browser.equalsIgnoreCase("firefox:remote")) {
            createFirefoxRemote(isHeadless);
        }
        return driver;
    }
}