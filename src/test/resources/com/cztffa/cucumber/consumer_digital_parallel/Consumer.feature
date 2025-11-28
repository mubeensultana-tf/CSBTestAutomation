Feature: Consumer digital and branch flow



  @flow=digital
  Scenario Outline: Happy flow of single applicant scenario "<submissionId>"
    Given : I am at the home page
#   // When : I click on the consumer tab
    Then : I click on product for "<submissionId>"
    And : I click on the checkout
    Then : I click on continue button
    Then : I click on the open an account
    Then : I should navigate to getting started page
    Then : I should select state from dropdown
    When : I provide the following details for "<submissionId>"
    And : I click on start Application
    And : I should navigate to personal details page
    When : I provide the below personal details for "<submissionId>"
    Then : I click on personal details next button
    Then  : I should see the account details page
#   Then : I provide below account details for "<submissionId>"
    And : I should see the funding page
    Then : I provide below funding details for "<submissionId>"
    Then : I click on funding details next button
    Then : I should see the consumer documents page
    Then : I upload requested documents
#    Then : I click on documents page next button
    Then : I should accept the all terms and conditions 3
    And : I click on review details submit button
    Then : I should see the reference Id page.
#    Then : I complete OLB registration for primary applicant
    Examples:
      | submissionId |
      | 1         |

#  joint party submission
#  @flow=digital
#  Scenario Outline: Happy flow of two applicant scenario "<submissionId>"
#    Given : I am at the home page
##   // When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on continue button
#    Then : I click on the open an account
#    Then : I should navigate to getting started page
#    Then : I should select state from dropdown
#    When : I provide the following details for "<submissionId>"
##  Then : I select the accept option
#    And : I click on start Application
#    And : I should navigate to personal details page
#    When : I provide the below personal details for "<submissionId>"
##   Then : I click on add additional applicant button
#    Then : I provide the below additional applicant details for "<submissionId>"
#    Then : I click on personal details next button
#   Then  : I should see the account details page
##   Then : I provide below account details for "<submissionId>"
#    And : I should see the funding page
#    Then : I provide below funding details for "<submissionId>"
#    Then : I click on funding details next button
#    Then : I should see the consumer documents page
#    Then : I upload requested documents
#    Then : I click on documents page next button
##    Then : I should see the review page.
#    Then : I should accept the all terms and conditions 3
#    And : I click on review details submit button
#  #And : I click on signable disclosures button
#    Then : I should see the reference Id page.
#    Then : I complete OLB registration for primary applicant
#    Examples:
#      | submissionId |
#      | 1         |

#  @flow=digital
#  Scenario Outline: Happy flow of existing applicant scenario "<submissionId>"
#    Given : I am at the home page
##    When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on continue button
#    Then : I click on Log In
#    And : I should navigate to personal details page
##    When : I provide the below personal details for existing applicant "<submissionId>"
#    Then : I click on personal details next button
#   Then  : I should see the account details page
#    And : I should see the funding page
#    Then : I provide below funding details for "<submissionId>"
#    Then : I click on funding details next button
#    Then : I should accept the all terms and conditions 3
#    And : I click on review details submit button
#    Then : I should see the reference Id page.
#    Examples:
#      | submissionId |
#      | 1         |




#  @flow=digital
#  Scenario Outline: Save the application of single applicant scenario "<submissionId>"
#    Given : I am at the home page
##    When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on continue button
#    Then : I click on the open an account
#  #Then : I click on proceed without prefill
#    Then : I should navigate to getting started page
#    Then : I should select state from dropdown
#    When : I provide the following details for "<submissionId>"
##  Then : I select the accept option
#    And : I click on start Application
#    And : I should navigate to personal details page
#    When : I provide the below personal details for "<submissionId>"
##    And : I provide the below additional applicant details for "<submissionId>"
#    Then : I click on personal details save button
#    Examples:
#      | submissionId |
#      | 1         |

#  @flow=digital
#  Scenario Outline: Cancel the application of single applicant scenario "<submissionId>"
#    Given : I am at the home page
##    When : I click on the consumer tab
#    Then : I click on product for "<submissionId>"
#    And : I click on the checkout
#    Then : I click on continue button
#    Then : I click on the open an account
#  #Then : I click on proceed without prefill
#    Then : I should navigate to getting started page
#    Then : I should select state from dropdown
#    When : I provide the following details for "<submissionId>"
##  Then : I select the accept option
#    And : I click on start Application
#    And : I should navigate to personal details page
#    When : I provide the below personal details for "<submissionId>"
##    And : I provide the below additional applicant details for "<submissionId>"
#    Then : I click on personal details cancel button
#    Examples:
#      | submissionId |
#      | 1         |

