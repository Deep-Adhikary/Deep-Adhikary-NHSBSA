Feature: Job seekers should able to search jobs in NHS jobs website

  Scenario: Job seekers serach jobs with preference
    Given a job seeker opens NHS jobs website
     When they put their preferences into the search functionality
      And they search jobs
     Then they should get list of jobs which matches their preference
      And they should able to sort search results with 'Date Posted (newest)'
