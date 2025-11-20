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

    public String firstName = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.FirstName__c']/descendant::input";
    
    public String middleName = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.MiddleName__c']/descendant::input";
    
    public String lastName = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.LastName__c']/descendant::input";
    
    public String dateOfBirth = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='Party__r.BirthDate__c']/descendant::input";
    
    public String streetAddress1 = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Line1__c']/descendant::input";

    public String addressText = "//div[contains(text(),'%s')]";

    public String city = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='City__c']/descendant::input";
    
    public String state = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='State__c']/descendant::input";
    
    public String zip = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='ZipCode__c']/descendant::input";
    
   // public String prefferedContact = "//tf-phone[@t-model='Party__r.PrimaryPhone__c']/descendant::input";
    
    public String mobilePhone = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-phone[@t-model='Party__r.PrimaryPhone__c']//input";
    
    public String employment = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Type__c']/descendant::input";
    
    public String email = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-email[@t-model='Party__r.PrimaryEmail__c']/descendant::input";
    
    public String employer = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='EmployerName__c']/descendant::input";
    
    public String occupation = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Occupation__c']//input";
    
    public String ssn = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-ssn[@t-model='Party__r.NationalIdentifierValue__c']/descendant::input";

    public String idType="//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//div[@t-model='Identifications__r.0']//tf-dropdown[@t-model='Type__c']/descendant::input";

    public String stateIssued = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='IssuingState__c']/descendant::input";

    public String identificationNumber = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='IdentificationNumber__c']/descendant::input";

    public String issueDate = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='IssueDate__c']//input";

    public String expiryDate ="//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='ExpirationDate__c']/descendant::input";

    @FindBy(xpath ="//div[contains(text(),'Alabama')]")
    public WebElement stateApply;

    @FindBy(xpath = "//div[contains(text(),'State ID')]")
    public WebElement idTypeStateIdApply;

    @FindBy(xpath = "//button[normalize-space()='Save My Progress']")
    public WebElement personalInfoSaveButton;

    @FindBy(xpath = "//tf-modal-footer//button[normalize-space()='Save My Progress']")
    public WebElement saveModalSavemyProgress;

    @FindBy(xpath = "//tf-modal-footer//button[normalize-space()='Continue']")
    public WebElement saveModalContinueButton;

    @FindBy(xpath = "//div[contains(text(),'Employed')]")
    public WebElement employmentEmployedApply;

    @FindBy(xpath = "//button[normalize-space()='Next']")
    public WebElement personalInfoNextButton;

    @FindBy(xpath = "//button//p[contains(text(), 'Cancel Application')]")
    public WebElement personalInfoCancelButton;


    @FindBy(xpath = "//tf-modal-footer//button[normalize-space()='Cancel Application']")
    public WebElement CancelButton;

    @FindBy(xpath = "//button[contains(text(),'Add Additional Applicant')]")
    public WebElement addAdditionalApplicantButton;






}
