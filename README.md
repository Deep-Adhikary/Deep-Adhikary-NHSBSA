# Deep-Adhikary-NHSBSA

This repository is for NHSBSA Selenium Assignment.

## Assignment Details

### User Story

```txt
As a jobseeker on NHS Jobs website
I want to search for a job with my preferences
So that I can get recently posted job results
```

### Test Ticket - Acceptance Criteria

```gherkin
Given I am a jobseeker on NHS Jobs website
When I put my preferences into the Search functionality
Then I should get a list of jobs which matches my preferences
And sort my search results with the newest Date Posted
```

### Assignment Task

Create a Functional Acceptance automation test suite for the above NHS Jobs Ticket
using `User Centric BDD`, `Java (11+)` &amp; `Selenium`.

****Use the NHS Jobs Search available below in your tests:****

<https://www.jobs.nhs.uk/candidate/search>

### Test Case Coverage

1. **Job Seekers Search Jobs Without Any Preferences**
   - Verify that job seekers can search for jobs without providing any preferences and that relevant results are displayed.

2. **Job Seekers Search Jobs by Only `Keyword`**
   - Verify that job seekers can search for jobs by providing only a keyword and that relevant results are displayed.

3. **Job Seekers Search Jobs by Only `Location`**
   - Verify that job seekers can search for jobs by providing only a location and that relevant results are displayed.

4. **Job Seekers Search Jobs by Only `Job Reference`**
   - Verify that job seekers can search for jobs by providing only a job reference and that relevant results are displayed.

5. **Job Seekers Search Jobs by Only `Employer Name`**
   - Verify that job seekers can search for jobs by providing only an employer name and that relevant results are displayed.

6. **Job Seekers Search Jobs by Only `Pay Range`**
   - Verify that job seekers can search for jobs by providing only a pay range and that relevant results are displayed.

7. **Job Seekers Search Jobs Using All Preferences**
   - Verify that job seekers can search for jobs by providing all available preferences (keyword, location, job reference, employer name, and pay range) and that relevant results are displayed.

8. **Verify `No Results` are Returned for Invalid Preferences**
   - Verify that no results are returned when job seekers search with invalid preferences.

9. **Verify Jobs Can Be Sorted by `Date Posted (Newest)`**
   - Verify that the jobs searched can be sorted by the date posted, with the newest jobs appearing first.

## Framework Details

The framework is a BDD (Behavior-Driven Development) framework implemented using Selenium and Java to design tests as per the instructions provided in assignments. It leverages the power of BDD to ensure that tests are written in a clear and understandable format, making it easier for all stakeholders to comprehend the test scenarios.

This comprehensive framework ensures robust and maintainable test automation, enhancing the overall efficiency and reliability of the testing process.

### Key Features

- **Data-Driven Testing**: Utilizes `Scenario Outline` to perform data-driven testing, allowing the execution of the same test scenario with different sets of test data.
- **Design Patterns**: Implements `Action` and `Object Repository` design patterns for enhanced reusability and maintainability. The `Singleton` design pattern is used to pass properties across the framework, while the `Builder` design pattern is used for effective handling of job preferences.
- **Dependency Injection**: Uses Dependency Injection with `PicoContainer` to pass driver instances between scenarios, ensuring better management of driver life cycles.
- **OOP Principles**: Leverages Object-Oriented Programming concepts like `Inheritance` and `Polymorphism` to write effective and clean code.
- **Synchronization**: Utilizes the `Explicit Wait` object synchronization strategy to reduce test flakiness.
- **Browser Support**: Supports test execution in both `Chrome` and `Firefox` browsers, in both headed and headless modes.
- **Remote Execution**: Capable of executing scripts on remote Selenium drivers.
- **Parallel Execution**: Supports parallel execution to speed up the testing process.
- **Screenshot Capabilities**: Built-in capabilities to capture screenshots in both sequential and parallel modes of execution. Screenshots are stored in the `target` folder.

