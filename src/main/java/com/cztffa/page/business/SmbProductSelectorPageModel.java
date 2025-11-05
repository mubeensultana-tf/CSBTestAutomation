package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbProductSelectorPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbProductSelectorPageModel {
    private WebDriver driver;

    public SmbProductSelectorPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbProductSelectorPageXpath.CLICKBUSINESSTAB)
    public WebElement clickBusinessTab;
    
    @FindBy(xpath = SmbProductSelectorPageXpath.CHECKOUTBTN)
    public WebElement checkOutBtn;
    
    @FindBy(xpath = SmbProductSelectorPageXpath.PROCEEDBTN)
    public WebElement proceedBtn;
    
    @FindBy(xpath = SmbProductSelectorPageXpath.PROCEEDWITHOUTPREFILLBTN)
    public WebElement proceedWithoutPrefillBtn;
    
}
