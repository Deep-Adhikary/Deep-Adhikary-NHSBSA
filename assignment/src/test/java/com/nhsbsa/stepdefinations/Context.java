package com.nhsbsa.stepdefinations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.utils.ConfigurationManager;
import com.nhsbsa.utils.DriverManager;
import com.nhsbsa.utils.PathUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Context {
    protected WebDriver driver;
    private boolean initialized = false;
    protected WebDriverWait wait;
    protected ConfigurationManager configs = ConfigurationManager.getInstance();
    private final Duration implicitWaitTime = Duration
            .ofSeconds(Integer.parseInt(configs.getProperty("implicitWaitTimeOut")));
    private final Duration explicitWaitTime = Duration
            .ofSeconds(Integer.parseInt(configs.getProperty("explicitWaitTimeOut")));
    private static Path runPath;
    private Path screenShotPath;
    private static DateTimeFormatter screenShotFormat;
    @BeforeAll
    public static void suiteSetup(){
        PathUtil pathUtil=new PathUtil();
        runPath=pathUtil.getRunRootDirectory();
        screenShotFormat=pathUtil.getScreenShotFormat();
    }
    @Before
    public void setup(Scenario scenario) {
        if (initialized)
            return;
        screenShotPath= new PathUtil().getScreenShotPath(runPath, scenario.getName());
        DriverManager driverManager = new DriverManager();
        this.driver = driverManager
                .getWebDriver(
                        configs.getProperty("browser"),
                        Boolean.parseBoolean(configs.getProperty("headless")));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime);
        driver.manage().window().maximize();
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
    public Path getScreenShotPath(){
        return screenShotPath;
    }
    public DateTimeFormatter getScreenShotFormat(){
        return screenShotFormat;
    }

    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, explicitWaitTime);
    }
    
}