### Folder Structure

```bash
.
├── .gitignore
├── README.md
└── assignment
    ├── pom.xml
    ├── src
    │   └── test
    │       ├── java
    │       │   └── com
    │       │       └── nhsbsa
    │       │           ├── RunCucumberTest.java           # Cucumber Runner
    │       │           ├── actions                        # User Actions
    │       │           │   └── FindAJobActions.java
    │       │           ├── base                           # Base Implementation
    │       │           │   ├── Actions.java
    │       │           │   ├── BaseStepDefinations.java
    │       │           │   └── Elements.java
    │       │           ├── elements                       # Elements(Locators)
    │       │           │   └── FindAJobElements.java
    │       │           ├── models                         # Models for complex data
    │       │           │   ├── JobPreferenceBuilder.java
    │       │           │   └── JobPreferences.java
    │       │           ├── stepdefinations                # Step definitions
    │       │           │   ├── Context.java               # Context steps and hooks
    │       │           │   └── FindAJob.java
    │       │           └── utils                          # Utilities
    │       │               ├── ConfigurationManager.java
    │       │               ├── DriverManager.java
    │       │               └── PathUtil.java
    │       └── resources
    │           ├── com
    │           │   └── nhsbsa
    │           │       └── features                       # Features files
    │           │           └── findajob.feature
    │           └── config.properties                      # Configurations
    └── target                                             # Outputs 
```

### Dependencies

- **Java**: 16
- **Maven**: 3.9.6
- **Cucumber**: 7.16.1
- **Selenium**: 4.20.0

Please refer to `pom.xml` for more details.

## Execution Instructions

- Install and Configure `Java 16`.
- Install and Configure `Maven`.

### Update `config.properties` file

Based on execution requirement modify the execution config.

```properties
# supported options: chrome:remote | firefox:remote | chrome:local         
browser=<browser to execute>  
# headless or headed     
headless=true
# Default selenium standalone server address. Can be replaced with remote one
seleniumRemoteUrl=http://localhost:4444  
# Base url
appBaseUrl=https://www.jobs.nhs.uk
# Timeouts
implicitWaitTimeOut=10
explicitWaitTimeOut=10
ajaxElementTimeOut=10
defaultPauseTime=1
```

### Update run configuration in `RunCucumberTest.java` as required

```java
@CucumberOptions(
    features = {"src/test/resources/com/nhsbsa/features"},  
    glue = {"com.nhsbsa.stepdefinations"},
    tags = "@findajob and not @skipped",
    plugin = {"pretty","html:target/cucumber-reports.html"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true) //change to false for sequential execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
```

### Install framework dependencies and execute tests

```bash
mvn install
```

## Results

- The framework generate results in `html` format using built-in html plugin of `cucumber`.

![Html Report Snippet](./assets/cucumber-report%20snippet.png?raw=true "Sample Execution Report")

- Screenshots are saved in `target/screenshot folder`.

### `target` folder structure

``` bash
assignment/target
├── generated-test-sources
│   └── test-annotations
├── maven-archiver
├── maven-status
│   └── maven-compiler-plugin
│       └── testCompile
│           └── default-testCompile
├── screenshots
│   └── 2024_08_15_00_08_15_7
│       ├── Job seekers search jobs with All Preference Applied
│       ├── Job seekers search jobs with No Preference
│       ├── Job seekers search jobs with Only Employer Name
│       ├── Job seekers search jobs with Only Job Reference
│       ├── Job seekers search jobs with Only Keyword
│       ├── Job seekers search jobs with Only Location
│       ├── Job seekers search jobs with Only Pay Range
│       ├── Job seekers search jobs with invalid preference
│       └── Verify jobs are being sorted based on 'Date Posted(newest)'
├── surefire-reports
│   ├── Surefire suite
│   └── junitreports
└── test-classes
```
