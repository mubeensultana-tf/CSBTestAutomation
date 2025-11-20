package com.cztffa.xpath.consumer;

public class AccountDetailsPageXpath {
    public static final String ONLINEBANKING = "//tf-checkbox[@t-model='selected']//input";
    public static final String ADDDEBITCARDBUTTON = "//tf-checkbox[@t-model='selected']//input";
    public static final String NEXT = "//button[normalize-space()='Next']";
    public static final String CLOSEBUTTON = "//button[contains(text(),'Continue')]";
    public static final String CANCELBUTTON = "//button[contains(text(),'cancel']";
}
