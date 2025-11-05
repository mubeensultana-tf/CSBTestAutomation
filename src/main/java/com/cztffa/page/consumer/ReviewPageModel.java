package com.cztffa.page.consumer;
import com.cztffa.xpath.consumer.ReviewPageXpath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class ReviewPageModel {
    private WebDriver driver;

    public ReviewPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ReviewPageXpath.TERMANDCONDITIONS)
    public WebElement termAndConditions;
    
    @FindBy(xpath = ReviewPageXpath.SUBMITBUTTON)
    public WebElement submitButton;
    
    @FindBy(xpath = ReviewPageXpath.SUCCESSMSG)
    public WebElement successMsg;
    
    public String disclosureCheckbox = "//div[@t-model='Disclosures__r.%s']//tf-checkbox//input";
    
}
