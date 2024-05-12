package com.nhsbsa.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nhsbsa.base.Actions;
import com.nhsbsa.base.FindAJobElements;
import com.nhsbsa.models.JobPreferences;

import static org.testng.Assert.*;

public class FindAJobActions extends Actions {
    private final FindAJobElements findAJobElements;

    public FindAJobActions(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        findAJobElements = new FindAJobElements(driver);
    }

    public void navigateToJobSearch() {
        driver.navigate().to(baseUrl + "/candidate/search");
        assertTrue(findAJobElements.getPageHeading().isDisplayed());
        takeScreenShotToFile();
    }

    public void applyPreferences(JobPreferences preferences) {

        findAJobElements.getWhatInput().sendKeys(preferences.getJobKeyword());
        findAJobElements.getWhereInput().sendKeys(preferences.getJobLocations());
        findAJobElements.getLocationDropDownItem(preferences.getJobLocations()).click();

        selectByTextIfNotEmpty(findAJobElements.getDistanceDropDown(),
                preferences.getJobDistance(), "visibletext");

        Boolean isMoreOptionsProvided = !preferences.getJobReference().equals("")
                || !preferences.getEmployer().equals("")
                || !preferences.getPayRange().equals("");

        if (isMoreOptionsProvided) {
            findAJobElements.getSearchOptionsToggle().click();
            wait.until(ExpectedConditions.textToBePresentInElement(
                    findAJobElements.getSearchOptionsToggle(), "Fewer search options"));

            findAJobElements.getJobReferenceInput().sendKeys(preferences.getJobReference());
            findAJobElements.getEmployerInput().sendKeys(preferences.getEmployer());

            selectByTextIfNotEmpty(findAJobElements.getPayRangeDropDown(),
                    preferences.getPayRange(), "visibletext");
            takeScreenShotToFile();
        }
    }

    public void performSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(findAJobElements.getSearchButton())).click();
        assertTrue(findAJobElements.getSummaryDistance().isDisplayed());
        takeScreenShotToFile();
    }

    public void valiDateAtlLeastOneSearchResultsReturned() {
        
        assertNotEquals(findAJobElements.getSearchResults().size(), 0);
        assertTrue(
            Integer.parseInt(findAJobElements.getSearchResultsCount().getText())>0);
        scrollIntoView(findAJobElements.getSearchResults().get(0));
        takeScreenShotToFile();
    }
}
