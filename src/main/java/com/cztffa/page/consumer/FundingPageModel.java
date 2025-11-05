package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.FundingPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class FundingPageModel {
    private WebDriver driver;

    public FundingPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = FundingPageXpath.FUNDINGTOGGLEBUTTON)
    public WebElement fundingToggleButton;
    
    @FindBy(xpath = FundingPageXpath.SOURCEOFFUNDDRAPPLY)
    public WebElement sourceOfFundDrApply;
    
    @FindBy(xpath = FundingPageXpath.SOURCEOFFUNDOPAPPLY)
    public WebElement sourceOfFundOpApply;
    
    @FindBy(xpath = FundingPageXpath.SOURCEOFFUNDCCAPPLY)
    public WebElement sourceOfFundCCApply;
    
    @FindBy(xpath = FundingPageXpath.SOURCEOFFUNDDCAPPLY)
    public WebElement sourceOfFundDCApply;
    
    @FindBy(xpath = FundingPageXpath.FUNDINGNEXTBUTTON)
    public WebElement fundingNextButton;
    
    @FindBy(xpath = FundingPageXpath.FLOATERMSGVALIDATION)
    public WebElement floaterMsgValidation;
    
    @FindBy(xpath = FundingPageXpath.BTNPLAID)
    public WebElement btnPlaid;
    
    @FindBy(xpath = FundingPageXpath.FRAMEPLAID)
    public WebElement framePlaid;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDNUMBER)
    public WebElement frameCardNumber;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDNAME)
    public WebElement frameCardName;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDDATE)
    public WebElement frameCardDate;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDCVV)
    public WebElement frameCardCVV;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDZIP)
    public WebElement frameCardZip;
    
    @FindBy(xpath = FundingPageXpath.FRAMECARDSTREET)
    public WebElement frameCardStreet;
    
    @FindBy(xpath = FundingPageXpath.DRPACNUM)
    public WebElement drpAcNum;
    
    @FindBy(xpath = FundingPageXpath.OPTACNUM)
    public WebElement optAcNum;
    
    @FindBy(xpath = FundingPageXpath.FLAGTRANTOACC)
    public WebElement flagTrantoAcc;
    
    @FindBy(xpath = FundingPageXpath.ACKNOWLEDGEPALIDTOGGLE)
    public WebElement acknowledgePalidToggle;
    
    @FindBy(xpath = FundingPageXpath.AUTHORIZEPALIDTOGGLE)
    public WebElement authorizePalidToggle;
    
    @FindBy(xpath = FundingPageXpath.BTNAUTHCONTINUE)
    public WebElement btnAuthContinue;
    
    @FindBy(xpath = FundingPageXpath.BTNAUTHCONTINUEGUEST)
    public WebElement btnAuthContinueGuest;
    
    @FindBy(xpath = FundingPageXpath.BTNPAY)
    public WebElement btnPay;
    
    @FindBy(xpath = FundingPageXpath.CARDNUMBER)
    public WebElement cardNumber;
    
    @FindBy(xpath = FundingPageXpath.CARDHOLDER)
    public WebElement cardHolder;
    
    @FindBy(xpath = FundingPageXpath.CARDEXPIRY)
    public WebElement cardExpiry;
    
    @FindBy(xpath = FundingPageXpath.CVV)
    public WebElement cvv;
    
    @FindBy(xpath = FundingPageXpath.ZIPCODE)
    public WebElement zipCode;
    
    @FindBy(xpath = FundingPageXpath.STREET)
    public WebElement street;
    
    @FindBy(xpath = FundingPageXpath.TXTUSERNAME)
    public WebElement txtUsername;
    
    @FindBy(xpath = FundingPageXpath.TXTPASSWORD)
    public WebElement txtPassword;
    
    @FindBy(xpath = FundingPageXpath.BTNAUTHSUBMIT)
    public WebElement btnAuthSubmit;
    
    @FindBy(xpath = FundingPageXpath.BTNAUTHCONTINUEAFTER)
    public WebElement btnAuthContinueAfter;
    
    @FindBy(xpath = FundingPageXpath.BTNAUTHCONTINUEAFTERSUCCESS)
    public WebElement btnAuthContinueAfterSuccess;
    
    @FindBy(xpath = FundingPageXpath.BTNLOGIN)
    public WebElement btnLogin;
    
    @FindBy(xpath = FundingPageXpath.CHECKBOX)
    public WebElement checkbox;
    
    @FindBy(xpath = FundingPageXpath.BTNSUBMIT)
    public WebElement btnSubmit;
    
    @FindBy(xpath = FundingPageXpath.CHECKBOXTERMS)
    public WebElement checkboxTerms;
    
    @FindBy(xpath = FundingPageXpath.BTNSUBMITCONFIRM)
    public WebElement btnSubmitConfirm;
    
    @FindBy(xpath = FundingPageXpath.BTNCONTINUE)
    public WebElement btnContinue;
    
    public String plaidInstutionName = "//button[@aria-label='%s']";
    
    @FindBy(xpath = FundingPageXpath.ROUTINGNUMBER)
    public WebElement routingNumber;
    
    @FindBy(xpath = FundingPageXpath.ACCOUNTCHECKING)
    public WebElement accountChecking;
    
    @FindBy(xpath = FundingPageXpath.ACCOUNTSAVING)
    public WebElement accountSaving;
    
    @FindBy(xpath = FundingPageXpath.ACCOUNTNUMBER)
    public WebElement accountNumber;
    
}
