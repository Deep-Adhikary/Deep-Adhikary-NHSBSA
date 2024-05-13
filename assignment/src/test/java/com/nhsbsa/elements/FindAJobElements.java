package com.nhsbsa.elements;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.nhsbsa.base.Elements;

public class FindAJobElements extends Elements {

    @FindBy(xpath = "//*[@id='heading']/h1")
    private WebElement pageHeading;

    @FindBy(css = "[data-test='search-jobTitle-input']")
    private WebElement whatInput;

    @FindBy(css = "[data-test='search-location-input']")
    private WebElement whereInput;

    @FindBy(id = "location__listbox")
    private WebElement locationDropDownList;

    @FindBy(css = "[data-test='search-distance-input']")
    private WebElement distanceSelect;

    @FindBy(css = "[data-test='search-jobReference-input']")
    private WebElement jobReferenceInput;

    @FindBy(css = "[data-test='search-employer-input']")
    private WebElement employerInput;

    @FindBy(css = "[data-test='search-payRange-input']")
    private WebElement payRangeSelect;

    @FindBy(css = "[data-test='clear-filters-button']")
    private WebElement clearFiltersButton;

    @FindBy(id = "searchOptionsBtn")
    private WebElement searchOptionsToggle;

    @FindBy(css = "[data-test='search-button']")
    private WebElement searchButton;

    // Search Results Page
    @FindBy(css = "[data-test='sort-input']")
    private WebElement sortResultSelect;

    @FindBy(css = "[data-test='search-results-count']")
    private WebElement searchResultsCount;

    @FindBy(css = "[data-test='search-result']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//*[.='No result found']")
    private WebElement noResultsFoundText;

    @FindBy(css = "summary[data-test='distance']")
    private WebElement summaryDistance;

    @FindBy(id = "back-link")
    private WebElement back_button;

    // Dynamic/ Child Locator
    private final By searchResultTitle = By.cssSelector("[data-test='search-result-job-title']");
    private final By datePostedLabels = By.id("date_posted");

    public FindAJobElements(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOf(pageHeading));
    }

    public WebElement getWhatInput() {
        return wait.until(ExpectedConditions.visibilityOf(whatInput));
    }

    public WebElement getWhereInput() {
        return wait.until(ExpectedConditions.visibilityOf(whereInput));
    }

    public WebElement getLocationDropDownItem(String item) {
        return wait.until(ExpectedConditions.visibilityOf(locationDropDownList))
                .findElements(By.tagName("li")).stream().filter(element -> {
                    return element.getText().equalsIgnoreCase(item);
                }).collect(Collectors.toList()).get(0);
    }

    public Select getDistanceDropDown() {
        return new Select(wait.until(ExpectedConditions.visibilityOf(distanceSelect)));
    }

    public WebElement getJobReferenceInput() {
        return wait.until(ExpectedConditions.visibilityOf(jobReferenceInput));
    }

    public WebElement getEmployerInput() {
        return wait.until(ExpectedConditions.visibilityOf(employerInput));
    }

    public Select getPayRangeDropDown() {
        return new Select(wait.until(ExpectedConditions.visibilityOf(payRangeSelect)));
    }

    public WebElement getSearchOptionsToggle() {
        return wait.until(ExpectedConditions.visibilityOf(searchOptionsToggle));
    }

    public WebElement getClearFiltersButton() {
        return wait.until(ExpectedConditions.visibilityOf(clearFiltersButton));
    }

    public WebElement getSearchButton() {
        return wait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    public Select getSortByDropDown() {
        return new Select(wait.until(ExpectedConditions.visibilityOf(sortResultSelect)));
    }

    public WebElement getSearchResultsCount() {
        return wait.until(ExpectedConditions.visibilityOf(searchResultsCount));
    }

    public List<WebElement> getSearchResults() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
    }

    public WebElement getSummaryDistance() {
        return wait.until(ExpectedConditions.visibilityOf(summaryDistance));
    }

    public WebElement getNoResultsFoundText() {
        return noResultsFoundText;
    }

    public WebElement getVisibleDatePostedLabel() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(datePostedLabels)).stream()
                .filter(element -> element.isDisplayed()).toList().get(0);
    }

    public By getSearchResultTitle() {
        return searchResultTitle;
    }

    public WebElement getBackToSearchButton() {
        return back_button;
    }

}
