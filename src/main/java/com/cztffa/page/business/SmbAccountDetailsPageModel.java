package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbAccountDetailsPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbAccountDetailsPageModel {
    private WebDriver driver;

    public SmbAccountDetailsPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbAccountDetailsPageXpath.BUSINESSCDINFO)
    public WebElement BusinessCdInfo;
    
    @FindBy(xpath = SmbAccountDetailsPageXpath.CERTIFICATETERM)
    public WebElement certificateTerm;
    
    @FindBy(xpath = SmbAccountDetailsPageXpath.CERTIFICATETERMSELECT)
    public WebElement certificateTermSelect;
    
    @FindBy(xpath = SmbAccountDetailsPageXpath.AMOUNTTOENTER)
    public WebElement amountToEnter;
    
    @FindBy(xpath = SmbAccountDetailsPageXpath.ACCOUNTDETAILSNEXTBUTON)
    public WebElement accountDetailsNextButon;
    
}
