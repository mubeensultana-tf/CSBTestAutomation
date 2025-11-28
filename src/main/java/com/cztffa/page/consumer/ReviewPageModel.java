package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.ReviewPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class ReviewPageModel {
    private WebDriver driver;

    public ReviewPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ReviewPageXpath.TERMANDCONDITIONS)
    public WebElement termAndConditions;
    
    @FindBy(xpath = "//button[normalize-space()='Submit Application']")
    public WebElement submitButton;
    
    @FindBy(xpath = ReviewPageXpath.SUCCESSMSG)
    public WebElement successMsg;

    @FindBy(xpath = ReviewPageXpath.SUCCESSMSG)
    public WebElement reviewpageloadmsg;
    
    public String disclosureCheckbox = "//div[@t-model='Disclosures__r.%s']//tf-checkbox//input";

    @FindBy(xpath = "//tf-input[@t-model='username']//input")
    public WebElement olbusername;

    @FindBy(xpath = "//tf-input[@t-model='password']//input")
    public WebElement olbpassword;

    @FindBy(xpath = "//tf-input[@t-model='retypePassword']//input")
    public WebElement olbrenterpassword;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    public WebElement Registerbutton;

    @FindBy(xpath = "//tf-checkbox[@t-model='Accepted__c']//input")
    public WebElement olbregisteracceptbtn;

    @FindBy(xpath = ReviewPageXpath.OLBSUCCESSMSG)
    public WebElement olbregistrationsuccessmsg;



}
