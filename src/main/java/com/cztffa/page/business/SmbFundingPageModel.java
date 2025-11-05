package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbFundingPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbFundingPageModel {
    private WebDriver driver;

    public SmbFundingPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbFundingPageXpath.FUNDINGTOGGLEBUTTON)
    public WebElement fundingToggleButton;
    
    @FindBy(xpath = SmbFundingPageXpath.SOURCEOFFUNDDRAPPLY)
    public WebElement sourceOfFundDrApply;
    
    @FindBy(xpath = SmbFundingPageXpath.SOURCEOFFUNDOPAPPLY)
    public WebElement sourceOfFundOpApply;
    
    @FindBy(xpath = SmbFundingPageXpath.SOURCEOFFUNDCCAPPLY)
    public WebElement sourceOfFundCCApply;
    
    @FindBy(xpath = SmbFundingPageXpath.SOURCEOFFUNDDCAPPLY)
    public WebElement sourceOfFundDCApply;
    
    @FindBy(xpath = SmbFundingPageXpath.FUNDINGNEXTBUTTON)
    public WebElement fundingNextButton;
    
    @FindBy(xpath = SmbFundingPageXpath.FLOATERMSGVALIDATION)
    public WebElement floaterMsgValidation;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNPLAID)
    public WebElement btnPlaid;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMEPLAID)
    public WebElement framePlaid;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDNUMBER)
    public WebElement frameCardNumber;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDNAME)
    public WebElement frameCardName;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDDATE)
    public WebElement frameCardDate;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDCVV)
    public WebElement frameCardCVV;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDZIP)
    public WebElement frameCardZip;
    
    @FindBy(xpath = SmbFundingPageXpath.FRAMECARDSTREET)
    public WebElement frameCardStreet;
    
    @FindBy(xpath = SmbFundingPageXpath.DRPACNUM)
    public WebElement drpAcNum;
    
    @FindBy(xpath = SmbFundingPageXpath.OPTACNUM)
    public WebElement optAcNum;
    
    @FindBy(xpath = SmbFundingPageXpath.FLAGTRANTOACC)
    public WebElement flagTrantoAcc;
    
    @FindBy(xpath = SmbFundingPageXpath.ACKNOWLEDGEPALIDTOGGLE)
    public WebElement acknowledgePalidToggle;
    
    @FindBy(xpath = SmbFundingPageXpath.AUTHORIZEPALIDTOGGLE)
    public WebElement authorizePalidToggle;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNAUTHCONTINUE)
    public WebElement btnAuthContinue;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNAUTHCONTINUEGUEST)
    public WebElement btnAuthContinueGuest;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNPAY)
    public WebElement btnPay;
    
    @FindBy(xpath = SmbFundingPageXpath.CARDNUMBER)
    public WebElement cardNumber;
    
    @FindBy(xpath = SmbFundingPageXpath.CARDHOLDER)
    public WebElement cardHolder;
    
    @FindBy(xpath = SmbFundingPageXpath.CARDEXPIRY)
    public WebElement cardExpiry;
    
    @FindBy(xpath = SmbFundingPageXpath.CVV)
    public WebElement cvv;
    
    @FindBy(xpath = SmbFundingPageXpath.ZIPCODE)
    public WebElement zipCode;
    
    @FindBy(xpath = SmbFundingPageXpath.STREET)
    public WebElement street;
    
    @FindBy(xpath = SmbFundingPageXpath.TXTUSERNAME)
    public WebElement txtUsername;
    
    @FindBy(xpath = SmbFundingPageXpath.TXTPASSWORD)
    public WebElement txtPassword;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNAUTHSUBMIT)
    public WebElement btnAuthSubmit;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNAUTHCONTINUEAFTER)
    public WebElement btnAuthContinueAfter;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNAUTHCONTINUEAFTERSUCCESS)
    public WebElement btnAuthContinueAfterSuccess;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNLOGIN)
    public WebElement btnLogin;
    
    @FindBy(xpath = SmbFundingPageXpath.CHECKBOX)
    public WebElement checkbox;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNSUBMIT)
    public WebElement btnSubmit;
    
    @FindBy(xpath = SmbFundingPageXpath.CHECKBOXTERMS)
    public WebElement checkboxTerms;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNSUBMITCONFIRM)
    public WebElement btnSubmitConfirm;
    
    @FindBy(xpath = SmbFundingPageXpath.BTNCONTINUE)
    public WebElement btnContinue;
    
    public String plaidInstutionName = "//button[@aria-label='%s']";
    
}
