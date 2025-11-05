package com.cztffa.xpath.consumer;

public class ReviewPageXpath {
    public static final String TERMANDCONDITIONS = "(//tf-checkbox[@t-model='Accepted__c']//input)";
    public static final String SUBMITBUTTON = "//button[normalize-space()='Submit Application']";
    public static final String SUCCESSMSG = "//h2[contains(text(),'Congratulations')]";
    public static final String DISCLOSURECHECKBOX = "//div[@t-model='Disclosures__r.%s']//tf-checkbox//input";
}
