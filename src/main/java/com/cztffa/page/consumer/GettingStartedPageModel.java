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
    
    @FindBy(xpath = GettingStartedPageXpath.TF_DROPDOWN_0_INPUT)
    public WebElement state;
    
    @FindBy(xpath = GettingStartedPageXpath.FIRSTNAME_2_INPUT)
    public WebElement firstName;
    @FindBy(xpath = GettingStartedPageXpath.LASTNAME_2_INPUT)
    public WebElement lastName;
    
    @FindBy(xpath = GettingStartedPageXpath.PHONENUMBER)
    public WebElement phoneNumber;
    
    @FindBy(xpath = GettingStartedPageXpath.PERSONALEMAIL)
    public WebElement personalEmail;
    
    @FindBy(xpath = GettingStartedPageXpath.ACCEPTPOLICY)
    public WebElement acceptPolicy;
    
    @FindBy(xpath = GettingStartedPageXpath.ACCEPTBTN)
    public WebElement acceptBtn;
    
    @FindBy(xpath = GettingStartedPageXpath.STARTAPPLICATIONBUTTON)
    public WebElement startApplicationButton;
    
}
