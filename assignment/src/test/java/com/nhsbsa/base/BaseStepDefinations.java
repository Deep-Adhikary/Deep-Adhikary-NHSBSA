package com.nhsbsa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.stepdefinations.Context;
import com.nhsbsa.utils.ConfigurationManager;

public class BaseStepDefinations {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigurationManager configs;
    protected BaseStepDefinations(Context context){
        this.configs=ConfigurationManager.getInstance();
        this.driver=context
        .getDriver();
        this.wait=context.getWebDriverWait();
    }
    
}
