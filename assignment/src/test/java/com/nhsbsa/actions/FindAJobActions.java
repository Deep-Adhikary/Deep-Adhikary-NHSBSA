package com.nhsbsa.actions;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.nhsbsa.base.Actions;
import com.nhsbsa.elements.FindAJobElements;
import com.nhsbsa.models.JobPreferences;
import com.nhsbsa.stepdefinations.Context;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.*;

public class FindAJobActions extends Actions {
    private final FindAJobElements findAJobElements;

    public FindAJobActions(Context context) {
        super(context);
        findAJobElements = new FindAJobElements(driver);
    }

    public void navigateToJobSearch() {
        driver.navigate().to(baseUrl + "/candidate/search");
        assertTrue(findAJobElements.getPageHeading().isDisplayed());
        takeScreenShotToFile();
    }

    private void provideLocationPreference(String location) {
        if (location.equals(""))
            return;
        findAJobElements.getWhereInput().sendKeys(location);
        findAJobElements.getLocationDropDownItem(location).click();
    }

    public void applyPreferences(JobPreferences preferences) {

        scrollIntoView(findAJobElements.getWhatInput());

        findAJobElements.getWhatInput().sendKeys(preferences.getJobKeyword());
        this.provideLocationPreference(preferences.getJobLocations());

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
        scrollIntoView(findAJobElements.getSummaryDistance());
        pause(0.5);
        takeScreenShotToFile();
    }

    public void valiDateAtlLeastOneSearchResultsReturned() {

        assertNotEquals(findAJobElements.getSearchResults().size(), 0);
        assertTrue(
                Integer.parseInt(findAJobElements.getSearchResultsCount().getText()) > 0);
        scrollIntoView(findAJobElements.getSearchResultsCount());
        pause(0.5);
        scrollIntoView(findAJobElements.getSearchResults().get(0));

        takeScreenShotToFile();
    }

    public void sortSearchResult(String sortBy) {
        WebElement firstResult = findAJobElements.getSearchResults().get(0);
        findAJobElements.getSortByDropDown().selectByVisibleText(sortBy);
        wait.until(ExpectedConditions.stalenessOf(firstResult));
        firstResult = findAJobElements.getSearchResults().get(0);
        scrollIntoView(firstResult);
        pause();
        takeScreenShotToFile();
    }

    public void verifyDataSortedByDatePostedNewest() {
        List<String> locators = findAJobElements
                .getSearchResults()
                .stream()
                .map(
                        searchedJob -> {
                            return searchedJob.findElement(findAJobElements.getSearchResultTitle()).getAttribute("href")
                                    .replace(baseUrl, "");
                        })
                .collect(Collectors.toList());
        List<LocalDate> dates = locators.stream().map(locator -> {
            DateTimeFormatter datePostedDateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

            WebElement element = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='" + locator + "']")));
            scrollIntoView(element).click();
            wait.until(ExpectedConditions.stalenessOf(element));

            LocalDate date = LocalDate.parse(
                    scrollIntoView(findAJobElements.getVisibleDatePostedLabel()).getAttribute("textContent"),
                    datePostedDateFormat);
            wait.until(ExpectedConditions.elementToBeClickable(findAJobElements.getBackToSearchButton())).click();
            wait.until(ExpectedConditions.visibilityOf(findAJobElements.getSearchResultsCount()));
            takeScreenShotToFile();
            return date;
        }).collect(Collectors.toList());

        Function<List<LocalDate>,Boolean> dateComparator= dateList->Ordering.<LocalDate> natural().reverse().isOrdered(dateList);
        assertTrue(dateComparator.apply(dates));
    }

    public void verifyNoResultsReturned() {
        scrollIntoView(findAJobElements.getNoResultsFoundText());
        assertTrue(findAJobElements.getNoResultsFoundText().isDisplayed());
        takeScreenShotToFile();
    }

}
