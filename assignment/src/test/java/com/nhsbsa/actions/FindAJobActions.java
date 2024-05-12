package com.nhsbsa.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.nhsbsa.base.Actions;
import com.nhsbsa.base.FindAJobElements;

import static org.testng.Assert.*;
public class FindAJobActions extends Actions {
    private final FindAJobElements findAJobElements;

    public FindAJobActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        findAJobElements=new FindAJobElements(driver);
    }

    public void navigateToJobSearch() {
        driver.navigate().to(baseUrl + "/candidate/search");
        assertTrue(findAJobElements.getPageHeading().isDisplayed());
    }
}
