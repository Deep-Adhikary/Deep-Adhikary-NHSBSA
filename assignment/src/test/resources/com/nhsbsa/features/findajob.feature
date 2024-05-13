Feature: Job seekers should able to search jobs in NHS jobs website

  Scenario Outline: Job seekers serach jobs with <preference type>
    Given a job seeker opens NHS jobs website
    When they put their preferences into the search functionality
      | Keyword | Location | Distance   | Reference   | Employer        | Pay Range      |
      | <what>  | <where>  | <distance> | <reference> | <employer name> | <salary range> |
    And they search jobs
    Then they should get list of jobs which matches their preference

    Examples:
      | preference type        | what                | where  | distance   | reference     | employer name                            | salary range       |
      | No Preference          |                     |        |            |               |                                          |                    |
      | Only Keyword           | Pharmacy Technician |        |            |               |                                          |                    |
      | Only Location          |                     | London | +100 Miles |               |                                          |                    |
      | Only Job Reference     |                     |        |            | E0365-23-1130 |                                          |                    |
      | Only Employer Name     |                     |        |            |               | NHS Business Service Authority           |                    |
      | Only Pay Range         |                     |        |            |               |                                          | £40,000 to £50,000 |
      | All Preference Applied | Pharmacy Technician | London |   +5 Miles | E0365-23-1130 | Ramsay Health Care UK Operations Limited | £20,000 to £30,000 |

  Scenario: Job seekers serach jobs with invalid preference
    Given a job seeker opens NHS jobs website
    When they put their preferences into the search functionality
      | Keyword | Location | Distance | Reference | Employer | Pay Range |
      | asdfgh  |          |          | sdfsfd    |          |           |
    And they search jobs
    Then no job result will be return

  Scenario: Verify jobs are being sorted based on 'Date Posted(newest)
    Given a job seeker opens NHS jobs website
    When they put their preferences into the search functionality
      | Keyword       | Location | Distance | Reference | Employer                       | Pay Range |
      | Automation QA | London   |          |           | NHS Business Service Authority |           |
    And they search jobs
    And they should able to sort search results with 'Date Posted (newest)'

  Scenario:
