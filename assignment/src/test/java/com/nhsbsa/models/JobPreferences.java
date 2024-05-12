package com.nhsbsa.models;

public class JobPreferences {
    private String jobKeyword;
    private String jobLocations;
    private String jobDistance;
    private String jobReference;
    private String employer;
    private String payRange;
    
    public JobPreferences(String jobKeyword, String jobLocations, String jobDistance, String jobReference, String employer, String payRange) {
        this.jobKeyword = jobKeyword;
        this.jobLocations = jobLocations;
        this.jobDistance = jobDistance;
        this.jobReference = jobReference;
        this.employer = employer;
        this.payRange = payRange;
    }
    public JobPreferences(JobPreferenceBuilder builder){
        this.jobKeyword = builder.getJobKeyword();
        this.jobLocations = builder.getJobLocations();
        this.jobDistance = builder.getJobDistance();
        this.jobReference = builder.getJobReference();
        this.employer = builder.getEmployer();
        this.payRange = builder.getPayRange();
    }
    
    public String getJobKeyword() {
        return jobKeyword;
    }

    public String getJobLocations() {
        return jobLocations;
    }

    public String getJobDistance() {
        return jobDistance;
    }

    public String getJobReference() {
        return jobReference;
    }

    public String getEmployer() {
        return employer;
    }

    public String getPayRange() {
        return payRange;
    }
}
