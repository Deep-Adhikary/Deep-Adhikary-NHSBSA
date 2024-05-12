package com.nhsbsa.models;

public class JobPreferenceBuilder {
    private String jobKeyword = "";
    private String jobLocations = "";
    private String jobDistance = "";
    private String jobReference = "";
    private String employer = "";
    private String payRange = "";


    public JobPreferenceBuilder jobKeyword(String jobKeyword) {
        this.jobKeyword = jobKeyword;
        return this;
    }

    public JobPreferenceBuilder jobLocations(String jobLocations) {
        this.jobLocations = jobLocations;
        return this;
    }

    public JobPreferenceBuilder jobDistance(String jobDistance) {
        this.jobDistance = jobDistance;
        return this;
    }

    public JobPreferenceBuilder jobReference(String jobReference) {
        this.jobReference = jobReference;
        return this;
    }

    public JobPreferenceBuilder employer(String employer) {
        this.employer = employer;
        return this;
    }

    public JobPreferenceBuilder payRange(String payRange) {
        this.payRange = payRange;
        return this;
    }

    public JobPreferences build() {
        return new JobPreferences(this);
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
