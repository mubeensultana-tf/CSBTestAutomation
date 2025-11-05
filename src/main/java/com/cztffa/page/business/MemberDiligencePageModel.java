package com.cztffa.page.business;
import com.cztffa.xpath.business.MemberDiligencePageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class MemberDiligencePageModel {
    private WebDriver driver;

    public MemberDiligencePageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = MemberDiligencePageXpath.DOMESTICWIRESNONEPPLY)
    public WebElement domesticWiresNonepply;
    
    @FindBy(xpath = MemberDiligencePageXpath.CLOSEBUTTON)
    public WebElement closeButton;
    
    @FindBy(xpath = MemberDiligencePageXpath.TRADEAREA)
    public WebElement tradeArea;
    
    @FindBy(xpath = MemberDiligencePageXpath.TRADELOCAL)
    public WebElement tradeLocal;
    
    @FindBy(xpath = MemberDiligencePageXpath.PERSONALINFONEXTBUTON)
    public WebElement personalInfoNextButon;
    
    @FindBy(xpath = MemberDiligencePageXpath.LOCATION)
    public WebElement location;
    
    @FindBy(xpath = MemberDiligencePageXpath.INTERNATIONALWIRESNONEPAPPLY)
    public WebElement internationalWiresNonepApply;
    
    @FindBy(xpath = MemberDiligencePageXpath.MONETARYWIRESNONEAPPLY)
    public WebElement monetaryWiresNoneApply;
    
    @FindBy(xpath = MemberDiligencePageXpath.MEMEBERDILIGENCENEXTBUTTON_)
    public WebElement memeberDiligenceNextButton;;
    
    @FindBy(xpath = MemberDiligencePageXpath.COMPLEMENTORYPRODBUTTON)
    public WebElement complementoryProdButton;
    
    public String realtedPersonButton = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.1']//span[contains(text(),'Related Person')]";
    
    public String domesticWiresXpath = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='Domestic_Wires']";
    
    public String internationalWiresXpath = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='International_Wires']";
    
    public String monetoryWiresXpath = "//tf-expansion-panel[@t-model='Submission__c.PartyXrefs__r.%s']//tf-due-diligence[@t-model='Party__r.DueDiligences__r.0'] //tf-dropdown[@t-model='Monetary_Instruments']";
    
    public String selfEmployed = "//div[@class='dropdown-item ng-star-inserted'][contains(text(),'Self Employed')]";
    
}
