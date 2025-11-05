package com.cztffa.xpath.consumer;

public class SalesforceDetailsPageXpath {
    public static final String USERNAME = "//input[@type='email']";
    public static final String PASSWORD = "//input[@type='password']";
    public static final String LOGINBUTTON = "//input[@value='Log In to Sandbox']";
    public static final String SEARCH = "//input[@placeholder='Search apps and items...']";
    public static final String TFOPERATIONS = "//p[contains(text(),'Terafina')]//b[contains(text(),'Operation')]";
    public static final String STARTAPPLICATION = "//button[contains(., 'Start Application')]";
    public static final String APPLAUNCHER = "//button[@title='App Launcher']";
}
