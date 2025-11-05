Feature: Consumer digital and branch flow

#  @flow=digital
#  Scenario Outline: Happy consumer flow of single and multiple applicants scenario "<submissionId>"
#  Given : I am at the home page
#  When : I click on the consumer tab
#  Then : I click on product for "<submissionId>"
#  And : I click on the checkout
#  Then : I click on proceed without prefill
#  Then : I should navigate to getting started page
#  When : I provide the following details for "<submissionId>"
#  And : I click on start Application
#  And : I should navigate to personal details page
#  When : I provide the below personal details for "<submissionId>"
#  And : I provide the below additional applicant details for "<submissionId>"
#  Then : I click on personal details next button
#  Then : I close promotion close button
#  Then : I click on the overdraft Coverage no checkbox
#  Then : I click on the account details next button
#  And : I should see the funding page
#  Then : I provide below funding details for "<submissionId>"
#  Then : I click on funding details next button
#  Then : I should accept the all terms and conditions 3
#  And : I click on review details submit button
#  And : I click on signable disclosures button
#  Then : I should see the reference Id page.
#
#  Examples:
#    | submissionId |
#    | 1            |
#    | 2            |
#    | 3            |
#
#  @flow=digital
#  Scenario Outline: Consumer flow personal info cancel scenario "<submissionId>"
#    Given : I am at the home page
#    When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on proceed without prefill
#    Then : I should navigate to getting started page
#    When : I provide the following details for "<submissionId>"
#    And : I click on start Application
#    And : I should navigate to personal details page
#    When : I provide the below personal details for "<submissionId>"
#    And : I provide the below additional applicant details for "<submissionId>"
#    Then : I click on personal details cancel button
#
#    Examples:
#      | submissionId |
#      | 1            |
#
#  @flow=branch
#  Scenario Outline: Happy branch flow of single applicant scenario "<submissionId>"
#    Given : I am at the home page
#    When : I provide my salesforce account details
#    When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on Add New
#    And : I should navigate to personal details page
#    When : I provide the below personal details for "<submissionId>"
#    And : I provide the below branch additional applicant details for "<submissionId>"
#    Then : I click on personal details next button
#    Then : I click on Application Verification next button
#    Then : I close promotion close button
#    Then : I click on the overdraft Coverage no checkbox
#    Then : I click on the account details next button
#    And : I should see the funding page
#    Then : I provide below funding details for "<submissionId>"
#    Then : I click on funding details next button
#    Then : I should accept the all terms and conditions 3
#    And : I click on review details submit button
#    And : I click on signable disclosures button
#    Then : I should see the reference Id page.
#
#    Examples:
#      | submissionId |
#      | 1            |

  @flow=digital
  Scenario Outline: Save the application of single applicant scenario "<submissionId>"
    Given : I am at the home page
#    When : I click on the consumer tab
    Then : I click on product for "<submissionId>"
    And : I click on the checkout
  Then : I click on the open an account
  #Then : I click on proceed without prefill
    Then : I should navigate to getting started page
    When : I provide the following details for "<submissionId>"
#  Then : I select the accept option
    And : I click on start Application
    And : I should navigate to personal details page
    When : I provide the below personal details for "<submissionId>"
    And : I provide the below additional applicant details for "<submissionId>"
    Then : I click on personal details save button
    Examples:
      | submissionId |
      | 1         |