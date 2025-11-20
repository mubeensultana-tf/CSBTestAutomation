package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.AccountDetailsPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPageModel {
    private WebDriver driver;

    public AccountDetailsPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = AccountDetailsPageXpath.ONLINEBANKING)
    public WebElement OnlineBanking;
    
    @FindBy(xpath = AccountDetailsPageXpath.ADDDEBITCARDBUTTON)
    public WebElement addDebitCardButton;
    
    @FindBy(xpath = "//button[normalize-space()='Next']")
    public WebElement accountDetailsNextButton;
    
    @FindBy(xpath = AccountDetailsPageXpath.CLOSEBUTTON)
    public WebElement closeButton;
    
    @FindBy(xpath = AccountDetailsPageXpath.CANCELBUTTON)
    public WebElement cancelButton;

    
}
