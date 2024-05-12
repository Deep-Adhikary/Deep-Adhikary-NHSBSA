package com.nhsbsa.stepdefinations;


import java.util.Map;

import com.nhsbsa.actions.FindAJobActions;
import com.nhsbsa.base.BaseStepDefinations;
import com.nhsbsa.models.JobPreferenceBuilder;
import com.nhsbsa.models.JobPreferences;

import io.cucumber.datatable.DataTable;
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
    public void applyPreferences(DataTable jobPreference) {
        Map<String,String> preferenceMap = jobPreference.asMaps(String.class, String.class).get(0);

        JobPreferences preferences=new JobPreferenceBuilder()
        .jobKeyword(preferenceMap.get("Keyword"))
        .jobLocations(preferenceMap.get("Location"))
        .jobDistance(preferenceMap.get("Distance"))
        .jobReference(preferenceMap.get("Reference"))
        .employer(preferenceMap.get("Employer"))
        .payRange(preferenceMap.get("Pay Range"))
        .build();
        findAJobActions.applyPreferences(preferences);
    }

    @When("they search jobs")
    public void performSearch() {
        findAJobActions.performSearch();
    }
    @Then("they should get list of jobs which matches their preference")
    public void searchSuccessfulAndResultsReturned(){
        findAJobActions.valiDateAtlLeastOneSearchResultsReturned();

    }
    @Then("they should able to sort search results with {string}")
    public void sortData(String sortBy){
        System.out.println(sortBy);
    }
   

}
