package com.nhsbsa.base;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.utils.ConfigurationManager;

public class Actions {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final ConfigurationManager configs;
    protected final String baseUrl;
    private final int defaultPauseTime;
    private final Path screenShotPath = Paths.get(
            FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
            "target", "screenshots");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_mm_dd_HH_mm_ss_S");

    protected Actions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        configs = ConfigurationManager.getInstance();
        baseUrl = configs.getProperty("appBaseUrl");
        defaultPauseTime=Integer.parseInt(configs.getProperty("defaultPauseTime"));
        createScreenshotDirectory();

    }

    protected void takeScreenShotToFile() {
        byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try {
            Files.write(
                    Paths.get(
                            screenShotPath.toString(),
                            LocalDateTime.now().format(formatter) + ".png"),
                    screenshotData);
            System.out.println("Screenshot saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void selectByTextIfNotEmpty(Select element, String text, String selectBy) {
        if (text.equalsIgnoreCase(""))
            return;
        if (selectBy.equalsIgnoreCase("value")) {
            element.selectByValue(text);
            return;
        }
        element.selectByVisibleText(text);
    }
    protected void scrollIntoView(WebElement element){
        // Javascript executor
      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    protected void pause(){
        pause(defaultPauseTime);
    }
    protected void pause(double timeOutInSecond){
        try {
            int st=(int)timeOutInSecond*1000;
            Thread.sleep(st);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createScreenshotDirectory() {
        try {
            if (!Files.isDirectory(screenShotPath)) {
                System.out.println("Creating Directory");
                Files.createDirectory(screenShotPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
