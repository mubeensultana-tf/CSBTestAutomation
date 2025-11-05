package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbDisclosurePageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbDisclosurePageModel {
    private WebDriver driver;

    public SmbDisclosurePageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbDisclosurePageXpath.SIGNBTN)
    public WebElement signBtn;
    
    @FindBy(xpath = SmbDisclosurePageXpath.CONTINUEBTN)
    public WebElement continueBtn;
    
    @FindBy(xpath = SmbDisclosurePageXpath.ADOPTANDSIGNBTN)
    public WebElement adoptAndSignBtn;
    
    @FindBy(xpath = SmbDisclosurePageXpath.FINISHBTN)
    public WebElement finishBtn;
    
    @FindBy(xpath = SmbDisclosurePageXpath.SUBMITBTN)
    public WebElement submitBtn;
    
}
