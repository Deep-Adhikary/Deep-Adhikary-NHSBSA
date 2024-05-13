package com.nhsbsa;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/com/nhsbsa/features"}, 
    glue = {"com.nhsbsa.stepdefinations"},
    tags = "@findajob and not @skipped",
    plugin = {"pretty","html:target/cucumber-reports.html"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}