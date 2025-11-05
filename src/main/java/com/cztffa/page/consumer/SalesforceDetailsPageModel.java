package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.SalesforceDetailsPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SalesforceDetailsPageModel {
    private WebDriver driver;

    public SalesforceDetailsPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SalesforceDetailsPageXpath.USERNAME)
    public WebElement username;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.PASSWORD)
    public WebElement password;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.LOGINBUTTON)
    public WebElement logInbutton;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.SEARCH)
    public WebElement search;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.TFOPERATIONS)
    public WebElement tfOperations;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.STARTAPPLICATION)
    public WebElement startApplication;
    
    @FindBy(xpath = SalesforceDetailsPageXpath.APPLAUNCHER)
    public WebElement appLauncher;

    @FindBy(xpath = "//button[@aria-label='Show Navigation Menu']")
    public WebElement homeMenu;

    @FindBy(xpath = "//li//span[contains(text(),'Home')]")
    public WebElement home;

}
