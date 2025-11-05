package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbPersonalInfoPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbPersonalInfoPageModel {
    private WebDriver driver;

    public SmbPersonalInfoPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String selectionYes = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-declaration[@t-model='Party__r']//input[@value='Yes']";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.PROCEEDWITHOUTPREFILLBTN)
    public WebElement proceedWithoutPrefillBtn;
    
    public String firstName = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.FirstName__c']/descendant::input";
    
    public String middleName = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.MiddleName__c']/descendant::input";
    
    public String lastName = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.LastName__c']/descendant::input";
    
    public String dateOfBirth = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='Party__r.BirthDate__c']//input";
    
    public String citizenship = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Party__r.Citizenship__c']/descendant::input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.CITIZENSHIPCITIZENOPTAPPLY)
    public WebElement citizenshipCitizenOptApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.CITIZENSHIPALIENAPPLY)
    public WebElement citizenshipAlienApply;
    
    public String citizenshipCountry = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Party__r.CountryOfCitizenship__c']//input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.CITIZENSHIPCOUNTRYAPPLY)
    public WebElement citizenshipCountryApply;
    
    public String willingForeign = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Party__r.CZAffiliatedWithForeignGoverment__c']//input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.WILLINGFREIGNAPPLY)
    public WebElement willingFreignApply;
    
    public String streetAddress1 = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-address[@t-model='Party__r.AddressXrefs__r.0.Address__r']//tf-input[@t-model='Line1__c']/descendant::input";
    
    public String addressText = "(//div[contains(text(),'%s')])[1]";
    
    public String city = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='City__c']//input";
    
    public String state = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='State__c']/descendant::input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.STATEOPTAPPLY)
    public WebElement stateOptApply;
    
    public String zip = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='ZipCode__c']//input";
    
    public String prefferedContact = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Party__r.PreferredContactMethod__c']/descendant::input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.PREFERREDCONTACTMOBILEAPPLY)
    public WebElement preferredContactMobileApply;
    
    public String mobilePhone = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-phone[@t-model='Party__r.PrimaryPhone__c']//input";
    
    public String email = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-email[@t-model='Party__r.PrimaryEmail__c']//input";
    
    public String employment = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Party__r.Employments__r.0.Type__c']/descendant::input";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.EMPLOYMENTEMPLOYEDAPPLY)
    public WebElement employmentEmployedApply;
    
    public String employer = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.Employments__r.0.EmployerName__c']//input";
    
    public String occupation = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-ssn[@t-model='Party__r.NationalIdentifierValue__c']//input";
    
    public String ssn = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-ssn[@t-model='Party__r.NationalIdentifierValue__c']//input";
    
    public String idType = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='Type__c']/descendant::input";
    
    public String idNumber = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='IdentificationNumber__c']//input";
    
    public String securityWord = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-input[@t-model='Party__r.CZSecurityWord__c']//input";
    
    public String issueDate = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='IssueDate__c']//input";
    
    public String expiryDate = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-date[@t-model='ExpirationDate__c']//input";
    
    public String stateIssued = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='IssuingState__c']//input";
    
    public String countryIssued = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='IssuingCountry__c']//input";
    
    public String selectRelation = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='RelationCode__c']//input";
    
    public String getRelationionshipOfPerson = "//div[@class='dropdown-item ng-star-inserted'][contains(text(),'%s')]";
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.IDTYPEMILITARYAPPLY)
    public WebElement idTypeMilitaryApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.IDTYPEDRIVERLICENCEAPPLY)
    public WebElement idTypeDriverLicenceApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.IDTYPESTATEIDAPPLY)
    public WebElement idTypeStateIdApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.IDTYPEPASSPORTAPPLY)
    public WebElement idTypePassportApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.IDTYPERESIDENTALIENAPPLY)
    public WebElement idTypeResidentAlienApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.PERSONALINFONEXTBUTON)
    public WebElement personalInfoNextButon;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.ADDADDITIONALAPPLICANTBUTTON)
    public WebElement addAdditionalApplicantButton;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.STATEAPPLY)
    public WebElement stateApply;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.SECURITYWORDERROR)
    public WebElement securityWordError;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.SSNERROR)
    public WebElement ssnError;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.RELATIONSELECT)
    public WebElement relationSelect;
    
    @FindBy(xpath = SmbPersonalInfoPageXpath.PERCENTAGE)
    public WebElement percentage;
    
}
