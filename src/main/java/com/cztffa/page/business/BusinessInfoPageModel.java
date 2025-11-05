package com.cztffa.page.business;
import com.cztffa.xpath.business.BusinessInfoPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class BusinessInfoPageModel {
    private WebDriver driver;

    public BusinessInfoPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = BusinessInfoPageXpath.STARTAPPLICATIONBUTTON)
    public WebElement startApplicationButton;
    
    public String businessName = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-input[@t-model='Party__r.LegalName__c']/descendant::input";
    
    public String businessType = "//label[contains(text(), 'Business Type')]/following::input[@role='combobox' and contains(@class, 'form-select')][1]";
    
    @FindBy(xpath = BusinessInfoPageXpath.BUSINESSTYPESELECTSOLE)
    public WebElement businessTypeSelectSole;
    
    @FindBy(xpath = BusinessInfoPageXpath.BUSINESSTYPESELECTLLC)
    public WebElement businessTypeSelectLlc;
    
    @FindBy(xpath = BusinessInfoPageXpath.BUSINESSTYPESELECTCORPORATION)
    public WebElement businessTypeSelectCorporation;
    
    @FindBy(xpath = BusinessInfoPageXpath.BUSINESSTYPESELECTGENRALPARTNERSHIP)
    public WebElement businessTypeSelectGenralPartnership;
    
    public String streetAddress1 = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-input[@t-model='Line1__c']/descendant::input";
    
    public String addressText = "//div[contains(text(),'%s')]";
    
    public String city = "//label[contains(text(), 'City')]/following::input[@type='text'][1]";
    
    public String state = "//input[@type='text' and @role='combobox' and contains(@class, 'form-control') and starts-with(@aria-labelledby, 'State-')]";
    
    @FindBy(xpath = BusinessInfoPageXpath.STATEOPTAPPLY)
    public WebElement stateOptApply;
    
    public String zip = "//input[@placeholder='Zip' and contains(@class, 'form-control')]";
    
    public String mobilePhone = "//input[@type='tel' and contains(@class, 'tf-phone-number')]";
    
    public String email = "//input[@type='email' and contains(@class, 'form-control')]";
    
    public String idType = "//input[contains(@class, 'form-select') and contains(@aria-labelledby, 'TaxIDType')]";
    
    @FindBy(xpath = BusinessInfoPageXpath.IDTYPESSNAPPLY)
    public WebElement idTypeSSNApply;
    
    @FindBy(xpath = BusinessInfoPageXpath.SSN)
    public WebElement ssn;
    
    @FindBy(xpath = BusinessInfoPageXpath.IDTYPETINAPPLY)
    public WebElement idTypeTINApply;
    
    @FindBy(xpath = BusinessInfoPageXpath.TIN)
    public WebElement tin;
    
    public String naicsCode = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//tf-dropdown[@t-model='Party__r.IndustryClassificationValue__c']/descendant::input";
    
    @FindBy(xpath = BusinessInfoPageXpath.SOYBEANFARMINGSELECTED)
    public WebElement soybeanFarmingSelected;
    
    public String dateOfEstablishment = "//div[contains(@class, 'tf-input-group')]//input[starts-with(@aria-labelledby, 'DateOfEstablishment') and @type='text']";
    
    public String relationCode = "//tf-expansion-panel//div[@t-model='Submission__c.PartyXrefs__r.%s']//tf-dropdown[@t-model='RelationCode__c']//input";
    
    public String relation = "//div[@class='dropdown-item'][contains(text(),'%s')]";
    
    @FindBy(xpath = BusinessInfoPageXpath.BUSINESSINFONEXTBUTON)
    public WebElement businessInfoNextButon;
    
}
