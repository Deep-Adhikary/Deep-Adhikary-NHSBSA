package com.nhsbsa.stepdefinations;


import com.nhsbsa.actions.FindAJobActions;
import com.nhsbsa.base.BaseStepDefinations;
import io.cucumber.java.en.*;

public class FindAJob extends BaseStepDefinations {
    private final FindAJobActions findAJobActions;
    public FindAJob(Context context) {
        super(context);
        findAJobActions=new FindAJobActions(driver,wait);       
    }

    @Given("a job seeker opens NHS jobs website")
    public void navigateToJobSearch() {
        findAJobActions.navigateToJobSearch();
    }

    @When("they put their preferences into the search functionality")
    public void applyPreferences() {
    }

    @When("they search jobs")
    public void performSearch() {
    }
    @Then("they should get list of jobs which matches their preference")
    public void searchSuccessfulAndResultsReturned(){

    }
    @Then("they should able to sort search results with {string}")
    public void sortData(String sortBy){
        System.out.println(sortBy);
    }
   

}
