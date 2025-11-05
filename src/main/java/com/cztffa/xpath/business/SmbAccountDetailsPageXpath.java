package com.cztffa.xpath.business;

public class SmbAccountDetailsPageXpath {
    public static final String BUSINESSCDINFO = "//div[contains(@class, 'd-flex') and contains(@class, 'justify-content-start')]/h2[text()='Business CD']";
    public static final String CERTIFICATETERM = "//div[@class='input-group tf-input-group']//input[@type='text' and starts-with(@aria-labelledby, 'CertificateTerm') and @role='combobox']";
    public static final String CERTIFICATETERMSELECT = "//div[contains(@class, 'dropdown-item ng-star-inserted') and contains(text(), '12 months 4.20%')]";
    public static final String AMOUNTTOENTER = "//div[@class='input-group tf-input-group']//input[@type='text' and @inputmode='decimal' and starts-with(@aria-labelledby, 'Amount')]";
    public static final String ACCOUNTDETAILSNEXTBUTON = "//button[@class='btn btn-primary float-end tf-step-action-next']";
}
