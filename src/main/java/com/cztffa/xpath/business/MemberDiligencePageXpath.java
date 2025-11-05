package com.cztffa.xpath.business;

public class MemberDiligencePageXpath {
    public static final String DOMESTICWIRESNONEPPLY = "//div[@class='dropdown-item'][contains(text(),'None')]";
    public static final String CLOSEBUTTON = "//button[contains(text(),'Continue')]";
    public static final String TRADEAREA = "//tf-dropdown[@t-model='CustomerTradeArea']//input";
    public static final String TRADELOCAL = "//div[@class='dropdown-item ng-star-inserted'][contains(text(),'Local')]";
    public static final String PERSONALINFONEXTBUTON = "//button[@class='btn btn-primary float-end tf-step-action-next']";
    public static final String LOCATION = "//tf-number[@t-model='NumberOfLocations']//input";
    public static final String INTERNATIONALWIRESNONEPAPPLY = "//div[@class='dropdown-item'][contains(text(),'None')]";
    public static final String MONETARYWIRESNONEAPPLY = "//div[@class='dropdown-item'][contains(text(),'None')]";
    public static final String MEMEBERDILIGENCENEXTBUTTON_ = "//button[@class='btn btn-primary float-end tf-step-action-next']";
    public static final String COMPLEMENTORYPRODBUTTON = "//button[@class='close']";
    public static final String REALTEDPERSONBUTTON = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//span[contains(text(),'Related Person')]";
    public static final String DOMESTICWIRESXPATH = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='Domestic_Wires']";
    public static final String INTERNATIONALWIRESXPATH = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='International_Wires']";
    public static final String MONETORYWIRESXPATH = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='Monetary_Instruments']";
    public static final String SELFEMPLOYED = "//div[@class='dropdown-item ng-star-inserted'][contains(text(),'Self Employed')]";
}
