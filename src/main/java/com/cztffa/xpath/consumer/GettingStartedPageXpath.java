package com.cztffa.xpath.consumer;

public class GettingStartedPageXpath {
    public static final String OPENANACCOUNT = "//button[normalize-space()='Open an Account']";
    public static final String TF_DROPDOWN_0_INPUT = "//tf-dropdown[@t-model='State__c']//input";
    public static final String FIRSTNAME_2_INPUT = "//tf-input[@t-model='Party__r.FirstName__c']//input";
    public static final String LASTNAME_2_INPUT = "//tf-input[@t-model='Party__r.FirstName__c']//input";
    public static final String PHONENUMBER = "//div[contains(text(),'Last Name')]/div";
    public static final String PERSONALEMAIL = "//tf-email[@t-model='Party__r.PrimaryEmail__c']//input";
    public static final String ACCEPTPOLICY = "//tf-checkbox[@t-model='Accepted__c']//input";
    public static final String ACCEPTBTN = "//button[normalize-space()='Accept']";
    public static final String STARTAPPLICATIONBUTTON = "//button[normalize-space()='Start Application']";
}
