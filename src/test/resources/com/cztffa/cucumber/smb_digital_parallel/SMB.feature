Feature: Review page flow

  @SMBFlow
  Scenario Outline: Happy flow of single business scenario "<submissionId>"
  Given : I am at the smb home page
  When : I click on the business tab
  Then : I click on smb products for "<submissionId>"
  And : I click on the checkout for smb
  Then : I click on proceed without prefill for smb
  Then : I should navigate to getting started page for smb
  When : I provide the following details for smb for "<submissionId>"
  And : I click on start Application for smb
  And : I should navigate to business details page
  When : I provide the below business details for "<submissionId>"
  Then : I click on business details next button
  And : I should navigate to business details personal page
  When : I provide the below business personal details for "<submissionId>"
  And : I provide the below additional applicant details for smb for "<submissionId>"
  Then : I click on personal details next button for smb
  And : I should see the relations page
  Then : I click on relations page next button
  And : I should see the customer due diligence page
  Then : I provide below due diligence details
  Then : I click on due diligence next button
  Then : I close promotion close button for smb
  And : I should see the account detail page
  Then : I should fill up the account details page
  Then : I click on account details next page
  And : I should see the business funding page
  Then : I provide below funding details for smb for "<submissionId>"
  Then : I click on business funding page next button
  And : I should see the documents page
  Then : I click on documents page next button
  Then : I should accept the all business terms and conditions 1
  And : I click on review details submit button for smb
  And : I click on signable disclosures button for smb
  Then : I should see the reference Id page for final submission of smb

  Examples:
    | submissionId |
    | 1            |
    | 2            |
