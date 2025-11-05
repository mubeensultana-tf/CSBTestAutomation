package com.cztffa.xpath.consumer;

public class PersonalInfoPageXpath {
    public static final String FIRSTNAME = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.FirstName__c']/descendant::input";
    public static final String MIDDLENAME = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.MiddleName__c']/descendant::input";
    public static final String LASTNAME = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.LastName__c']/descendant::input";
    public static final String DATEOFBIRTH = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='Party__r.BirthDate__c']/descendant::input";
    public static final String STREETADDRESS1 = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Line1__c']/descendant::input";
    public static final String CITY = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='City__c']/descendant::input";
    public static final String STATE = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='State__c']/descendant::input";
    public static final String ZIP = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='ZipCode__c']/descendant::input";
    public static final String PREFFEREDCONTACT = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-phone[@t-model='Party__r.PrimaryPhone__c']/descendant::input";
    public static final String MOBILEPHONE = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-phone[@t-model='Party__r.SecondaryPhone__c']/descendant::input";
    public static final String EMPLOYMENT = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Type__c']/descendant::input";
    public static final String EMAIL = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-email[@t-model='Party__r.PrimaryEmail__c']/descendant::input";
    public static final String EMPLOYER = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='CZPreviousEmployer__c']/descendant::input";
    public static final String OCCUPATION = "";
    public static final String SSN = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-ssn[@t-model='Party__r.NationalIdentifierValue__c']/descendant::input";
    public static final String IDTYPE = "//div[contains(text(),'%s')]";
    public static final String IDNUMBER = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='IdentificationNumber__c']/descendant::input";
    public static final String SECURITYWORD = "";
    public static final String ISSUEDATE = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='IssueDate__c']/descendant::input";
    public static final String EXPIRYDATE = "//tf-expansion-panel//[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='ExpirationDate__c']/descendant::input";
    public static final String PERSONALINFOSAVEBUTTON = "//button[normalize-space()='Save']";
}
