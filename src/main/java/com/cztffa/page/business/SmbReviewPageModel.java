package com.cztffa.page.business;
import com.cztffa.xpath.business.SmbReviewPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class SmbReviewPageModel {
    private WebDriver driver;

    public SmbReviewPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SmbReviewPageXpath.TERMANDCONDITIONS)
    public WebElement termAndConditions;
    
    @FindBy(xpath = SmbReviewPageXpath.SUBMITBUTTON)
    public WebElement submitButton;
    
    @FindBy(xpath = SmbReviewPageXpath.SUCCESSMSG)
    public WebElement successMsg;
    
    @FindBy(xpath = SmbReviewPageXpath.ISREQUIREDERROR)
    public WebElement isRequiredError;
    
    public String disclosureCheckbox = "//div[@t-model='Disclosures__r.%s']//tf-checkbox//input";
    
}
