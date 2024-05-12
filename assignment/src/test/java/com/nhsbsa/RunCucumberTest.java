package com.nhsbsa;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/com/nhsbsa/features"}, 
    glue = {"com.nhsbsa.stepdefinations"},
    plugin = {"pretty"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    
}