package com.cztffa.xpath.business;

public class BusinessInfoPageXpath {
    public static final String STARTAPPLICATIONBUTTON = "//button[contains(text(), 'Start Application')]";
    public static final String BUSINESSNAME = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-input[@t-model='Party__r.LegalName__c']/descendant::input";
    public static final String BUSINESSTYPE = "//label[contains(text(), 'Business Type')]/following::input[@role='combobox' and contains(@class, 'form-select')][1]";
    public static final String BUSINESSTYPESELECTSOLE = "//div[contains(@class, 'dropdown-item') and @role='option' and normalize-space(text())='Sole Proprietorship']";
    public static final String BUSINESSTYPESELECTLLC = "//div[contains(@class, 'dropdown-item') and @role='option' and normalize-space(text())='Limited Liability Company']";
    public static final String BUSINESSTYPESELECTCORPORATION = "//div[contains(@class, 'dropdown-item') and @role='option' and normalize-space(text())='Corporation']";
    public static final String BUSINESSTYPESELECTGENRALPARTNERSHIP = "//div[contains(@class, 'dropdown-item') and @role='option' and normalize-space(text())='General Partnership']";
    public static final String STREETADDRESS1 = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-input[@t-model='Line1__c']/descendant::input";
    public static final String ADDRESSTEXT = "//div[contains(text(),'%s')]";
    public static final String CITY = "//label[contains(text(), 'City')]/following::input[@type='text'][1]";
    public static final String STATE = "//input[@type='text' and @role='combobox' and contains(@class, 'form-control') and starts-with(@aria-labelledby, 'State-')]";
    public static final String STATEOPTAPPLY = "//div[contains(@class, 'dropdown-item ng-star-inserted') and normalize-space()='Arizona']";
    public static final String ZIP = "//input[@placeholder='Zip' and contains(@class, 'form-control')]";
    public static final String MOBILEPHONE = "//input[@type='tel' and contains(@class, 'tf-phone-number')]";
    public static final String EMAIL = "//input[@type='email' and contains(@class, 'form-control')]";
    public static final String IDTYPE = "//input[contains(@class, 'form-select') and contains(@aria-labelledby, 'TaxIDType')]";
    public static final String IDTYPESSNAPPLY = "//div[contains(@class, 'dropdown-item') and normalize-space(text())='SSN']";
    public static final String SSN = "//input[@type='text' and contains(@aria-labelledby, 'SSN')]";
    public static final String IDTYPETINAPPLY = "//div[contains(@class, 'dropdown-item') and normalize-space(text())='TIN']";
    public static final String TIN = "//input[@type='text' and contains(@aria-labelledby, 'TIN')]";
    public static final String NAICSCODE = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-dropdown[@t-model='Party__r.IndustryClassificationValue__c']/descendant::input";
    public static final String SOYBEANFARMINGSELECTED = "//div[@class='dropdown-item ng-star-inserted'][contains(text(),'Soybean Farming')]";
    public static final String DATEOFESTABLISHMENT = "//div[contains(@class, 'tf-input-group')]//input[starts-with(@aria-labelledby, 'DateOfEstablishment') and @type='text']";
    public static final String RELATIONCODE = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='RelationCode__c']//input";
    public static final String RELATION = "//div[@class='dropdown-item'][contains(text(),'%s')]";
    public static final String BUSINESSINFONEXTBUTON = "//button[@class='btn btn-primary float-end tf-step-action-next']";
}
