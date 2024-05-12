package com.nhsbsa.stepdefinations;


import com.nhsbsa.actions.FindAJobActions;
import com.nhsbsa.base.BaseStepDefinations;
import com.nhsbsa.utils.DriverManager;

import io.cucumber.java.en.*;

public class FindAJob extends BaseStepDefinations {
    private final FindAJobActions findAJobActions;
    public FindAJob(DriverManager driverManager) {
        super(driverManager);
        findAJobActions=new FindAJobActions(driver,wait);
        
    }

    @Given("an example scenario")
    public void anExampleScenario() {
        findAJobActions.navigateToJobSearch();
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
        driver.quit();
       
    }

}
