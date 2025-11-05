package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbGettingStartedPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbGettingStartedPageModel {
    private WebDriver driver;

    public SmbGettingStartedPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbGettingStartedPageXpath.FIRSTNAME)
    public WebElement firstName;
    
    @FindBy(xpath = SmbGettingStartedPageXpath.LASTNAME)
    public WebElement lastName;
    
    @FindBy(xpath = SmbGettingStartedPageXpath.PHONENUMBER)
    public WebElement phoneNumber;
    
    @FindBy(xpath = SmbGettingStartedPageXpath.PERSONALEMAIL)
    public WebElement personalEmail;
    
    @FindBy(xpath = SmbGettingStartedPageXpath.ACCEPTPOLICY)
    public WebElement acceptPolicy;
    
    @FindBy(xpath = SmbGettingStartedPageXpath.ACCEPTBTN)
    public WebElement acceptBtn;
    
}
