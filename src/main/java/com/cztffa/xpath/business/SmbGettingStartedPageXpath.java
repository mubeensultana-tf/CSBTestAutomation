package com.cztffa.xpath.business;

public class SmbGettingStartedPageXpath {
    public static final String FIRSTNAME = "//tf-input[@t-model='Party__r.FirstName__c']//input";
    public static final String LASTNAME = "//tf-input[@t-model='Party__r.LastName__c']//input";
    public static final String PHONENUMBER = "//tf-phone[@t-model='Party__r.PrimaryPhone__c']//input";
    public static final String PERSONALEMAIL = "//tf-email[@t-model='Party__r.PrimaryEmail__c']//input";
    public static final String ACCEPTPOLICY = "//tf-checkbox[@t-model='Accepted__c']//input";
    public static final String ACCEPTBTN = "//button[contains(text(),'Accept')]";
}
