package com.cztffa.xpath.business;

public class SmbReviewPageXpath {
    public static final String TERMANDCONDITIONS = "(//tf-checkbox[@t-model='Accepted__c']//input)";
    public static final String SUBMITBUTTON = "//button[@class='btn btn-primary d-block w-100 mw-490 m-auto tf-step-action-next']";
    public static final String SUCCESSMSG = "//p[contains(text(),'Submission')]";
    public static final String ISREQUIREDERROR = "//small[contains(text(),'is required')]";
    public static final String DISCLOSURECHECKBOX = "//div[@t-model='Disclosures__r.%s']//tf-checkbox//input";
}
