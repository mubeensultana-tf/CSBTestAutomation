package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.GettingStartedPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class GettingStartedPageModel {
    private WebDriver driver;

    public GettingStartedPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = GettingStartedPageXpath.OPENANACCOUNT)
    public WebElement OpenAnAccount;
    
//    @FindBy(xpath = GettingStartedPageXpath.STATE)
//    public WebElement state;
    
    @FindBy(xpath = GettingStartedPageXpath.FIRSTNAME)
    public WebElement firstName;

    @FindBy(xpath = "//tf-input[@t-model='Party__r.LastName__c']//input")
    public WebElement lastName;

    @FindBy(xpath = "//tf-phone[@t-model='Party__r.PrimaryPhone__c']//input")
    public WebElement phoneNumber;
    
    @FindBy(xpath = "//tf-email[@t-model='Party__r.PrimaryEmail__c']//input")
    public WebElement personalEmail;
    
    @FindBy(xpath = GettingStartedPageXpath.ACCEPTPOLICY)
    public WebElement acceptPolicy;
    
    @FindBy(xpath = GettingStartedPageXpath.ACCEPTBTN)
    public WebElement acceptBtn;
    
    @FindBy(xpath = GettingStartedPageXpath.STARTAPPLICATIONBUTTON)
    public WebElement startApplicationButton;

    @FindBy(xpath = "//tf-dropdown[@t-model='State__c']//input")
    public WebElement state;

    @FindBy(xpath = "//div[contains(text(),' Massachusetts ')]")
    public WebElement selectedState;
    //Existing customer
    @FindBy(xpath = "//button[normalize-space()='Log In']")
    public WebElement LogInButton;

    @FindBy(xpath = "//button[normalize-space()=\"Ok, Let's Go!\"]")
    public WebElement LetsGoButton;

    @FindBy(xpath = "//tf-input[@t-model='Credentials.User']//input")
    public WebElement userName;

    @FindBy(xpath = "//tf-input[@t-model='Credentials.Password']//input")
    public WebElement password;

    @FindBy(xpath = "//tf-checkbox[@t-model='Accepted__c']//input")
    public WebElement disclosureLink;




}
