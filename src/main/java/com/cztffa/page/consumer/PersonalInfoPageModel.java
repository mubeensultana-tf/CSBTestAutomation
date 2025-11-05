package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.PersonalInfoPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class PersonalInfoPageModel {
    private WebDriver driver;

    public PersonalInfoPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = PersonalInfoPageXpath.FIRSTNAME)
    public WebElement firstName;
    
    @FindBy(xpath = PersonalInfoPageXpath.MIDDLENAME)
    public WebElement middleName;
    
    @FindBy(xpath = PersonalInfoPageXpath.LASTNAME)
    public WebElement lastName;
    
    @FindBy(xpath = PersonalInfoPageXpath.DATEOFBIRTH)
    public WebElement dateOfBirth;
    
    @FindBy(xpath = PersonalInfoPageXpath.STREETADDRESS1)
    public WebElement streetAddress1;
    
    @FindBy(xpath = PersonalInfoPageXpath.CITY)
    public WebElement city;
    
    @FindBy(xpath = PersonalInfoPageXpath.STATE)
    public WebElement state;
    
    @FindBy(xpath = PersonalInfoPageXpath.ZIP)
    public WebElement zip;
    
    @FindBy(xpath = PersonalInfoPageXpath.PREFFEREDCONTACT)
    public WebElement prefferedContact;
    
    @FindBy(xpath = PersonalInfoPageXpath.MOBILEPHONE)
    public WebElement mobilePhone;
    
    @FindBy(xpath = PersonalInfoPageXpath.EMPLOYMENT)
    public WebElement employment;
    
    @FindBy(xpath = PersonalInfoPageXpath.EMAIL)
    public WebElement email;
    
    @FindBy(xpath = PersonalInfoPageXpath.EMPLOYER)
    public WebElement employer;
    
    @FindBy(xpath = PersonalInfoPageXpath.OCCUPATION)
    public WebElement occupation;
    
    @FindBy(xpath = PersonalInfoPageXpath.SSN)
    public WebElement ssn;
    
    @FindBy(xpath = PersonalInfoPageXpath.IDTYPE)
    public WebElement idType;
    
    @FindBy(xpath = PersonalInfoPageXpath.IDNUMBER)
    public WebElement idNumber;
    
    @FindBy(xpath = PersonalInfoPageXpath.SECURITYWORD)
    public WebElement securityWord;
    
    @FindBy(xpath = PersonalInfoPageXpath.ISSUEDATE)
    public WebElement issueDate;
    
    @FindBy(xpath = PersonalInfoPageXpath.EXPIRYDATE)
    public WebElement expiryDate;
    
    @FindBy(xpath = PersonalInfoPageXpath.PERSONALINFOSAVEBUTTON)
    public WebElement personalInfoSaveButton;
    
}
